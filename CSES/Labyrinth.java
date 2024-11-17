import java.util.*;
public class Main{
    static class Edge{
        int x,y;
        Edge(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        sc.nextLine();
        int x=0,y=0;
        char[][] maze=new char[1001][1001];
        char[][] br=new char[1001][1001];
        for(int i=0;i<n;i++){
            String line=sc.nextLine();
            for(int j=0;j<m;j++){
                maze[i][j]=line.charAt(j);
                if(maze[i][j]=='A'){
                    x=i;
                    y=j;
                }
            }
        }
        boolean[][] visited=new boolean[1001][1001];
        List<Character>path=new LinkedList<>();
        if(bfs(x, y, maze, visited, path, n, m, br)){
            System.out.println("YES");
            System.out.println(path.size());
            for(int i=path.size()-1;i>=0;i--){
                // System.out.print(ch);
                System.out.print(path.get(i));
            }
            System.out.println();
            
        }else{
            System.out.println("NO");
        }
    }
    private static boolean isValid(int i,int j,int n,int m,char[][] maze,boolean[][] visited){
        if(i<0 || i>=n || j<0 || j>=m ){
            return false;
        }
        if(maze[i][j]=='#' || visited[i][j]){
            return false;
        }
        return true;
    }
    private  static boolean bfs(int x,int y,char[][] maze,boolean[][] visited,List<Character>path,int n,int m,char[][] br){
        Queue<Edge>q=new LinkedList<>();
        String s="";
        visited[x][y]=true;
        q.offer(new Edge(x, y));
        while(!q.isEmpty()){
            Edge ed=q.peek();
            int a=ed.x;
            int b=ed.y;
            q.poll();
            if(maze[a][b]=='B'){
                // something have to do
                while(true){
                    path.add(br[a][b]);
                    if(path.get(path.size()-1)=='L'){
                        b++;
                    }
                    if(path.get(path.size()-1)=='R'){
                        b--;
                    }
                    if(path.get(path.size()-1)=='U'){
                        a++;
                    }
                    if(path.get(path.size()-1)=='D'){
                        a--;
                    }
                    if(a==x && b==y){
                        break;
                    }
                }
                System.out.println(s);
                return true;
            }
            if(isValid(a, b-1, n, m, maze, visited)){
                q.offer(new Edge(a, b-1));
                visited[a][b-1]=true;
                br[a][b-1]='L';
                s+='L';
            }
            if(isValid(a, b+1, n, m, maze, visited)){
                q.offer(new Edge(a, b+1));
                visited[a][b+1]=true;
                br[a][b+1]='R';
                s+='R';
            }
            if(isValid(a+1, b, n, m, maze, visited)){
                q.offer(new Edge(a+1, b));
                visited[a+1][b]=true;
                br[a+1][b]='D';
                s+='D';
            }
            if(isValid(a-1, b, n, m, maze, visited)){
                q.offer(new Edge(a-1, b));
                visited[a-1][b]=true;
                br[a-1][b]='U';
                s+='U';
            }
           
        }
        

        return false;
    }

}

