/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raceofrandom;

/*
 * COMP2112 Fall 2025 - Project 3: Race of Random
 * Group 2
 * Murat Karatas - 22COMP1015
 * Bugra Alp - 23SOFT1048
 */

import java.util.Random;

public class Graph {
    int V;
    int E;
    LinkedList<Edge>[] adj;
    
    int[][] nextCheckpoints;
    int[][] distances;
    int[] edgeCount;
    
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        edgeCount = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Edge>();
        }
    }
    
    public void addEdge(int from, int to, int distance) {
        Edge edge = new Edge(from, to, distance);
        adj[from].insertLast(edge);
        edgeCount[from]++;
        E++;
    }
    
    public void buildArrays() {
        nextCheckpoints = new int[V][];
        distances = new int[V][];
        
        for (int v = 0; v < V; v++) {
            int count = edgeCount[v];
            nextCheckpoints[v] = new int[count];
            distances[v] = new int[count];
            
            Node<Edge> current = adj[v].first;
            int index = 0;
            while (current != null) {
                nextCheckpoints[v][index] = current.data.to;
                distances[v][index] = current.data.distance;
                index++;
                current = current.next;
            }
        }
    }
    
    public int[] getRandomNextWithDistance(int checkpoint, Random random) {
        int count = edgeCount[checkpoint];
        if (count == 0) return new int[] {-1, 0};
        
        int index = random.nextInt(count);
        int next = nextCheckpoints[checkpoint][index];
        int dist = distances[checkpoint][index];
        
        return new int[] {next, dist};
    }
    
    public int getDistance(int from, int to) {
        int count = edgeCount[from];
        for (int i = 0; i < count; i++) {
            if (nextCheckpoints[from][i] == to) {
                return distances[from][i];
            }
        }
        return -1;
    }
    
    public LinkedList<Edge> getEdges(int from) {
        return adj[from];
    }
    
    public boolean hasPath(int from, int to) {
        boolean[] visited = new boolean[V];
        return dfsPath(from, to, visited);
    }
    
    private boolean dfsPath(int current, int target, boolean[] visited) {
        if (current == target) return true;
        
        visited[current] = true;
        
        Node<Edge> node = adj[current].first;
        while (node != null) {
            int next = node.data.to;
            if (!visited[next]) {
                if (dfsPath(next, target, visited)) {
                    return true;
                }
            }
            node = node.next;
        }
        
        return false;
    }
    
    public boolean hasDirectRoad(int from, int to) {
        int count = edgeCount[from];
        for (int i = 0; i < count; i++) {
            if (nextCheckpoints[from][i] == to) {
                return true;
            }
        }
        return false;
    }
}