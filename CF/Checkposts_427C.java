import java.io.*;
import java.util.*;
 
 
public class Checkposts_427C{
    static class Graph 
    {
        int nodes, total_component;
        ArrayList<Integer>[] adj, revadj,con_comps;
    
        boolean[] visited;
        int[] costs;
        int[] component_id;
        Stack<Integer> ordering;
        long result;
    
        Graph(int val) 
        {
            nodes = val;
            total_component = 0;
            adj = new ArrayList[nodes];
            revadj = new ArrayList[nodes];
            con_comps = new ArrayList[nodes];
    
            for (int i = 0; i < val; i++) 
            {
                adj[i]=(new ArrayList<>());
                revadj[i]=(new ArrayList<>());
                con_comps[i]=new ArrayList<>();
            }
    
            visited = new boolean[val];
            costs = new int[val];
            component_id = new int[val];
            ordering = new Stack();
        }
    
        void addEdge(int from, int to) 
        {
            adj[from].add(to);
            revadj[to].add(from);
        }
    
        
        void dfs(int node) 
        {
            visited[node] = true;
    
            for (int next:adj[node]) {
                if (!visited[next])
                    dfs(next);
            }
            ordering.add(node);
        }
    
        void dfs2(int node) 
        {
            component_id[node] = total_component;
            con_comps[total_component].add(costs[node]);
    
            visited[node] = true;
    
            for (int next : revadj[node]) 
            {
                if (!visited[ next])
                    dfs2(next);
            }
        }
    
        void scc() {
            for (int i = 0; i < nodes; i++) {
                if (!visited[i])
                    dfs(i);
            }
    
            for (int i = 0; i < nodes; i++)
                visited[i] = false;
    
            while (!ordering.isEmpty()) {
                int top = ordering.peek();
                ordering.pop();
    
                if (!visited[top]) {
                    dfs2(top);
                    total_component++;
                }
            }
        }
    
        void printans()
        {
            long ans1=0,ans2=1;
            for(int i=0;i<total_component;i++)
            {
                HashMap<Integer,Integer>hm = new HashMap<>();
                int mini=1000000000;
                for(int val:con_comps[i])
                {
                    mini=Math.min(mini,val);
                    if(hm.containsKey(val))
                        hm.put(val,hm.get(val)+1);
                    else hm.put(val, 1);
                }
                ans1+=mini;
                ans2*=hm.get(mini);
                ans2%=1000000007;
            }
            System.out.println(ans1+" "+ans2);
        }
    }
	public static void main(String[] args) throws Exception
	{
		in=new Scanner(System.in);
		int num=in.nextInt();
 
		Graph graph = new Graph(num);
 
		for(int i=0;i<num;i++)
		{
			graph.costs[i]=in.nextInt();
		}
 
		int edges=in.nextInt();
		
		for(int i=0;i<edges;i++)
		{
			int from=in.nextInt(),to=in.nextInt();
			
			graph.addEdge(from-1, to-1);
		}
 
		graph.scc();
 
		graph.printans();
	}
 
	static Scanner in;
		
		static class Scanner
		{
			StringTokenizer st;
			BufferedReader br;
	 
			public Scanner (InputStream s)
			{
				br=new BufferedReader(new InputStreamReader(s));
			}
	 
			public String next() throws IOException
			{
				while(st==null || !st.hasMoreTokens())
					st=new StringTokenizer(br.readLine());
				return st.nextToken();
			}
 
			public int nextInt() throws IOException
			{
				return Integer.parseInt(next());
			}
	 
		}  
}