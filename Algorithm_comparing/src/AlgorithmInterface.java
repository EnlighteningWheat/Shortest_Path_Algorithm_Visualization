public class AlgorithmInterface {
    // 设置一个数来根据节点的多少更改接口的大小
    final int cpacity = 200;
    // 储存节点个数
    int VertexNum;
    // 声明两个值用于储存运算时间和运算内存
    public double cuntime;
    public double storage;
    // 迪杰斯特拉算法接口
    /*
     * 为了实现辨别已访问节点，正在访问节点，还有未访问节点，我们借助visit表
     * visit表中1代表已经访问，2代表正在访问，0代表未访问节点。
     * 为了记录每次的最短路径长的变化，我们采用dist表来记录最短路径长度。
     * 为了记录每个当前访问节点判断他的一个联通的节点的弧长，我们用一个二维数组来记录，该数组中每一行储存（当前访问节点，与他联通的那个节点）
     * 为了使得visit表和dist表同步，我们将每个当前访问节点判断他的一个联通的节点是否能构成最短路径作为一单位时间，将visit表的更改也当作一单位时间。
     * 我们定义一个数组用来记录时间，和该时间visit表或者dist表是否变化。其中1代表不改变，2代表dist表改变，3代表visit表改变,0代表未储存。
     */
    public int[][] dj_edge;
    public int[] dj_time;
    public int[] dj_visit;
    public int[][] dj_dist;
   
    // Bellman算法接口
    /*
     * Bellman算法是每轮遍历每个弧，轮数k代表他可以找到每两个节点之间k条弧及其以下的最短距离
     * 所以我们用一个用一个三维的数组来记录遍历每个弧
     * 同时还要返回一个带有时间步的dist数组来记录最短路径
     * 为了同步dist表和path表我们要用一个一维数组来表示时间，我们用将访问一条弧看作一个时间单位，其中1表示没有变化，2表示dist发生了变化
     * 
     */
    // 第一维表示轮次，第二维表示弧，第三维度来记录弧的起点和终点
    public int[][][] be_edge;
    public int[][] be_dist;
    public int[] be_time;

    // SPFA算法接口
    /*
     * SPFA算法的是一个用队列优化的bellman算法，但仍旧有当前访问的点
     * 我们需要一个一维数组来记录当前访问节点的变化visit,当前访问节点的变化，更改当前访问节点算作一时间单位
     * 我们需要一个二维数组来记录队列每次的更改queue（队列的更改和节点的更改同时进行，在一个时间单位里面）
     * 我们需要一个二维数组来记录最短路径表的更改dist
     * 我们需要一个二维数组来记录遍历的弧的变化edge
     * 为了同步上面所有的值，我们需要一个一维数组来表示时间，其中每访问一条弧算作一时间单位，其中1表示最短路径表变化，2表示visit和queue变化
     */
    public int[] sp_visit;
    public int[][] sp_queue;
    public int[][] sp_dist;
    public int[][] sp_edge;
    public int[] sp_time;

    //运行时间和运行内存
    long runTime;
    long runMem;

    public AlgorithmInterface(int vertexNum) {
        this.VertexNum=vertexNum;
        // 初始化dijstra的接口
        dj_time = new int[10 * cpacity];
        dj_visit = new int[cpacity];
        dj_edge = new int[10 * cpacity][2];
        dj_dist = new int[cpacity][vertexNum];

        // 初始化bellman的接口
        be_edge = new int[vertexNum][10 * cpacity][2];
        be_dist = new int[cpacity][vertexNum];
        be_time = new int[10 * cpacity];

        // 初始化SPFA的接口
        sp_visit = new int[vertexNum];
        sp_queue = new int[cpacity][vertexNum];
        sp_dist = new int[cpacity][vertexNum];
        sp_edge = new int[10 * cpacity][2];
        sp_time = new int[10 * cpacity];

    }

}
