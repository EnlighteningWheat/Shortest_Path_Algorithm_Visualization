import java.util.*;

// 用Map初始化graph中顶点和边的位置
// 顶点 Map-> vMap
// 边 Map-> eMap

public class GraphicalParam {

    public Map<Integer, int[]> vMap;
    public Map<String, int[]> eMap;
    public Map<Integer, int[]> dMap;
    final public int graph_capacity = 10;

    public GraphicalParam() {

        int[] v0, v1, v2, v3, v4, v5, v6, v7, v8, v9;

        int[][][] e = new int[][][] {};

        // v存放顶点位置参数 前两个数是顶点中的参数的坐标 后两个数是顶点圈圈的坐标
        v0 = new int[] { 395, 275, 380, 250 };
        v1 = new int[] { 535, 275, 520, 250 };
        v2 = new int[] { 325, 405, 310, 380 };
        v3 = new int[] { 605, 405, 590, 380 };
        v4 = new int[] { 465, 495, 450, 470 };
        v5 = new int[] { 465, 145, 450, 120 };
        v6 = new int[] { 230, 310, 215, 285 };
        v7 = new int[] { 700, 310, 685, 285 };
        v8 = new int[] { 310, 555, 295, 530 };
        v9 = new int[] { 620, 555, 605, 530 };

        vMap = new HashMap<>();
        vMap.put(0, v0);
        vMap.put(1, v1);
        vMap.put(2, v2);
        vMap.put(3, v3);
        vMap.put(4, v4);
        vMap.put(5, v5);
        vMap.put(6, v6);
        vMap.put(7, v7);
        vMap.put(8, v8);
        vMap.put(9, v9);

        // e存放边位置参数
        // 前四个数是线段两端的位置坐标 后两个数是权值的位置坐标

        eMap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            int[] v1_temp;
            v1_temp = vMap.get(i);
            for (int j = i; j < 10; j++) {
                String key = "(" + String.valueOf(i) + "," + String.valueOf(j) + ")";
                int[] v2_temp;
                v2_temp = vMap.get(j);
                int[] v3_temp;
                int mark1 = (v1_temp[0] + v2_temp[0]) / 2;
                int mark2 = (v1_temp[1] + v2_temp[1]) / 2;
                v3_temp = new int[] { v1_temp[0], v1_temp[1], v2_temp[0], v2_temp[1], mark1, mark2 };
                eMap.put(key, v3_temp);
            }
        }

        dMap = new HashMap<>();

        int d_first_x = 800;
        int d_first_y = 600;
        int x_temp = d_first_x;
        int y_temp = d_first_y;

        for (int i = 0; i < 10; i++) {
            int[] d_temp = new int[] { x_temp, y_temp, x_temp, y_temp + 60 };
            dMap.put(i, d_temp);
            x_temp += 50;
        }

    }

    public void readAdmarix(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int[] v1_temp;
            v1_temp = vMap.get(i);
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] != 999) {

                }

            }
        }

    }
}
