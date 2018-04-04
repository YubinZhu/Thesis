import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by 朱宇斌 on 2018/4/2
 */

public class DualGraph {

    private Graph originGraph; // it's undirected graph

    private static final int NOEDGE = 0;

    private static final int UNTRAVELED = 1;

    private static final int TRAVELED = 2;

    private static final double NOANGLE = 7; // because polar angle value cannot bigger than 2 * PI

    /* constructor */
    DualGraph(Graph newOriginGraph) {
        originGraph = new Graph(newOriginGraph);
    }

    /* get dual graph */
    public Graph getDualGraph() {
        /* make a directed graph based on origin graph */
        /* In directed graph, edges[i][j] represents status (no edge, untraveled or traveled) of the directed edge from node i to j. */
        Graph directedGraph = new Graph(originGraph);

        int n = directedGraph.getN();
        Point[] nodes = directedGraph.getNodes();
        int[][] edges = directedGraph.getEdges();

        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (edges[i][j] != 0) {
                    edges[i][j] = UNTRAVELED;
                }
                if (i > j && (edges[i][j] == UNTRAVELED || edges[j][i] == UNTRAVELED)) {
                    edges[i][j] = edges[j][i] = UNTRAVELED;
                }
            }
        }

        /* begin algorithm (the left-most turning algorithm) */
        /* 1. Change all edge in undirected graph to two-way edge in directed graph. (Already done) */
        /* 2. Order all outgoing edges of each node by polar angle. */
        double[][] polarAngle = new double[n][n]; // PI to 2 * PI
        int[][] polarIndex = new int[n][n]; // store index
        /* calculate angle of each edge */
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (edges[i][j] == NOEDGE) {
                    polarAngle[i][j] = NOANGLE;
                }
                else {
                    polarAngle[i][j] = Math.atan((nodes[j].getY() - nodes[i].getY()) / (nodes[j].getX() - nodes[i].getX())) + 0.0;
                    if (nodes[j].getX() < nodes[i].getX()) {
                        polarAngle[i][j] += Math.PI;
                    }
                    else if (nodes[j].getY() < nodes[i].getY()) {
                        polarAngle[i][j] += 2 * Math.PI;
                    }
                }
            }
            HashMap hashMap = new HashMap();
            for (int j = 0; j < n; j += 1) {
                hashMap.put(polarAngle[i][j], j);
            }
            /* sort here */
            Arrays.sort(polarAngle[i]);
            for (int j = 0; j < n; j += 1) {
                if (polarAngle[i][j] == NOANGLE) {
                    polarIndex[i][j] = -1;
                }
                else {
                    polarIndex[i][j] = (int)hashMap.get(polarAngle[i][j]);
                }
                System.out.print(polarIndex[i][j] + " ");
            }
            System.out.println();
        }

        /* 3. Find an untraveled edge <u, v> and set it to present edge. */
        ArrayList<ArrayList<Integer>> regions = new ArrayList<>();
        int regionsN = 0;
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (edges[i][j] == NOEDGE || edges[i][j] == TRAVELED) {
                    continue;
                }
                /* 'edge' is present edge */
                regions.add(new ArrayList<>());
                int u = i, v = j;
                while (true) { //TODO
                    //System.out.print(u + " ");
                    regions.get(regionsN).add(u);
                    /* Mark present edge <u, v> */
                    edges[u][v] = TRAVELED;
                    /* Find the last edge before <v, u> in outgoing edges of v according to polar index and set it to next present edge. */
                    int index = 0;
                    for (; index < n; index += 1) {
                        if (polarIndex[v][index] == u) {
                            break;
                        }
                    }
                    if (index == 0) {
                        for (int k = 0; k < n; k += 1) {
                            if (polarIndex[v][k] == -1) {
                                index = k - 1;
                                break;
                            }
                        }
                    } else {
                        index -= 1;
                    }
                    u = v;
                    v = polarIndex[v][index];
                    /* Repeat till found present edge has been traveled. */
                    if (edges[u][v] == TRAVELED) {
                        //System.out.println();
                        regionsN += 1;
                        break;
                    }
                }
            }
        }

        System.out.println("--> " + regions.size() + " regions in total:");
        for (ArrayList<Integer> arrayList : regions) {
            /* System.out.println(arrayList.toString()); // not recommend */
            System.out.println(Arrays.toString(arrayList.toArray()));
        }



        return directedGraph; //TODO
    }

}