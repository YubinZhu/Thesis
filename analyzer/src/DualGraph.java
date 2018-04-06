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

    private int dualN;

    private ArrayList<Integer>[] dualRegions;

    private ArrayList<ArrayList<ArrayList<Integer>>> dualEdges;

    /* constructor */
    DualGraph(Graph newOriginGraph) {
        originGraph = new Graph(newOriginGraph);

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
        ArrayList<ArrayList<Integer>> polarIndex = new ArrayList<>();
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
            polarIndex.add(new ArrayList<>());
            for (int j = 0; j < n; j += 1) {
                if (polarAngle[i][j] == NOANGLE) {
                    break;
                }
                else {
                    polarIndex.get(i).add((Integer)hashMap.get(polarAngle[i][j]));
                }
            }
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
                while (true) {
                    regions.get(regionsN).add(u);
                    /* Mark present edge <u, v> */
                    edges[u][v] = TRAVELED;
                    /* Find the last edge before <v, u> in outgoing edges of v according to polar index and set it to next present edge. */
                    int index = 0;
                    for (; index < n; index += 1) {
                        if (polarIndex.get(v).get(index) == u) {
                            break;
                        }
                    }
                    if (index == 0) {
                        for (int k = 0; k < n; k += 1) {
                            index = polarIndex.get(v).size() - 1;
                        }
                    } else {
                        index -= 1;
                    }
                    u = v;
                    v = polarIndex.get(v).get(index);
                    /* Repeat till found present edge has been traveled. */
                    if (edges[u][v] == TRAVELED) {
                        regionsN += 1;
                        break;
                    }
                }
            }
        }

        dualN = regions.size();
        dualRegions = new ArrayList[dualN];

        for (int i = 0; i < dualN; i += 1) {
            dualRegions[i] = regions.get(i);
        }

        dualEdges = new ArrayList<>(); // store values of edges between two regions
        for (int i = 0; i < dualN; i += 1) {
            dualEdges.add(new ArrayList<>());
            for (int j = 0; j < dualN; j += 1) {
                dualEdges.get(i).add(new ArrayList<>());
            }
        }

        /* 4. For each edge in origin graph, add edge between to regions in dual graph. */
        for (int i = 0; i < originGraph.getN(); i += 1) {
            for (int j = 0; j < originGraph.getN(); j += 1) {
                if (i > j && ((originGraph.getEdges()[i][j] != 0 || originGraph.getEdges()[j][i] != 0))) {
                    boolean mark = false;
                    for (int k = 0; k < regions.size(); k += 1) {
                        if (regions.get(k).indexOf(i) != -1 && regions.get(k).indexOf(j) != -1) {
                            for (int l = regions.size() - 1; l >= 0; l -= 1) {
                                if (regions.get(l).indexOf(i) != -1 && regions.get(l).indexOf(j) != -1) {
                                    dualEdges.get(k).get(l).add(originGraph.getEdges()[i][j]);
                                    dualEdges.get(l).get(k).add(originGraph.getEdges()[i][j]);
                                    mark = true;
                                    break;
                                }
                            }
                            if (mark == true) {
                                break;
                            }
                        }
                    }
                }
            }
        }



    }

    public void show() {
        System.out.println("--> " + dualN + " regions in total:");
        for (ArrayList<Integer> arrayList : dualRegions) {
            /* System.out.println(arrayList.toString()); // not recommend */
            System.out.println(Arrays.toString(arrayList.toArray()));
        }
        System.out.println("--> dual matrix:");
        for (ArrayList<ArrayList<Integer>> arrayListArrayList: dualEdges) {
            for (ArrayList<Integer> arrayList: arrayListArrayList) {
                System.out.print(Arrays.toString(arrayList.toArray()) + " ");
            }
            System.out.println();
        }
    }

}
