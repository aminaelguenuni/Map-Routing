/*************************************************************************
 *  Dijkstra's algorithm.
 *
 *************************************************************************/

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;                  


public class Dijkstra {
    private static double INFINITY = Double.MAX_VALUE;
    private static double EPSILON  = 0.000001;  

    private EuclideanGraph G;
    private double[] dist;//storing shortest distance to each city
    private int[] pred;//storing cities that came before one on the path
    private List<Integer> touched; //IDEA 1: declaring a list to store touched or visisted vertices 
    private int verticesExamined = 0;//for vertices counting README file puroposes 
    public Dijkstra(EuclideanGraph G) {
        this.G = G;
    }
    public int getVerticesExamined() { return verticesExamined; }
    // return shortest path distance from s to d
    public double distance(int s, int d) {
        dijkstra(s, d);
        return dist[d];
    }

    // print shortest path from s to d  (interchange s and d to print in right order)
    public void showPath(int d, int s) {
        dijkstra(s, d);
        if (pred[d] == -1) {
            System.out.println(d + " is unreachable from " + s);
            return;
        }
        for (int v = d; v != s; v = pred[v])
            System.out.print(v + "-");
        System.out.println(s);
    }

    // plot shortest path from s to d
    public void drawPath(int s, int d) {
        dijkstra(s, d);
        if (pred[d] == -1) return;
        Turtle.setColor(Color.red);
        for (int v = d; v != s; v = pred[v])
            G.point(v).drawTo(G.point(pred[v]));
        Turtle.render();
    }

    // Dijkstra's algorithm to find shortest path from s to d
    private void dijkstra(int s, int d) {
        int V = G.V();//vertices number of cities
        verticesExamined = 0;//reset counter at start of each query

        // initialize Original code
        /*dist = new double[V];
        pred = new int[V];
        for (int v = 0; v < V; v++) dist[v] = INFINITY;
        for (int v = 0; v < V; v++) pred[v] = -1;
        */
        //IDEA 1 implementation: only re-initializing those values that changed in the previous query.
        if (dist == null) {//First time running the algo
            dist = new double[V];//creates arrays of size V, Vertices
            pred = new int[V];//creates array of size V, pred 
            for (int v = 0; v < V; v++) dist[v] = INFINITY; //setting all dist to infi
            for (int v = 0; v < V; v++) pred[v] = -1; //setting predecessors to -1, nothing yet 
            touched = new ArrayList<>();//create the touched list
        } else {//if  this is not the first time running the algo 
            // only reset vertices we actually touched last time
            for (int v : touched) {//looping through the vertices we used last time ONLY
                dist[v] = INFINITY;//same things again here, reseting work 
                pred[v] = -1;
            }
            touched.clear();//clear the touched list for the next query
        }

        IndexPQ pq = new IndexPQ(V);//priority queue that can store V nodes (cities)
        for (int v = 0; v < V; v++) pq.insert(v, dist[v]);//inserts every node into the priority queue with its current distance.

        // set distance of source, starting city 
        dist[s] = 0.0;
        pred[s] = s;
        pq.change(s, dist[s]);
        touched.add(s);  // mark source as touched as it is the first one/origin

        // run Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int v = pq.delMin();//give the city with the lowest priotity= first to be processed 
            verticesExamined++;
            //// System.out.println("process " + v + " " + dist[v]);
            //Early stop if we find the destination IDEA 1
            if (v == d) break; // v = the city we just popped off queue, d = destination 
            // v is not reachable from s so stop
            if (pred[v] == -1) break;
            touched.add(v);              // we track every vertex we visited IDEA 1 implementation 
            
            // scan through all nodes w adjacent to v
            IntIterator i = G.neighbors(v);//getting a list of all cities directly connect to v
            while (i.hasNext()) {//keep looping as long as there are more enighbors to check
                int w = i.next();//the next neighbor city
                double newDist = dist[v] + G.distance(v, w);//IDEA 2
                // newDist: real distance traveled to reach w through v
                // dist[v]: how far we already traveled to get to v
                // G.distance(v,w): Distance from v to w
                double priority = newDist + G.distance(w, d) - G.distance(v, d);
                //  A* priority (biases search toward destination)
                //what the algorithm uses to decide next/ the ordering in the queue
                // G.distance(w, d): straight line from w to destination
                // G.distance(v, d): straight line from v to destination
                if (newDist < dist[w] - EPSILON) {//only update if we find a shorter path 
                    dist[w] = newDist;//store real distance
                    pq.change(w, priority);   // IDEA 2 update the position of w as it has a new priotiry number
                    pred[w] = v;
                    touched.add(w);//IDEA 1: adding w to touched list 
                }
            }
        }
       
    }

}
