import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Stack;
import java.lang.Math.*;
import java.lang.String;
/*
 * TO DO LIST:
 *  We have a completed set up for the input, just need to the algorithms for the minimum spanning trees. 
 *  Shouldn't require anything but a few methods to write and drop into main after inserting is finished. 
 */

/**
 *
 * @author g96t776
 * Date: 4/11/18
 * Overview: WeightedGraph is the main class of the project
 *  Takes a file input and converts it into a weighted graph that can later be converted into a minimum spanning tree
 *  Contains the String array 
 */
public class WeightedGraph {
    char A;
    int B;
    String C;
    
    public Node[] nodeList;
    
    public WeightedGraph(){
       nodeList = new Node[6];
    }
    
    // insert a new Node
    public void insert(char inName, int location){
        nodeList[location] = new Node(inName);
    }
    
    public static void main(String[] args) throws IOException {
        
        WeightedGraph theGraph = new WeightedGraph();
        
    
        final Path IN_PATH = FileSystems.getDefault().getPath("input", "input.txt");
        Charset charset = Charset.forName("US-ASCII");
       
        try (BufferedReader reader = Files.newBufferedReader(IN_PATH, charset)) {
            String line = null;
            if ((line = reader.readLine()) != null) {        // gather first line for names and number of nodes
                // we want to get 1 char at a time to insert as Node names, separated by commas, ending in \n ?
                String[] nodesToInsert = line.split(",");
                // start at String nTI 0, then create nodes for each name until '\n' is hit
                int i=0;
                for (String string : nodesToInsert) {
                    char charToInsert = nodesToInsert[i].toCharArray()[0];      // sets charToInsert as the first (and only?) character in String nodesToInsert[i]
                    theGraph.insert(charToInsert, i);
                    i++;
                }
                
                // now all the nodes are given names, and we have the total number i (this will help to assign edges)
                int nodeLoc = 0, edgeLoc = 0;
                while ((line = reader.readLine()) != null) {        // gather 1 line at a time
                    String[] edgesToInsert = line.split(",");
                          
                    edgeLoc = 0;
                    for (String string : edgesToInsert) {               // FIND A WAY TO DEAL WITH INFINITY (NO EDGE)
                        int intToInsert = Integer.parseInt(string);     // get the weight
                        // node destination is theGraph.nodeList[edgeLoc]
                        theGraph.nodeList[nodeLoc].setEdge(edgeLoc, theGraph.nodeList[edgeLoc], intToInsert);
                        // now bump up edgeLoc so the next edge is placed correctly
                        edgeLoc ++;
                    }
                    
                    // set the distance to itself to 0
                    theGraph.nodeList[nodeLoc].setEdge(nodeLoc, theGraph.nodeList[nodeLoc], 0);
                                        
                    // bump up nodeLoc so we move up a node
                    nodeLoc ++;
                }
                
            } else System.out.println("buffered reader error, no first line found\n");
            
            System.out.println("We did it?\n");
            
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
    return;
    }
}
