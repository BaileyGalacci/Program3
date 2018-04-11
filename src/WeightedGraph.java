
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
 * authors: Edward Galacci, Hans Mazur
 * Date: 4/10/18 Overview: WeightedGraph is the main class of
 * the project Takes a file input and converts it into a weighted graph that can
 * later be converted into a minimum spanning tree Contains the String array
 */
public class WeightedGraph {

    public Node[] nodeList;
    Tree primTree, kruskalTree;
    Tree[] Forest;
    int[][] dist;

    public WeightedGraph() {
        nodeList = new Node[6];
        primTree = new Tree();
        kruskalTree = new Tree();
        dist = new int[6][6];
        Forest = new Tree[5];
        for (int j = 0; j < 5; j++) {
            Forest[j] = new Tree();
        }
    }

    // nested class Tree
    public class Tree {

        public Node[] nodeOrder;



        public Node.Edge[] edgeList = new Node.Edge[10];
        Node.PriorityQueue Q;
        public Tree(){
            nodeOrder = new Node[6];
            nodeOrder[0] = new Node('A');
            Q = nodeOrder[0].new PriorityQueue(10);
        }
    }

    // insert a new Node
    public void insert(char inName, int location) {
        nodeList[location] = new Node(inName);
    }

    // print out distance table
    public void printTable(int[][] data, int i){
        for(int j=0; j<i; j++){
                for(int k=0; k < i; k++){
                   
                }
            }
    }
    
    public static void main(String[] args) throws IOException {

        WeightedGraph theGraph = new WeightedGraph();

        final Path IN_PATH = FileSystems.getDefault().getPath("input", "input.txt");
        Charset charset = Charset.forName("US-ASCII");

        try (BufferedReader reader = Files.newBufferedReader(IN_PATH, charset)) {
            int i = 0;
            String line = null;
            if ((line = reader.readLine()) != null) {        // gather first line for names and number of nodes
                // we want to get 1 char at a time to insert as Node names, separated by commas, ending in \n ?
                String[] nodesToInsert = line.split(",");
                // start at String nTI 0, then create nodes for each name until '\n' is hit

                for (String string : nodesToInsert) {
                    char charToInsert = nodesToInsert[i].toCharArray()[0];      // sets charToInsert as the first (and only?) character in String nodesToInsert[i]
                    theGraph.insert(charToInsert, i);
                    i++;
                }

                // now all the nodes are given names, and we have the total number of nodes i (this will help to assign edges)
                int nodeLoc = 0, edgeLoc = 0;
                while ((line = reader.readLine()) != null) {        // gather 1 line at a time
                    String[] edgesToInsert = line.split(",");

                    edgeLoc = 0;
                    for (String string : edgesToInsert) {               // FIND A WAY TO DEAL WITH INFINITY (NO EDGE)
                        int intToInsert = Integer.parseInt(string);     // get the weight
                        // node destination is theGraph.nodeList[edgeLoc]
                        theGraph.nodeList[nodeLoc].setEdge(edgeLoc, theGraph.nodeList[edgeLoc], intToInsert);
                        // now bump up edgeLoc so the next edge is placed correctly
                        edgeLoc++;
                    }

                    // set the distance to itself to 0
                    theGraph.nodeList[nodeLoc].setEdge(nodeLoc, theGraph.nodeList[nodeLoc], 0);

                    // bump up nodeLoc so we move up a node
                    nodeLoc++;
                }

            } else {
                System.out.println("buffered reader error, no first line found\n");
            }

            System.out.println("We did it?\n");

            // Prim's algorithm     //////////////////////////////////////////////////////
            // start at the first vertex
            int[] D = new int[i];
            Node.Edge[] edgeTemp;
            D[0] = 0;   // distance to itself is 0
            
            int j=1;
            for (j=1; j<i; j++){
                D[j] = Integer.MAX_VALUE;
            }
            
            // initialize the Tree
            
            // choose node 1 as our first node arbitrarily
            theGraph.primTree.nodeOrder[0] = theGraph.nodeList[0];
            theGraph.nodeList[0].setIsMarked(true);
            
            // we need a priority queue
            //theGraph.primTree.Q
            
            
            
            for(int k=0; k<i-1; k++){
                // find all vertexes from our newest node and put them in the array
                int z=999999999;
                Node.Edge tempEdge;
                for(j=0; j<i; j++){
                    // most recent node in nodeOrder is k
                    if(theGraph.primTree.nodeOrder[k].getEdge(j).weight != Integer.MAX_VALUE && theGraph.primTree.nodeOrder[k].getEdge(j).destination.isIsMarked() == false && theGraph.primTree.nodeOrder[k].getEdge(j).weight != 0 ) {
               
                        // found a new edge for the queue
                        theGraph.primTree.Q.insert(theGraph.primTree.nodeOrder[k].getEdge(j).destination, theGraph.primTree.nodeOrder[k].getEdge(j).weight);
                        
                       
                    }
                    
                }
                // all relavent edges are now stored in edgeTemp
               // for(j=0; j<theGraph.primTree.Q.size(); j++){          Loop not needed since Q is sorted
                    // check if dest is marked
                while(!theGraph.primTree.Q.isEmpty()){
                    tempEdge = theGraph.primTree.Q.remove();            // pops an edge off the stack
                    if(tempEdge.weight < z && tempEdge.weight != 0) {
                        z = tempEdge.weight;
                        // store this location for later
                        theGraph.primTree.nodeOrder[k+1] = tempEdge.destination;
                        theGraph.primTree.edgeList[k] = tempEdge;
                    }
                }
                
                theGraph.primTree.nodeOrder[k+1].setIsMarked(true);
                //}
                // mark new Node in tree
                
            }
            
            // print out the results for Prim's Algorithm
            System.out.print("\nPrim's Algorithm");
            for(j=0;j<i-1;j++) {
                char startNode = theGraph.primTree.nodeOrder[j].getName();
                char endNode = theGraph.primTree.edgeList[j].destination.getName();
                System.out.printf("\n%c => %c",startNode,endNode);
            }
            System.out.println("");
/**
            // KRUSKALS ALGORITHM       ////////////////////////////////////////////////
            //unmarks everything and populates the forest
            for (j = 0; j < i; j++) {
                theGraph.nodeList[j].setIsMarked(false);
                theGraph.Forest[j].nodeOrder[0] = theGraph.nodeList[j];
            }
            
            
            //creates edge list
            Node.Edge[] edges = new Node.Edge[21];
            for (j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (j==k) {
                        continue;
                    }
                    edges[4*j+k] = theGraph.nodeList[j].getEdge(k);
                    
                }
            }
            
            //main kruskal loop
            while(theGraph.Forest[0].nodeOrder[4]==null) {
                //finds least weighted edge
                int w = 99;
                Node.Edge e;
                for(j=0;j<20;j++) {
                    if(w > edges[j].weight) {
                        w = edges[j].weight;
                        e = edges[j]; 
                    }
                } 
            }
**/            
            // Dijkstra's Algorithm         /////////////////////////////////////
            
            for (j = 0; j < i; j++) {   // unmark all the nodes again
                theGraph.nodeList[j].setIsMarked(false);
                for (int k=0; k< i; k++){   // populate all with standard values, or inf if they are not present
                    theGraph.dist[j][k] = Integer.MAX_VALUE;
                    try{
                        theGraph.dist[j][k] = theGraph.nodeList[j].getEdge(k).weight;
                    } catch (NullPointerException y) {
                        System.err.format("NullPointerException:asfasdf %s%n", y);
                    }
                }
                
            }
            
            // Start at node 0, repeat until no more nodes are left ('i' will track this)
            for(j=0; j<i; j++){
                for(int k=0; k < i; k++){
                    for(int l=0; l<i; l++){
                        if (theGraph.dist[i][j] > theGraph.dist[i][k] + theGraph.dist[k][j]){
                            theGraph.dist[i][j] = theGraph.dist[i][k] + theGraph.dist[k][j];
                        }
                    }
                }
            }
                
            
           

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

    }
}
