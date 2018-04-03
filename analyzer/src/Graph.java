import java.awt.*;

/**
 * Created by 朱宇斌 on 2018/4/2
 */

public class Graph {

    private int n; // the number of nodes
    private Point[] nodes; // each value represents the position of node;
    private int[][] edges; // each value represents the weight of directed edge.

    /* constructor without parameter*/
    Graph() {
        /* initialize n */
        n = 0;
        /* initialize nodes */
        nodes = null;
        /* initialize edges */
        edges = null;
    }

    /* constructor with parameter*/
    Graph(int newN, Point[] newNodes, int[][] newEdges){
        /* initialize n */
        n = newN;
        /* initialize nodes */
        nodes = new Point[n];
        for (int i = 0; i < n; i += 1) {
            nodes[i] = new Point(newNodes[i]);
        }
        /* initialize edges */
        edges = new int[n][n];
        for (int i = 0; i < n; i += 1) {
            for (int j = 0;j < n; j += 1) {
                edges[i][j] = newEdges[i][j];
            }
        }
    }

    /* constructor with another Graph */
    Graph(Graph newGraph) {
        this(newGraph.getN(), newGraph.getNodes(), newGraph.getEdges());
    }

    /* getData */
    public int getN() {
        return n;
    }

    public int[][] getEdges() {
        return edges;
    }

    public Point[] getNodes() {
        return nodes;
    }

    /* show the graph */
    public void show() {
        System.out.println("-->n=" + n);
        System.out.println("-->nodes:");
        for (int i = 0; i < n; i += 1) {
            System.out.println("x=" + nodes[i].x + ",y=" + nodes[i].y);
        }
        System.out.println("-->edges matrix:");
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < n; j += 1) {
                System.out.print(edges[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
