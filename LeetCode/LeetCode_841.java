import java.util.*;
class Solution {
    private void dfs(int node,List<List<Integer>> rooms,Set<Integer>vis){
        if(!vis.contains(node)){
            vis.add(node);
        }
        for(Integer nbr:rooms.get(node)){
            if(nbr==node){
                continue;
            }
            if(!vis.contains(nbr)){
                dfs(nbr, rooms, vis);
            }
        }

    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer>vis=new HashSet<>();
        dfs(0, rooms, vis);
        return vis.size()==rooms.size();
    }
}
public class LeetCode_841 {
    
}
