package de.unistuttgart.dsass2022.ex08.p1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class KruskalTest {

    @Test
    public void testKruskal() throws IOException {
        WeightedGraph graph = new WeightedGraph();


        String dir = "src/main/resources/wilhelma.osm";
        InputStream is = new FileInputStream(dir);
        String jsonTxt = IOUtils.toString(is, "UTF-8");
        JSONObject json = new JSONObject(jsonTxt);

        //iterates over all "node"-types and adds the ID, lat and long to the graph
        for(Object s: json.getJSONArray("elements")) {
            JSONObject entry = (JSONObject) s;
            if(((String) entry.get("type")).compareTo("node") == 0) {
                long id = Long.valueOf(entry.get("id").toString());
                double lat = (double) entry.get("lat");
                double lon = (double) entry.get("lon");
                graph.addNode(id, new Node(id,lat,lon));
            }

        }

        //iterates over all "way"-types and adds them to the graph
        for(Object s: json.getJSONArray("elements")) {
            JSONObject entry = (JSONObject) s;

            if (((String) entry.get("type")).compareTo("way") == 0) {
                JSONArray wayPoints = entry.getJSONArray("nodes");
                for(int i = 0; i < wayPoints.length()-1; i++) {
                    long src = Long.valueOf(wayPoints.get(i).toString());
                    long dst =Long.valueOf(wayPoints.get(i+1).toString());

                    Node srcMeta = graph.getNode(src);
                    Node dstMeta = graph.getNode(dst);
                    //approximate the weight of the edge by the pythagoran theorem. For a better approx. have a look at geodesics
                    double weight = Math.sqrt(Math.pow(srcMeta.getLatitude()-dstMeta.getLatitude(),2)
                            +Math.pow(srcMeta.getLongitude()-dstMeta.getLongitude(),2));

                    //OSM-data is undirected
                    graph.addEdge(src,dst,weight);
                    graph.addEdge(dst,src,weight);
                }
            }
        }


        Set<IEdge> mst = MinimalSpanningTree.kruskal(graph);
    }
	
}
