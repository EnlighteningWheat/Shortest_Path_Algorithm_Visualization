import java.util.LinkedList;
import java.util.Queue;
// SPFA算法接口
/*
 * SPFA算法的是一个用队列优化的bellman算法，但仍旧有当前访问的点
 * 我们需要一个一维数组来记录当前访问节点的变化visit,当前访问节点的变化，更改当前访问节点算作一时间单位
 * 我们需要一个二维数组来记录队列每次的更改queue（队列的更改和节点的更改同时进行，在一个时间单位里面）
 * 我们需要一个二维数组来记录最短路径表的更改dist
 * 我们需要一个二维数组来记录遍历的弧的变化edge
 * 为了同步上面所有的值，我们需要一个一维数组来表示时间，其中每访问一条弧算作一时间单位，其中1表示访问弧，2表示最短路径表变化和弧，3表示visit和queue变化,4表示queue变化
 */

public class SPFA {
    // 定义一个值来表示无穷大
    final int inf = 999;
    //运行时间和运行内存
    long runTime;
    long runMem;

    public int[][] adjacencyMatrix;// 邻接表
    int VertexNum;
    int[] sp_visit;
    int[] sp_dist;
    // 设置一个数组来记录队列里面的情况
    int[] Array_queue;

    public AlgorithmInterface sp_Interface;

    public SPFA(int[][] sp_adjacencyMatrix, int vertexnum) {
        
        adjacencyMatrix = sp_adjacencyMatrix;
        VertexNum = vertexnum;
        sp_Interface=new AlgorithmInterface(vertexnum);
        sp_visit = new int[vertexnum];
        sp_dist = new int[vertexnum];
        Array_queue=new int[vertexnum];
        for (int i = 0; i <vertexnum ; i++) {
            Array_queue[i]=inf;
        }
    }

    public void excute_SPFA() {
        //定义一个时间
        int sp_time=0;
        int queue_num=0;
        int visit_num=0;
        int dist_num=0;
        int edge_num=0;


        int source = 0;// 如果这里source不是默认为0点，就要在上面加入int source的输入并且执行下面一条代码
        // this.source=source;
        // 初始化
        System.out.print(VertexNum);
        for (int i = 0; i < VertexNum; i++) {
            sp_visit[i] = 0;
            sp_dist[i] = 999;
        }
        sp_dist[source] = 0;
        // 为了方面更改起点这里就用source表示了
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);// 把起点加入队列
        arrayQueue_push(source);
        copy( sp_dist,sp_Interface.sp_dist[dist_num]);
        dist_num++;
        copy(Array_queue, sp_Interface.sp_queue[queue_num]);
        queue_num++;
        sp_visit[0]=1;
        sp_Interface.sp_time[sp_time]=4;
        sp_time++;
        while (!queue.isEmpty()) {
           
            int current = queue.poll();// 出队,开始计算
            //更改时间
            sp_Interface.sp_time[sp_time]=3;
            sp_time++;
            //更改访问的节点
            sp_Interface.sp_visit[visit_num] = current;
            visit_num++;
             //接口内储存队列数据
            arrayQueue_pop();
            copy(Array_queue, sp_Interface.sp_queue[queue_num]);
            queue_num++;
            for (int i = 0; i < Array_queue.length; i++) {
                System.out.print("queue"+Array_queue[i]);
            }
            
            sp_visit[current] = 0;
            for (int n = 1; n < VertexNum; n++) {
                
                if (adjacencyMatrix[current][n] != 999) {
                sp_Interface.sp_edge[edge_num][0]=current;
                sp_Interface.sp_edge[edge_num][1]=n;
                edge_num++;
                sp_Interface.sp_time[sp_time]=1;
                int distance = adjacencyMatrix[current][n];
                    if (sp_dist[n] > sp_dist[current] + distance) {
                        sp_dist[n] = sp_dist[current] + distance;
                        //更新接口内的dist表
                        copy(sp_dist,sp_Interface.sp_dist[dist_num]);
                        dist_num++;
                        //更新时间
                        sp_Interface.sp_time[sp_time]=2;
                        sp_time++;
                        if (sp_visit[n] == 0) {
                            queue.offer(n);// 更新之后入队
                            //更新接口里面的队列表
                            arrayQueue_push(n);
                            copy(Array_queue,sp_Interface.sp_queue[queue_num]);
                            queue_num++;
                            sp_visit[n] = 1;
                            sp_Interface.sp_time[sp_time]=4;
                            sp_time++;
                        }
                    }else{
                        sp_time++;
                    }
                    ;
                }
                
            }
        }
    }

    public AlgorithmInterface returnResult() {
        Runtime r=Runtime.getRuntime();
        r.gc();
        long startTime =System.currentTimeMillis();
        long startMem=r.totalMemory();
        excute_SPFA();
        long endMem=r.freeMemory();
        long endTime=System.currentTimeMillis();
        runTime=endTime-startTime;
        runMem=startMem-endMem;
        sp_Interface.runTime=runTime;
        sp_Interface.runMem=runMem;
      
        return sp_Interface;
    }

    // 为了实现记录队列中每次变化，设置一个方法
    public void arrayQueue_push(int n){
        for (int i = 0; i < VertexNum; i++) {
            if(Array_queue[i]==inf){
                Array_queue[i]=n;
                break;
            }
        }
    }
    public void arrayQueue_pop() {
        int[] temp=new int[VertexNum];
        for (int i = 0; i <VertexNum ; i++) {
            temp[i]=inf;
        }
        for (int i = 0; i < VertexNum-1; i++) {
            temp[i]=Array_queue[i+1];
        }
       copy(temp, Array_queue);
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