// package Greedy;
import java.util.*;

class Object{
    double number;
    double tot_profit;
    double perUnitProfit;
    Object(double number,double tot_profit,double perUnitProfit){
        this.tot_profit=tot_profit;
        this.number=number;
        this.perUnitProfit=perUnitProfit;
    }
}
public class Knapsack {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Object>lst=new ArrayList<>();
        for(int i=0;i<n;i++){
            double x=sc.nextInt();
            double y=sc.nextInt();
            lst.add(new Object(y, x, x/y));
        }
        Collections.sort(lst,Comparator.comparingDouble(e->e.perUnitProfit));
        //Collections.sort(EdgeList,Comparator.comparingInt(e -> e.weight));
        int need=sc.nextInt();
        int i=lst.size()-1;
        int maxProf=0;
        while(need!=0 || i>=0){
            Object obj=lst.get(i);
            // System.out.println(obj.perUnitProfit+" "+obj.number);
            if(need<=obj.number){
                maxProf+=(need*obj.perUnitProfit);
                need=0;
                i--;
            }else{
                i--;
                need-=obj.number;
                maxProf+=(obj.number*obj.perUnitProfit);
            }
        }
        System.out.println("Maximum possible Profit : "+maxProf);
    }
}
