#include<bits/stdc++.h>
using namespace std;
vector<int> adj[100001];
bool vis[100001];
void dfs(int node){
    vis[node]=true;
    for(int nbr:adj[node]){
        if(!vis[nbr]){
            dfs(nbr);
        }
    }
}
int  main()
{
    int n,m,a,b;
    cin >> n >> m;
    vector<int>ans;
    for(int i=0;i<m;i++){
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    for(int i=1;i<=n;i++){
        if(vis[i]==false)
        {
            dfs(i);
            ans.push_back(i);
        }
    }
    cout<<ans.size()-1<<endl;
    for(int i=1;i<ans.size();i++){
        cout<<ans[i-1]<<" "<<ans[i]<<endl;
    }
    
    return 0;   
}