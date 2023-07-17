public class ReadMassege {
    final int inf=999;
    public int read(String GraphMassege,String[] Vertex,int[][] AdjacencyMatrix){
        int n=0;
        int len=0;
        
        String[] Strings=GraphMassege.split("\n");
        int sign=Strings.length;
        
        while(true){
            String temp1=Strings[n].substring(0, 6);
            String temp2=Strings[n].substring(0, 4);
            if(temp1.equals("vertex")){
            Vertex[len]=Strings[n].substring(7, 8);
            n+=1;
            len+=1;
            }else if(temp2.equals("edge")){
            String v1=Strings[n].substring(5, 6);
            String v2=Strings[n].substring(7, 8);
            int n1=LocateVertex(v1, Vertex);
            int n2=LocateVertex(v2, Vertex);
            String Power=Strings[n].substring(9,Strings[n].length());
            int power=Integer.parseInt(Power);
            AdjacencyMatrix[n1][n2]=power;
            n+=1;
            }
            if(n==sign){
                break;
            }
            
        } 
        return len;
    }
    //寻找vertex的位置（这里可能溢出）
    public int LocateVertex(String vertex,String[] Vertex){
        int n=0;
        while(true){
            if(Vertex[n].equals(vertex)){
                return n;
            }
            n+=1;

        }
    }
}
