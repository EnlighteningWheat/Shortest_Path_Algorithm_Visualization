import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

//自定义面板 输出图

public class PuttingGraph extends JPanel implements Runnable {
    //定义一个颜色矩阵，0是红色，1是黄色，2是黄色
    int[][] edgecolorMatrix;
    int[] vexColorlist;
    int[] distColorlist;
    String[] distNum;
    int[] dists=new int[10];
    // 声明节点的容量
    final int Number = 10;

    //准备所用到的图像
    BufferedImage background;

    // 定义画图矩阵的大小
    final int CLENGTH = 10;
    final int CWIDTH = 10;

    // 创建一个邻接表和一个画图矩阵
    int[][] AdjacencyMatrix = new int[Number][Number];
    int[][] PaintingMatrix = new int[CLENGTH][CWIDTH];

    // 声明一个节点表和一个值用来储存节点个数
    String[] Vertexs = new String[Number];
    int VertexNum;

    // 储存边个数
    int EdgeNum;

    public Color[] edgeColors = new Color[2];
    public Color[] vexColores=new Color[3];
    public Color[] distColors=new Color[2];

    // 两个坐标定位点
    public int v1 = -1, v2 = -1;



    //接口
    AlgorithmInterface P_interface;
    int edgeNum;


    //
    String sign;
    //图像
    BufferedImage backGround;

    //运行时间和运行内存
    long runTime;
    long runMem;
    // 构造方法 - 传入图结构
    public PuttingGraph(int[][] adjacencyMatrix, String[] vertex, int vertexNum) {
        try {
            backGround=ImageIO.read(new File("./lib/Background1.png"));
          } catch (Exception e) {
            e.printStackTrace();
          }
          edgecolorMatrix=new int[10][10];
          vexColorlist=new int[10];
          distColorlist=new int[10];
          distNum=new String[10];
          distNtoS(dists);
        //颜色矩阵
        
        //设置颜色
        System.out.print("Color"+vertexNum);
        edgeColors[0]=Color.white;
        edgeColors[1]=Color.red;
        vexColores[0]=Color.green;
        vexColores[1]=Color.YELLOW;
        vexColores[2]=Color.BLUE;
        distColors[0]=Color.white;
        distColors[1]=Color.red;
        this.AdjacencyMatrix = adjacencyMatrix;
        this.Vertexs = vertex;
        this.VertexNum = vertexNum;

        for (int i = 0; i < Number; i++) {
            for (int j = 0; j < Number; j++) {
                if (AdjacencyMatrix[i][j] != 0) {
                    EdgeNum += 1;
                }
            }
        }



    }
    public void read(int[][] adjacencyMatrix, String[] vertex, int vertexNum){
        this.AdjacencyMatrix = adjacencyMatrix;
        this.Vertexs = vertex;
        this.VertexNum = vertexNum;
        

        for (int i = 0; i < Number; i++) {
            for (int j = 0; j < Number; j++) {
                if (AdjacencyMatrix[i][j] != 0) {
                    EdgeNum += 1;
                }
            }
        }



    }

    // 重写paint方法
    @Override
    public void paint(Graphics g) {
        // 调用父类的paint，一定要写
        super.paint(g);
        

        g.drawImage(backGround, 0,0,this.getWidth(), this.getHeight(), this);

        // 改变面板中的字体大小
        g.setFont(g.getFont().deriveFont(20f));
        // 引入位置参数Map
        GraphicalParam graphicalParam = new GraphicalParam();

        // 画边
        for (int i = 0; i < VertexNum; i++) {
            // v1,v2用来临时记录边的邻接顶点
            int v1 = 0, v2 = 0;

            // 获取当前边邻接顶点的位置下标
            for (int j = 0; j < VertexNum; j++) {
                if (AdjacencyMatrix[i][j] != 0 && AdjacencyMatrix[i][j] != 999
                        && AdjacencyMatrix[i][j] != Integer.MAX_VALUE) {
                    v1 = i;
                    v2 = j;
                    // 获取每条边的对应的位置参数信息
                    String str1 = "(" + String.valueOf(v1) + "," + String.valueOf(v2) + ")";
                    String str2 = "(" + String.valueOf(v2) + "," + String.valueOf(v1) + ")";
                    int[] elocation = (graphicalParam.eMap.get(str1) != null) ? graphicalParam.eMap.get(str1)
                            : graphicalParam.eMap.get(str2);
                    // 边的位置坐标集合
                    g.setColor(edgeColors[edgecolorMatrix[i][j]]);

                    // 画边
                    g.drawLine(elocation[0], elocation[1], elocation[2], elocation[3]);
                    // 画边上的标注
                    g.drawString(String.valueOf(AdjacencyMatrix[i][j]), elocation[4], elocation[5]);
                }
            }
        }
        // 画顶点
        for (int i = 0; i < VertexNum; i++) {
            // 获取每个顶点值
            String vertex = Vertexs[i];
            // 获取每个顶点的位置参数
            int[] vlocation = graphicalParam.vMap.get(i).clone();

            // 控制后改变颜色
            g.setColor(vexColores[vexColorlist[i]]);
            g.fillOval(vlocation[2], vlocation[3], 40, 40);
            g.setColor(Color.black);
            g.drawString(vertex, vlocation[0], vlocation[1]);
        }

        // 画dist表
        for (int i=0;i<VertexNum;i++) {
             String vertex = Vertexs[i];
             // 获取每个顶点的位置参数
             int[] dlocation = graphicalParam.dMap.get(i).clone();
            
            // 控制后改变颜色
            g.setColor(distColors[distColorlist[i]]);
             g.drawString(vertex, dlocation[0], dlocation[1]);
            g.setColor(vexColores[vexColorlist[i]]);
            g.drawString(distNum[i], dlocation[2], dlocation[3]);
             }
            g.setColor(Color.white);
             g.drawString("运行时间：", 850,400);
             g.drawString(String.valueOf(runTime), 1000,400);
             g.drawString("运行内存：", 850,450);
             g.drawString(String.valueOf(runMem), 1000,450);

    }

    // 封装画图的具体方法
    /**
     * @param g
     */

    public void edgeToRed(int i,int j){
        edgecolorMatrix[i][j]=1;
    }
    public void edgeToblack(int i,int j){
        edgecolorMatrix[i][j]=0;
    }
    public void edgeToAllBlack(){
        for (int i = 0; i < edgecolorMatrix.length; i++) {
            for (int j = 0; j < edgecolorMatrix.length; j++) {
                edgecolorMatrix[i][j]=0;
            }
        }
    }
    public void vexToGreen(int i){
        vexColorlist[i]=0;
    }
    public void vexToYellow(int i){
        vexColorlist[i]=1;
    }
    public void vexToBlue(int i){
        vexColorlist[i]=2;
    }
    public void vexToAllGreen(){
        Arrays.fill(vexColorlist,0);
    }
    public void vexToAllYellow(){
        Arrays.fill(vexColorlist,1);
    }
    public void readInterface(AlgorithmInterface interface1){
        P_interface=interface1;
       }
    public void readEdgeNum(int b_edgeNum){
        edgeNum=b_edgeNum;}
    public void readSign(String pSign){
        sign=pSign;
    }
    public void distVexToRed(int[] queue_Num){
            distVexToWhite();
            for (int j = 0; j < queue_Num.length; j++) {
                if(queue_Num[j]!=999){
                    distColorlist[queue_Num[j]]=1;
                }      
             }
    }  
    public void distVexToWhite(){
            Arrays.fill(distColorlist,0);
    }
    public void readRuntime(long runt){
        runTime=runt;
    }
     public void run(){
        if(sign.equals("bellman")){
            bellman();
        }else if(sign.equals("spfa")){
            SPFA();
        }else if(sign.equals("ok")){
            vexToAllGreen();
            edgeToAllBlack();
            distVexToWhite();
            distNtoS(dists);
            runMem=0;
            runTime=0;
            repaint();
        }else if(sign.equals("dijistra")){
            Dijistra();
        }
      

     }
    public void bellman() {
    runMem=P_interface.runMem;
    runTime=P_interface.runTime;
    int edge_num=0;
        int time=0;
        int k=1;
        int dist_num=0;
            
            while(true){
              
              if(k!=time/edgeNum+1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 k=time/edgeNum+1;
                 edge_num=0;
                 edgeToAllBlack();
                 repaint();
              }
              
              if(P_interface.be_time[time]==0){
                //0表示到达时间终点，停止展示
                break;
              }else if(P_interface.be_time[time]==1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time++;
                edgeToRed(P_interface.be_edge[k][edge_num][0],P_interface.be_edge[k][edge_num][1]);
                System.out.print(k);
                System.out.print(P_interface.be_edge[k][edge_num][0]);
                System.out.print(P_interface.be_edge[k][edge_num][1]);
                edge_num+=1;
                repaint();
                
               
              }else if(P_interface.be_time[time]==2){
                //2表示dist表变化了，调用用绘图方法更改图上dist表
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                time++;
                distNtoS(P_interface.be_dist[dist_num]);
                dist_num++;
                edgeToRed(P_interface.be_edge[k][edge_num][0],P_interface.be_edge[k][edge_num][1]);
                System.out.print(k);
                System.out.print(P_interface.be_edge[k][edge_num][0]);
                System.out.print(P_interface.be_edge[k][edge_num][1]);
                edge_num+=1;
                repaint();
                

              }
              System.out.print("一单位时间");

            }
            System.out.print("bellman 完成");
  }
   public void distNtoS(int[] dist){
            for (int i = 0; i < dist.length; i++) {
                if(dist[i]!=999&&dist[i]!=0){
                    distNum[i]=String.valueOf(dist[i]);
                }else{
                    distNum[i]="inf";
                }
                
            }
   }
    public void pathTOred(){

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
  public void Dijistra(){
    runMem=P_interface.runMem;
    runTime=P_interface.runTime;
    int time=0;
    int edge_num=0;
    int visit_num=0;
    int dist_num=0;
    while(true){
        if(P_interface.dj_time[time]==0){
           vexToAllYellow();
            repaint();
            //停止
            break;
        }else if(P_interface.dj_time[time]==1){
            //弧改变
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            edgeToRed(P_interface.dj_edge[edge_num][0],P_interface.dj_edge[edge_num][1]);
            edge_num++;
            repaint();
            time++;

        }else if(P_interface.dj_time[time]==2){
            //最短路径表和弧改变
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("dist");
            distNtoS(P_interface.dj_dist[dist_num]);
            dist_num++;
            edgeToRed(P_interface.dj_edge[edge_num][0],P_interface.dj_edge[edge_num][1]);
            edge_num++;
            repaint();
            time++;
        }else if(P_interface.dj_time[time]==3){
            //访问节点改变
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(visit_num!=0){
                vexToYellow(P_interface.dj_visit[visit_num-1]);
            }
            vexToBlue(P_interface.dj_visit[visit_num]);
            visit_num++;
            repaint();
            time++;
        }
        System.out.print("一单位时间");
    }
    System.out.print("Dijistra 完成");

  }
  public void SPFA(){
    //测试
    for (int i = 0; i < P_interface.sp_queue.length; i++) {
        for (int j = 0; j < P_interface.sp_queue[1].length; j++) {
           System.out.print(" "+P_interface.sp_queue[i][j]);
        }
        System.out.println();
    }
    runMem=P_interface.runMem;
    runTime=P_interface.runTime;
    int time=0;
    int edge_num=0;
    int visit_num=0;
    int queue_Num=0;
    int dist_num=0;
    while(true){
        if(P_interface.sp_time[time]==0){


            //停止
            break;
        }else if(P_interface.sp_time[time]==1){
            //访问弧
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            edgeToRed(P_interface.sp_edge[edge_num][0],P_interface.sp_edge[edge_num][1]);
            System.out.print(P_interface.sp_edge[edge_num][0]);
            System.out.print(P_interface.sp_edge[edge_num][1]);
            edge_num++;
            repaint();


        }else if(P_interface.sp_time[time]==2){
            //访问最短路径和弧
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            distNtoS(P_interface.sp_dist[dist_num]);
            dist_num++;
            edgeToRed(P_interface.sp_edge[edge_num][0],P_interface.sp_edge[edge_num][1]);
            System.out.print(P_interface.sp_edge[edge_num][0]);
            System.out.print(P_interface.sp_edge[edge_num][1]);
            edge_num++;
            repaint();

        }else if(P_interface.sp_time[time]==3){
            //访问visit表
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            distVexToRed(P_interface.sp_queue[queue_Num]);
            queue_Num++;
            vexToYellow(P_interface.sp_visit[visit_num]);
            visit_num++;
            repaint();

        }else if(P_interface.sp_time[time]==4){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
            distVexToRed(P_interface.sp_queue[queue_Num]);
            queue_Num++;
            repaint();
        }
       System.out.print("一单位时间");

    }
    System.out.print("spfa 完成");
  }
}
