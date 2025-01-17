import java.util.*;
public class LCS{
 public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    // int n=sc.nextInt();
    String a=sc.nextLine();
    String b=sc.nextLine();
    System.out.println(LCS1(a, b, 0, 0));
    LCS2(a, b);
 }  
 public static int LCS1(String a,String b,int i,int j){
    if(i>=a.length() || j>=b.length()){
        return 0;
    }
    else if(a.charAt(i)==b.charAt(j)){
        return 1+LCS1(a, b, i+1, j+1);
    }
    else{
        return Math.max(LCS1(a, b, i+1, j), LCS1(a, b, i, j+1));
    }
 } 
 public static void LCS2(String a,String b){
    int m=a.length();
    int n=b.length();
    int[][] dp=new int[m+1][n+1];
    for(int i=1;i<=m;i++){
        for(int j=1;j<=n;j++){
            if(a.charAt(i-1)==b.charAt(j-1)){
                dp[i][j]=dp[i-1][j-1]+1;
            }else{
                dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    System.out.println(dp[m][n]);
    String ans="";
    int i=m,j=n;
    while (i>0 && j>0){
        if(a.charAt(i-1)==b.charAt(j-1)){
            ans+=a.charAt(i-1);
            i--;
            j--;
        }else if(dp[i-1][j]>dp[i][j-1]){
            i--;
        }else{
            j--;
        }
    }
    System.out.println(rev(ans));
 }
 public static String rev(String s){
    String ans="";
    for(int i=s.length()-1;i>=0;i--){
        ans+=s.charAt(i);
    }
    return ans;
 }
}
