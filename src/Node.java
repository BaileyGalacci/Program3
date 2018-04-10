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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author g96t776
 * Date: 4/11/16
 * Overview: Part of Weighted Graph
 *  Node class contains the code to setup the pieces of each graph, with connections based on a nested class Edge
 *  : char name, boolean isMarked;
 *      Edge class contains a destination Node[, Weight (assumed 0 if not defined)]
 *      Edge is assumed to be directional, as each Node contains their own Edges. 
 */



public class Node {
    
    // variables
    private char name = '&';
    private boolean isMarked = false;
    private Edge[] edge = new Edge[6];
    
    public Node(char inName){
        name = inName;
        
    }

    public boolean isIsMarked() {
        return isMarked;
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }
    
    // Nested Edge class, always up to 6 edges (including one to itself of weight infinity
    public class Edge{
        int weight = 0;
        Node destination = null;
        
        public Edge( Node inDest, int inWeight){
            destination = inDest;
            weight = inWeight;     
        }
        
        public Edge(Node inDest){
            destination = inDest;
        }
    }
    
    
    // methods
    public void setEdge(int edgeN, Node inDest, int inWeight){
        this.edge[edgeN] = new Edge(inDest, inWeight);
        return;
    }
    
    public Edge getEdge(int edgeN){
        return this.edge[edgeN];
    }
    
    
    
}
