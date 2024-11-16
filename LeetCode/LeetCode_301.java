import java.util.*;
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans=new ArrayList<>();
        if(s.length()==0 || s==null){
            ans.add("");
            return ans;
        }
        Set<String> visited=new HashSet<>();
        Queue<String>q=new LinkedList<>();
        q.add(s);
        boolean flag=false;
        while(!q.isEmpty()){
            String str=q.poll();
            if(isValid(str)){
                ans.add(str);
                flag=true;
            }
            if(flag){continue;}
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)!=')' && str.charAt(i)!='('){
                    continue;
                }
                String t=str.substring(0, i)+str.substring(i+1);
                if(!visited.contains(t)){
                    q.offer(t);
                    visited.add(t);
                }
            }  
        } 
        if(ans.size()==0){
            ans.add("");
        }
        return ans;
    }
    private boolean isValid(String s){
        int cnt=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                cnt++;
            }else if(s.charAt(i)==')' &&cnt--==0){
                return false;
            }
        }
        return cnt==0;
    }
    
}
public class LeetCode_301 {
    public static void main(String[] args) {
        Solution A=new Solution();
        String str="()())()";
        List<String>ans =A.removeInvalidParentheses(str);
        System.out.println(ans.size());
        for(String s:ans){

            System.out.println(s);
        }
    }
}
