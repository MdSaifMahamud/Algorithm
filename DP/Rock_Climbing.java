import java.util.*;
public class Rock_Climbing {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[][] cost=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                cost[i][j]=sc.nextInt();
            }
        }
        int[][] ways=new int[n+1][m+2];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m+1;j++){
                if(i==0){
                    ways[i][j]=0;
                }
                if(j==0 || j==m+1){
                    ways[i][j]=1000000;
                }
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                ways[i][j]=Math.min(ways[i-1][j-1],Math.min(ways[i-1][j],ways[i-1][j+1]))+cost[i][j];
            }
        }
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m+1;j++){
                if(ways[i][j]==1000000){
                    System.out.print("Inf ");
                }else{
                    System.out.print(ways[i][j]+" ");
                }
            }
            System.out.println();
        }


    }
}
