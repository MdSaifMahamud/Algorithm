#include<bits/stdc++.h>
using namespace std;
void dfs(char arr[1001][1001],int n,int m,int i,int j,bool vis[1001][1001]){
    if(i<1 || j<1 || i>n || j>m){
        return;
    }
    if(vis[i][j] || arr[i][j]=='#'){
        return;
    }
    vis[i][j]=true;
    dfs(arr,n,m,i+1,j,vis);
    dfs(arr,n,m,i-1,j,vis);
    dfs(arr,n,m,i,j-1,vis);
    dfs(arr,n,m,i,j+1,vis);
}
int main()
{
    int n,m;
    cin >> n >> m;
    char arr[1001][1001];
    bool vis[1001][1001]={{false}};
    for(int i=1;i<=n;i++)
    {
        for(int j=1;j<=m;j++)
        {
            cin >> arr[i][j];
        }
    }
    int ans=0;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(arr[i][j]=='.' && !vis[i][j]){
                dfs(arr,n,m,i,j,vis);
                ans++;
            }
        }
    }
    cout<<ans<<endl;
    return 0;
}