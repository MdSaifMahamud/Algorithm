#include<bits/stdc++.h>
using namespace std;
vector<int>adj[100001];
bool vis[100001];
int dist[100001];
int par[100001];
int n,m;
bool bfs(){
    queue<int>q;
    dist[1]=1;
    vis[1]=1;
    q.push(1);
    while(!q.empty()){
        int node=q.front();
        q.pop();
        if(node==n){
            return true;
        }
        for(int u:adj[node]){
            if(!vis[u]){
                dist[u]=dist[node]+1;
                vis[u]=true;
                par[u]=node;
                q.push(u);
            }
        }
    }
    return false;
}
int main()
{
    int a,b;
    cin >> n >> m;
    for(int i=0;i<m;i++){
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    if(bfs()){
        cout<<dist[n]<<endl;
        int p=n;
        vector<int>ans;
        while(p!=0){
            ans.push_back(p);
            p=par[p];
        }
        reverse(ans.begin(),ans.end());
        for(int x:ans){
            cout<<x<<" ";
        }
        cout<<endl;
    }else{
        cout<<"IMPOSSIBLE"<<endl;
    }
    
    return 0;
}