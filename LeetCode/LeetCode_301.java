import java.util.*;
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String>ans=new ArrayList<>();
        if(s.length()==0 || s==null ||s.length()==1){
            return ans;
        } 
        Set<String>visited=new HashSet<>();
        Queue<String>queue=new LinkedList<>();
        queue.add(s);
        boolean f=false;
        while(!queue.isEmpty()){
            String st=queue.poll();
            if(isValid(st)){
                ans.add(st);
                f=true;
            }
            if(f){continue;}
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=')' && s.charAt(i)!='('){
                    continue;
                }
                String t=s.substring(0, i)+s.substring(i+1);
                if(!visited.contains(t)){
                    queue.offer(t);
                    visited.add(t);
                }
            }
        }  
        return ans;
    }
    public boolean isValid(String s){
        int cnt=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                cnt++;
            }else if(s.charAt(i)==')' && cnt--==0){
                return false;
            }
        }
        return cnt==0;
    }
}
public class LeetCode_301 {
    
}
