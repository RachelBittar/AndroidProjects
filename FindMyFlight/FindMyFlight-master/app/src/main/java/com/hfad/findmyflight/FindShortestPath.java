package com.hfad.findmyflight;
import org.w3c.dom.Node;

import java.util.*;

public class FindShortestPath {

    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    List<List<Node> > adj;
    private int V; // Number of vertices

    public FindShortestPath(int v) {
        this.V = v;
        dist = new int[V];
        settled = new HashSet<Integer>();
      //  pq = new PriorityQueue<Node>(V, new Node());
    }





}




//public class DPQ {
//
//    public DPQ(int V)
//    {
//        this.V = V;
//        dist = new int[V];
//        settled = new HashSet<Integer>();
//        pq = new PriorityQueue<Node>(V, new Node());
//    }
//
//    // Function for Dijkstra's Algorithm
//    public void dijkstra(List<List<Node> > adj, int src)
//    {
//        this.adj = adj;
//
//        for (int i = 0; i < V; i++)
//            dist[i] = Integer.MAX_VALUE;
//
//        // Add source node to the priority queue
//        pq.add(new Node(src, 0));
//
//        // Distance to the source is 0
//        dist[src] = 0;
//        while (settled.size() != V) {
//
//            // remove the minimum distance node
//            // from the priority queue
//            int u = pq.remove().node;
//
//            // adding the node whose distance is
//            // finalized
//            settled.add(u);
//
//            e_Neighbours(u);
//        }
//    }