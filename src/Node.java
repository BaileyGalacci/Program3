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

    public char getName() {
        return name;
    }
    
    // Nested Edge class, always up to 6 edges (including one to itself of weight infinity
    public class Edge{
        int weight = 0;
        Node destination = null;
        Node home = null;
        
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
        this.edge[edgeN].home = this;
    }
    
    public Edge getEdge(int edgeN){
        return this.edge[edgeN];
    }
    

       /** Class PriorityQueue **/
    public class PriorityQueue
    {
        private Edge[] heap; 
        private int heapSize, capacity;
    
        /** Constructor **/
        public PriorityQueue(int capacity)
        {    
            this.capacity = capacity + 1;
            heap = new Edge[this.capacity];
            heapSize = 0;
        }
        /** function to clear **/
        public void clear()
        {
            heap = new Edge[capacity];
            heapSize = 0;
        }
        /** function to check if empty **/
        public boolean isEmpty()
        {
            return heapSize == 0;
        }
        /** function to check if full **/
        public boolean isFull()
        {
            return heapSize == capacity - 1;
        }
        /** function to get Size **/
        public int size()
        {
            return heapSize;
        }
        /** function to insert task **/
        public void insert(Node dest, int weight)
        {
            Edge newEdge = new Edge(dest, weight);
    
            heap[++heapSize] = newEdge;
            int pos = heapSize;
            while (pos != 1 && newEdge.weight > heap[pos/2].weight)
            {
                heap[pos] = heap[pos/2];
                pos /=2;
            }
            heap[pos] = newEdge;    
        }
        /** function to remove task **/
        public Edge remove()
        {
            int parent, child;
            Edge item, temp;
            if (isEmpty() )
            {
                System.out.println("Heap is empty");
                return null;
            }
    
            item = heap[1];
            temp = heap[heapSize--];
    
            parent = 1;
            child = 2;
            while (child <= heapSize)
            {
                if (child < heapSize && heap[child].weight < heap[child + 1].weight)
                    child++;
                if (temp.weight >= heap[child].weight)
                    break;
    
                heap[parent] = heap[child];
                parent = child;
                child *= 2;
            }
            heap[parent] = temp;
    
            return item;
        }
    }
    
}
