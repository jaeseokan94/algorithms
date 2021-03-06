import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//https://www.hackerrank.com/challenges/dijkstrashortreach 
public class Solution {
    
    public static class Graph {
        
        public Graph(){
            
        }
       
        public int minDist(int[] dist , boolean[] spt){
            //initialise min val
            int min = Integer.MAX_VALUE;
            int min_index = -1 ;
            int V = dist.length;
            for(int v = 0 ; v < V ; v++){
                if(spt[v] == false && dist[v] <= min)
                    {
                    min = dist[v];
                    min_index = v;
                }
            }
            return min_index;
        }
            
        
        public int[] shortestReach(int[][] g, int startId) { // 0 indexed
            int V = g[0].length;
            boolean[] spt = new boolean[V];
            int[] dist = new int[V] ; 
            for(int i = 0 ; i < V ; i ++){
                dist[i] = Integer.MAX_VALUE;
                spt[i] = false ;
            }
            
            dist[startId] = 0 ;
            
            for(int i = 0 ; i < V-1 ; i++)
                {
                
                int u = minDist(dist,spt);
                
                spt[u] = true ;
                
                for(int v = 0 ; v < V ; v++){
                    
                    if(!spt[v] && g[u][v]!=0 && dist[u] != Integer.MAX_VALUE &&
                      dist[u]+g[u][v]<dist[v]){
                        dist[v] = dist[u] + g[u][v];
                    }
                        
                    
                }
                
            }
            
            for(int v = 0 ; v < V ; v++){
                if(dist[v]==Integer.MAX_VALUE){
                    dist[v] = -1 ;
                }
            
            }
            
            
            
          
            
            
            return dist ;
        }
        
        
        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] g = new int[n][n];
            Graph graph = new Graph();
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                int weight = scanner.nextInt(); 
                if(g[u][v]!=0){
                     
                    if(weight>g[u][v]){
                        weight = g[u][v];
                        
                    }
                }
                g[u][v] = weight ;
                g[v][u] = weight ;
                
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(g,startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
    }
}
