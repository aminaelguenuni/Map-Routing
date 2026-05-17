
/*************************************************************************
 *  Compilation:  javac Distances.java
 *  Execution:    java Distances file < input.txt
 *  Dependencies: EuclideanGraph.java Dijkstra.java In.java StdIn.java
 *
 *  Reads in a map from a file, and repeatedly reads in two integers s
 *  and d from standard input, and prints the distance of the shortest
 *  path from s to d to standard output.
 ************************************************************************/
public class Distances {

    public static void main(String[] args) {

        In graphin = new In(args[0]);
        EuclideanGraph G = new EuclideanGraph(graphin);
        Dijkstra dijkstra = new Dijkstra(G);
        System.err.println("Done reading the graph " + args[0]);
        System.err.println("Enter query pairs from stdin");

        int queryCount = 0;
        long totalVertices = 0;
        long startTime = System.currentTimeMillis();

        while(!StdIn.isEmpty()) {
            int s = StdIn.readInt();
            int d = StdIn.readInt();
            double result = dijkstra.distance(s, d);  
            System.out.println(result);   
            totalVertices += dijkstra.getVerticesExamined();  
            queryCount++;
        }
   
        long endTime = System.currentTimeMillis();
        double totalSeconds = (endTime - startTime) / 1000.0;

        System.out.println("------ RESULTS ------");
        System.out.println("Total queries:         " + queryCount);
        System.out.println("Total time:            " + totalSeconds + " seconds");
        System.out.println("Avg vertices examined: " + (totalVertices / queryCount)); 
    }
}