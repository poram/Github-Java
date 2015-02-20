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

 
class CycleCheck //Stack is used to pop off top elements
{
    private Stack<Integer> stack;
    private int adjacencyMatrix[][];
  
    public  CycleCheck()
    {
        stack = new Stack<Integer>();
    }
  
    public boolean CycleCheck(int adj_matrix[][], int source)
    {
        boolean cyclepresent = false;
        int number_of_nodes = adj_matrix[source].length - 1; 
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
        for (int sourcevertex = 1; sourcevertex <= number_of_nodes; sourcevertex++)
        {
            for (int destinationvertex = 1; destinationvertex <= number_of_nodes; destinationvertex++)
            {
                adjacencyMatrix[sourcevertex][destinationvertex] = adj_matrix[sourcevertex][destinationvertex];
            }
         }
  
         int visited[] = new int[number_of_nodes + 1];
         int element = source;
         int i = source;
         visited[source] = 1;
         stack.push(source);
  
         while (!stack.isEmpty())//Will iterate as long is the stack has values
         {
             element = stack.peek();
             i = element;
             while (i <= number_of_nodes)
             {
                 if (adjacencyMatrix[element][i] >= 1 && visited[i] == 1)
                 {
                     if (stack.contains(i))
                     {
                         cyclepresent = true;
                         return cyclepresent;
                     }
                 }
                 if (adjacencyMatrix[element][i] >= 1 && visited[i] == 0)
                 {
                     stack.push(i);
                     visited[i] = 1;
                     adjacencyMatrix[element][i] = 0;// mark as labelled;
                     adjacencyMatrix[i][element] = 0;
                     element = i;
                     i = 1;
                     continue;
                  }
                  i++;
             }
             stack.pop();
        }
        return cyclepresent;
    }
}




