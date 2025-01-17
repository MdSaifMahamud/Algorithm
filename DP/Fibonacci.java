// package DP;
import java.util.*;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();     // we need nth fibonacci number
        int[] arr=new int[n+1];
        Arrays.fill(arr,-1);
        arr[0]=0;
        arr[1]=1;
        System.out.println(fibo3(n));
        System.out.println(fibo1(n));
        System.out.println(fibo2(arr, n));
        System.out.println(fibo4(n));
    }
    public static int fibo1(int n){     // good enough  // DP approches  // Always use previous data
        int[] arr=new int[n+1];         // bottom up approch
        Arrays.fill(arr,-1);
        arr[0]=0;
        arr[1]=1;
        for(int i=2;i<=n;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[n];
    }
    
    public static int fibo2(int[] arr,int n){ // dp with recursive approches  // recursion + memoization
        if(n==1 || n==0){               // top down approches
            return n;
        }
        if(arr[n]!=-1){
            return arr[n];
        }
        arr[n]=fibo2(arr, n-1)+fibo2(arr, n-2);
        return arr[n];

    }
    public static int  fibo3(int n){        // bad approch
        if(n==1 || n==0){
            return n;
        }else{
            return fibo3(n-1)+fibo3(n-2);
        }
    }
    public static int fibo4(int n){         // bottom up approches
        int prev1=1,prev2=0;        //   best space and time optimization
        int curr=0;
        for(int i=2;i<=n;i++){
            curr=prev1+prev2;
           prev2=prev1;
           prev1=curr;
        }
        return curr;
    }

}
