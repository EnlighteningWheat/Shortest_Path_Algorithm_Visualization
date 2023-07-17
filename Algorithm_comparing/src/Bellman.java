import java.util.Arrays;

/*Bellman算法是每轮遍历每个弧，轮数k代表他可以找到每两个节点之间k条弧及其以下的最短距离
* 所以我们用一个用一个三维的数组来记录遍历每个弧
* 同时还要返回一个带有时间步的dist数组来记录最短路径
* 为了同步两个表我们要用一个一维数组来表示时间，我们用将访问一条弧看作一个时间单位，其中1表示没有变化，2表示dist发生了变化
* */
public class Bellman {
    // 定义一个值来表示无穷大
    final int inf = 999;
   //运行时间和运行内存
   long runTime;
   long runMem;
    // 先定义一个接口
    AlgorithmInterface interface1;
    int VertexNum;
    int[][] adjacencyMatrix;
    int[] dist;
    //定义一个edge来储存所有边
    int[][] egdes;

    // 重载构造函数
    public Bellman(int[][] B_adjacencyMatrix, int vertexNum) {
        this.VertexNum=vertexNum;
        adjacencyMatrix = B_adjacencyMatrix;
        interface1 = new AlgorithmInterface(vertexNum);
        dist = new int[vertexNum];
        egdes=new int[100][3];
        
        
    }

    public int edgeNum() {
        // 定义一个值来储存边的数量
        int E = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] != inf) {  
                    egdes[E][0]=i;
                    egdes[E][1]=j;
                    egdes[E][2]=adjacencyMatrix[i][j];
                    E += 1;
                }
            }
        }
        System.out.println("第零条边"+egdes[1][0]+egdes[1][1]);
        
        return E;

    }

    // bellman算法主体
    public void excute_bellmanFord() {
        // 先获取图边的数量
        int E = edgeNum();
        System.out.print("E");
        System.out.print(E);


        // 定义时间
        int be_second = 0;
        int distNum=0;
        for (int i = 0; i < VertexNum; i++) {
            Arrays.fill(dist,inf);// 初始化节点为无穷大
        }
        dist[0] = 0;
        for (int i = 1; i < VertexNum; i++) {
            int egdeNum=0;
            for (int j = 0; j < E; j++) {
                int u=egdes[j][0];
                int v=egdes[j][1];
                int weight=egdes[j][2];
                System.out.print("dist");
                for (int k = 0; k < dist.length; k++) {
                    System.out.print(dist[k]);
                }
                if (dist[u] != inf
                        && dist[v] > dist[u] + weight) {
                    System.out.print("进来了");
                    dist[v] = dist[u] + weight;
                    // 将dist的存起来
                    copy(dist, interface1.be_dist[distNum]);
                    distNum++;
                    for (int k = 0; k < dist.length; k++) {
                        System.out.print("bellman"+interface1.be_dist[distNum][k]);
                    }
                    interface1.be_time[be_second] = 2;
                    be_second++;
                } else {
                    interface1.be_time[be_second] = 1;
                    be_second++;
                }
                interface1.be_edge[i][egdeNum][0] = u;
                interface1.be_edge[i][egdeNum][1] = v;
                egdeNum++;
            }
        }
    }

    // 返回接口实例
    public AlgorithmInterface returnResult() {
        Runtime r=Runtime.getRuntime();
        r.gc();
        long startTime =System.currentTimeMillis();
        long startMem=r.totalMemory();
        excute_bellmanFord();
        long endMem=r.freeMemory();
        long endTime=System.currentTimeMillis();
        runTime=endTime-startTime;
        runMem=startMem-endMem;
        interface1.runTime=runTime;
        interface1.runMem=runMem;
        
        return interface1;
    }

    public void copy(int a[][],int b[][]){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[i][j]=a[i][j];
            }
        }
    }
    public void copy(int a[],int b[]){
        for (int i = 0; i < a.length; i++) {
            b[i]=a[i];
        }
    }
}
