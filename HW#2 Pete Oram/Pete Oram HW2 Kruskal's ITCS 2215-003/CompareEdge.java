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

 
class CompareEdge implements Comparator<Edge> //Looks at the weight of the edges and compares them 
{
    public int compare(Edge edge1, Edge edge2)
    {
        if (edge1.weight < edge2.weight)
            return -1;
        if (edge1.weight > edge2.weight)
            return 1;
        return 0;
    }
}