import java.awt.*;

/**
 * Created by 朱宇斌 on 2018/3/21
 */

public class PipeMap {

    public static void main(String args[]) {

        int n = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0,0);
        points[1] = new Point(0,0);
        points[2] = new Point(0,0);
        points[0].setLocation(0,0);
        points[1].setLocation(-1,1);
        points[2].setLocation(0,1);
        int[][] edges = new int[3][3];
        edges[0][1] = edges[0][2] = edges[2][1] = 1;
        //edges[0][1] = 1;
        DualGraph dualGraph = new DualGraph(new Graph(n, points, edges));
        dualGraph.getDualGraph().show();

        n = 11;
        points = new Point[11];
        points[0] = new Point(-1,3);
        points[1] = new Point(0,-3);
        points[2] = new Point(2,3);
        points[3] = new Point(-3,-3);
        points[4] = new Point(1,2);
        points[5] = new Point(-2,1);
        points[6] = new Point(-4,0);
        points[7] = new Point(-3,-2);
        points[8] = new Point(3,-3);
        points[9] = new Point(1,-4);
        points[10] = new Point(-2,-4);
        edges = new int[][]{
            {0,0,0,0,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0},
            {0,0,0,0,1,0,0,0,1,0,0},
            {0,0,0,0,0,0,1,1,0,0,1},
            {0,0,0,0,0,1,0,0,1,0,0},
            {0,0,0,0,0,0,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,1},
            {0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0,0,0}
        };
        dualGraph = new DualGraph(new Graph(n, points, edges));
        dualGraph.getDualGraph().show();



        //TODO: judge crossing point and duplicating edge

    }

}
