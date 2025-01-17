import java.util.*;
public class Rod_Cutting {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] price=new int[n+1];
        Arrays.fill(price,0);
        for(int i=1;i<=n;i++){
            price[i]=sc.nextInt();
        }
        int[][] s=new int[n+1][n+1];
        // Arrays.fill(s,0);
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0){
                    continue;
                }else if(i>j){
                    s[i][j]=s[i-1][j];
                }else{
                    s[i][j]=Math.max(s[i-1][j],price[i]+s[i][j-i]);
                }
            }
        }
        // for(int i=0;i<=n;i++){
        //     for(int j=0;j<=n;j++){
        //         System.out.print(s[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        System.out.println("Maximum Profit is "+s[n][n]);
        HashMap<Integer,Integer> m=new HashMap<>();
        for(int i=n,j=n;s[i][j]!=0;i--){
            if(s[i-1][j]==s[i][j]){
                // i--;
                continue;
            }else{
                
                j=j-i;
                m.put(i, price[i]);
            }
        }
        System.out.println(m.size());
        for(Integer i:m.keySet()){
            System.out.println(i+"->"+m.get(i));
        }
    }
}
