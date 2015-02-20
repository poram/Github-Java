//Pete Oram
//ITCS 2215-003
//HW #2
//#800159179

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * This program implements Kruskal's algorithm to find the Minimum Spanning Tree of a graph.
 * The input file is the adjacency list of the graph, the output file contains the edges that are part 
 * of the Minimum Spanning Tree (MST).
 * 
 * @Pete Oram #800159179
 * @ITCS-2215-003 HW #2
 * @3/19/2014
 */

public class Kruskal
{    
    private int visited[]; //Node visited
    public int spanning_tree[][]; //Spanning tree
    private List<Edge> edges;
    private int numVertices; //For counting number of vertices
    public static final int MAX_VALUE = 999;    
    
    public Kruskal(int numVertices) //Takes in the number of vertices
    {
        this.numVertices = numVertices;
        visited = new int[this.numVertices + 1];
        spanning_tree = new int[numVertices + 1][numVertices + 1];
        edges = new LinkedList<Edge>(); //Creates new linked list      
    }
  
    public void kAlgorithm(int adjacencyMatrix[][])
    {
        boolean finished = false;
        for (int source = 1; source <= numVertices; source++)//Iterate while source is less than or equal to the number of vertices
        {
            for (int destination = 1; destination <= numVertices; destination++)
            {
                if (adjacencyMatrix[source][destination] != MAX_VALUE && source != destination)
                {
                    Edge edge = new Edge();
                    edge.sourcevertex = source;
                    edge.destinationvertex = destination;
                    edge.weight = adjacencyMatrix[source][destination];
                    adjacencyMatrix[destination][source] = MAX_VALUE;
                    edges.add(edge);
                }
            }
        }
        Collections.sort(edges, new EdgeComparator());
        CheckCycle checkCycle = new CheckCycle();
        for (Edge edge : edges)
        {
            spanning_tree[edge.sourcevertex][edge.destinationvertex] = edge.weight;
            spanning_tree[edge.destinationvertex][edge.sourcevertex] = edge.weight;
            if (checkCycle.checkCycle(spanning_tree, edge.sourcevertex))
            {
                spanning_tree[edge.sourcevertex][edge.destinationvertex] = 0;
                spanning_tree[edge.destinationvertex][edge.sourcevertex] = 0;
                edge.weight = -1;
                continue;
            }
            visited[edge.sourcevertex] = 1;//Set to 1
            visited[edge.destinationvertex] = 1;
            for (int i = 0; i < visited.length; i++)//Will iterate until all visited
            {
                if (visited[i] == 0)
                {
                    finished = false;
                    break;
                } else
                {
                    finished = true;
                }
            }
            if (finished)
                break;
        }  
    }
}