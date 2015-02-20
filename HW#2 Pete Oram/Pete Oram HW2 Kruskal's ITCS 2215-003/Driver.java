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

public class Driver
{
   public static void main(String args) throws Exception
    {
        int adj_matrix[][];
        int num_of_vertices;
        String filename = "inputfile";
        String opfile = "outputfile";
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the filename :");
        filename = console.nextLine();
        System.out.println("Enter the output filename :");
        opfile = console.nextLine();
        Scanner scan = new Scanner(new File(filename));
        ArrayList<String> al = new ArrayList<String>();
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        HashMap<Integer,String> revmap = new HashMap<Integer,String>();
        int i = 1;
        while(scan.hasNext())//Reads in the graph from file
        {
            String line = scan.nextLine();
            String toks[] = (line.trim()).split("[ ,]");
            al.add(line);
            map.put(toks[0], i);
            revmap.put(i,toks[0]);
            i++;
        }
        num_of_vertices = al.size();
        adj_matrix = new int[num_of_vertices + 1][num_of_vertices + 1];
  
        for(int k = 0; k < al.size();k++)//Iterate to get all characters and separate them with comma
        {
            String line = al.get(k);
             
            String toks[] = (line.trim()).split("[ ,]");
            int src = map.get(toks[0]);
            for(int l = 2; l < toks.length; l += 4 )
            {
                
                int dest = map.get(toks[l]);
                int weight = Integer.parseInt(toks[l+2  ]);
                adj_matrix[src][dest] = weight;
                adj_matrix[dest][src] = weight;
            }
        }
         
        Kruskal kAlgorithm = new Kruskal(num_of_vertices);//Create new Kruskal object
        kAlgorithm.kAlgorithm(adj_matrix);
        int[][] spantree = kAlgorithm.spanning_tree;
        PrintWriter writer = new PrintWriter(opfile, "UTF-8");//Print
        for(int a = 1; a <= num_of_vertices; a++)
        {
            for(int b = a+1;b<=num_of_vertices;b++)
            {
                if(spantree[a][b] != 0)
                {
                    writer.println(revmap.get(a)+", "+revmap.get(b));
                }
            }
        }
        scan.close();
        writer.close();
        console.close();
    }
}