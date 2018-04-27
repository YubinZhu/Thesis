import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by 朱宇斌 on 2018/3/21
 */

public class PipeMap {

    private static final String REGEX = ",";

    private static final int PRECISION = 1000;

    public static void main(String args[]) throws IOException {

        // read prepared file
        String path = "misc/pipe/cascade_prepare.txt";
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int n = Integer.parseInt(bufferedReader.readLine());
        Point[] nodes = new Point[n];
        for (int i = 0; i < n; i += 1) {
            String[] strings;
            strings = bufferedReader.readLine().split(REGEX);
            nodes[i] = new Point();
            nodes[i].setLocation(Double.parseDouble(strings[0]) * PRECISION, Double.parseDouble(strings[1]) * PRECISION);
        }
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i += 1) {
            String[] strings;
            strings = bufferedReader.readLine().split(REGEX);
            edges[i] = new int[n];
            for (int j = 0; j < n; j += 1) {
                edges[i][j] = Integer.parseInt(strings[j]);
            }
        }
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < n; j += 1) {
                if (edges[i][j] != 0) {
                    edges[i][j] = 1;
                }
                if (i > j && (edges[i][j] == 1 || edges[j][i] == 1)) {
                    edges[i][j] = edges[j][i] = 1;
                }
            }
        }
        int targetN = Integer.parseInt(bufferedReader.readLine());
        Point[] targets = new Point[targetN];
        for (int i = 0; i < targetN; i += 1) {
            String[] strings;
            strings = bufferedReader.readLine().split(REGEX);
            targets[i] = new Point();
            targets[i].setLocation(Double.parseDouble(strings[0]) * PRECISION, Double.parseDouble(strings[1]) * PRECISION);
        }
        bufferedReader.close();

        HashSet hashSet = new HashSet();
        for (int i = 0; i < n; i += 1) {
            for (int j = i; j < n; j += 1) {
                if (edges[i][j] != 0) {
                    hashSet.add(new Point(i, j));
                }
            }
        }

        // binding target point
        // result: each line represent the two index of nodes which make up edge
        File targetFile = new File("misc/pipe/cascade_binding.txt");
        Writer writer = new FileWriter(targetFile);

        for (Point target : targets) {
            Point minIndex = null;
            double minDistance = Double.MAX_VALUE;
            Iterator iterator = hashSet.iterator();
            while(iterator.hasNext()) {
                Point nodesIndex = (Point)iterator.next();
                Point a = nodes[nodesIndex.x];
                Point b = nodes[nodesIndex.y];
                double distanceTargetA = Math.sqrt(Math.pow(target.x - a.x, 2) + Math.pow(target.y - a.y, 2));
                double distanceTargetB = Math.sqrt(Math.pow(target.x - b.x, 2) + Math.pow(target.y - b.y, 2));
                double distanceAB = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
                double halfPerimeter = (distanceTargetA + distanceTargetB + distanceAB) / 2;
                double heightToLineAB = Math.sqrt(halfPerimeter * (halfPerimeter - distanceTargetA) * (halfPerimeter - distanceTargetB) * (halfPerimeter - distanceAB)) * 2 / distanceAB;
                double realDistance;
                if (Math.pow(distanceTargetA, 2) > Math.pow(distanceTargetB, 2) + Math.pow(distanceAB, 2)) {
                    realDistance = distanceTargetB;
                } else if (Math.pow(distanceTargetB, 2) > Math.pow(distanceTargetA, 2) + Math.pow(distanceAB, 2)) {
                    realDistance = distanceTargetA;
                } else {
                    realDistance = heightToLineAB;
                }
                if (realDistance < minDistance) {
                    minDistance = realDistance;
                    minIndex = nodesIndex;
                }
            }
            writer.write(minIndex.x + "," + minIndex.y + "\n");
        }
        writer.close();

        // edge-related map


    }

}
