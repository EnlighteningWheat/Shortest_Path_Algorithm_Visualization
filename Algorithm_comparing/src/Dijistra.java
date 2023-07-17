import java.util.Arrays;

/*
    * 为了实现辨别已访问节点，正在访问节点，还有未访问节点，我们借助visit表
    * visit表中1代表已经访问，2代表正在访问，0代表未访问节点。
    * 为了记录每次的最短路径长的变化，我们采用dist表来记录最短路径长度。
    * 为了记录每个当前访问节点判断他的一个联通的节点的弧长，我们用一个二维数组来记录，该数组中每一行储存（当前访问节点，与他联通的那个节点）
    * 为了使得visit表和dist表同步，我们将每个当前访问节点判断他的一个联通的节点是否能构成最短路径作为一单位时间，将visit表的更改也当作一单位时间。
    * 我们定义一个数组用来记录时间，和该时间visit表或者dist表是否变化。其中1代表访问的弧改变，2代表disit表和弧改变，3代表visit表改变,0代表未储存。
    */
public class Dijistra {
    //运行时间和运行内存
    long runTime;
    long runMem;
    int[][] di_adjacencyMatrix;
    int vertexNum;
    int[] visited;
    int[] distance;
    AlgorithmInterface di_Interface;

    public Dijistra(int[][] di_adjacencyMatrix, int Vertexnum) {
        di_Interface = new AlgorithmInterface(Vertexnum);
        this.di_adjacencyMatrix = di_adjacencyMatrix;
        vertexNum = Vertexnum;
    }

    public void excute_Dijistra() {
        
        visited = new int[vertexNum];
        distance = new int[vertexNum];
        Arrays.fill(distance, 999);
        distance[0] = 0;

        // 定义接口的标尺
        int di_second = 0;
        int visit_num = 0;
        int dist_num = 0;
        int edge_num = 0;

        // 先将初始化数据装入接口当中
        copy(distance ,di_Interface.be_dist[dist_num]);
        dist_num++;
        for (int i = 0; i < vertexNum - 1; i++) {
            int minDistance = 999;
            int minIndex = -1;
            for (int j = 0; j < vertexNum; j++) {
                if (visited[j] == 0 && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minIndex = j;
                }
            }
            di_Interface.dj_visit[visit_num]=minIndex;
            visit_num++;
            di_Interface.dj_time[di_second] = 3;
            di_second++;
            for (int k = 0; k < vertexNum; k++) {

                // 向接口中记录弧长
                if(di_adjacencyMatrix[minIndex][k] != 999){
                    
                    di_Interface.dj_edge[edge_num][0] = minIndex;
                    di_Interface.dj_edge[edge_num][1] = k;
                    edge_num++;
                    di_Interface.dj_time[di_second] = 1;
                
                if (visited[k] == 0  && distance[minIndex] != 999
                        && distance[minIndex] + di_adjacencyMatrix[minIndex][k] < distance[k]) {
                   
                    distance[k] = distance[minIndex] + di_adjacencyMatrix[minIndex][k];
                    // 向接口中记录最短距离表
                    System.out.print("dist");
                    copy(distance, di_Interface.dj_dist[dist_num]);
                    for (int j = 0; j < distance.length; j++) {
                        System.out.print(distance[j]+" ");
                    }
                    dist_num++;
                    di_Interface.dj_time[di_second] = 2;
                }
              di_second++;
            }
               
            }
            visited[minIndex]=1;
        }
        
    }

    public AlgorithmInterface returnResult() {
        Runtime r=Runtime.getRuntime();
        r.gc();
        long startTime =System.currentTimeMillis();
        long startMem=r.totalMemory();
        excute_Dijistra();
        long endMem=r.freeMemory();
        long endTime=System.currentTimeMillis();
        runTime=endTime-startTime;
        runMem=startMem-endMem;
        di_Interface.runTime=runTime;
        di_Interface.runMem=runMem;
        return di_Interface;
        
    }


    public void copy(int a[][], int b[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[i][j] = a[i][j];
            }
        }
    }

    public void copy(int a[], int b[]) {
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
    }
}
