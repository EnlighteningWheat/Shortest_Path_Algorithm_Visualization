import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;


/**
 * Painting
 */
public class GUI {
  
      //声明无限大数
      final int inf=999;
      //声明节点的容量
      final int Number=10;
      final int cpacity = 100;
      //定义画图矩阵的大小
      final int CLENGTH=10;
      final int CWIDTH=10;
      //创建一个邻接表和一个画图矩阵
      int[][] AdjacencyMatrix= new int[Number][Number];
      int[][] PaintingMatrix=new int[CLENGTH][CWIDTH];
      //声明一个节点表和一个值用来储存节点个数
      String[] Vertex=new String[Number];
      int VertexNum;
      
     
      //实例化一个接口
      AlgorithmInterface P_interface;
      //实例化三个个算法
      Dijistra dijistra;
      Bellman bellman;
      SPFA spfa;

      //声明图形化页面所需要的组件
      //先定义一个窗口
      private JFrame f = new JFrame("单源最短路径算法比较");
      PuttingGraph p1 = new PuttingGraph(AdjacencyMatrix, Vertex, VertexNum);
      JPanel panel=new JPanel();
      JPanel p = new JPanel();
      JTextArea pointOut = new JTextArea("vertex s\nvertex a\nvertex b\nvertex c\nvertex d\nvertex e\nvertex f\nvertex t\nedge s a 9\nedge s e 14\nedge s f 15\nedge a b 24\nedge b t 19\nedge b d 2\nedge c b 6\nedge c t 6\nedge d c 11\nedge d t 16\nedge e d 30\nedge e f 5\nedge e b 18\nedge f d 20\nedge f t 44");
      JButton DijstraButton = new JButton("Dijistra");
      JButton BellmanButton = new JButton("Bellman");
      JButton SpfaButton=new JButton("SPFA");
      JButton OkButtom=new JButton("OK");
      
      //重载构造函数
      public GUI(){
        for (int i = 0; i < AdjacencyMatrix.length; i++) {
            for (int j = 0; j < AdjacencyMatrix.length; j++) {
              AdjacencyMatrix[i][j]=inf;
            }
        }
      }



      //声明事件监听器
      ActionListener listener=new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (e.getActionCommand().equals("Dijistra")) {
            dijistra=new Dijistra(AdjacencyMatrix, VertexNum);
              P_interface=dijistra.returnResult();
              System.out.print("dij");
              p1.readInterface(P_interface);
              p1.readSign("dijistra");
              Thread t=new Thread(p1);
              t.start();
        }else if(e.getActionCommand().equals("Bellman")){
          bellman=new Bellman(AdjacencyMatrix, VertexNum);
          int edgeNum=bellman.edgeNum();
          P_interface=bellman.returnResult();
          p1.readEdgeNum(edgeNum);
          p1.readInterface(P_interface);
          p1.readSign("bellman");
          Thread t=new Thread(p1);

          t.start();
         
        }else if(e.getActionCommand().equals("SPFA")){
         spfa=new SPFA(AdjacencyMatrix, VertexNum);
        //获取spfa算法的结果
         P_interface=spfa.returnResult();
         p1.readInterface(P_interface);
         p1.readSign("spfa");
         Thread t=new Thread(p1);
         t.start();
         panel.repaint();
        
        }else if(e.getActionCommand().equals("OK")){
            String GraphMassege=pointOut.getText();
            System.out.println(GraphMassege);
            ReadMassege readMassege=new ReadMassege();
            VertexNum=readMassege.read(GraphMassege, Vertex, AdjacencyMatrix);
            for (int i = 0; i < VertexNum; i++) {
              System.out.println("this");
              System.out.println(Vertex[i]);
            }
            System.out.println(VertexNum);
            p1.read(AdjacencyMatrix, Vertex, VertexNum);
            p1.readSign("ok");
            f.repaint();
            p1.run();
            
            }
        }
        
      };
      

      //组装视图
      public void init(){
      
        //实例化四个算法

        
        
        //给邻接表赋值
        for (int i = 0; i < AdjacencyMatrix.length; i++) {
          for (int j = 0; j < AdjacencyMatrix.length; j++) {
            AdjacencyMatrix[i][j]=inf;
          }
        }
        
    
        //button的事件监听
        DijstraButton.addActionListener(listener);
        BellmanButton.addActionListener(listener);
        SpfaButton.addActionListener(listener);
        OkButtom.addActionListener(listener);

        //组装窗口左边
       
        p1.setPreferredSize(new Dimension(1300, 1000));
        Container c = f.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(p1);
        //组装窗体右边
        pointOut.setEditable(true);
        Dimension ButtonDim = new Dimension(150, 40);
        Dimension TextDim=new Dimension(150, 400);
        Font fn = new Font("宋体", Font.BOLD, 14);
        pointOut.setPreferredSize(TextDim);
        pointOut.setFont(fn);
        
        DijstraButton.setPreferredSize(ButtonDim);
        BellmanButton.setPreferredSize(ButtonDim);
        SpfaButton.setPreferredSize(ButtonDim);
        OkButtom.setPreferredSize(ButtonDim);
        p.setLayout(new FlowLayout());
        p.setPreferredSize(new Dimension(150, 1000));
        p.add(DijstraButton);
        p.add(BellmanButton);
        p.add(SpfaButton);
        p.add(pointOut);
        p.add(OkButtom);
        c.add(p);
        f.pack();
        f.setVisible(true);
      } 
      public static void main(String[] args) {
        GUI a=new GUI();
        a.init();
    }


    
}
