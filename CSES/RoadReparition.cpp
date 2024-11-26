#include<bits/stdc++.h>
using namespace std;
class DSU {
    private:
        vector<int> p,rank,setSize;
        int numSets;
    public:
        DSU(int n) {
            p.assign(n,0);
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
            rank.assign(n,0); // height of all trees are 0.
            setSize.assign(n,1); // size of individual sets are 1
            numSets = n;
        }
 
        int find(int x) {
            if (p[x]==x) return x;
            return p[x] = find(p[x]); // path compression
        }
 
        bool same(int x, int y) {return find(x)==find(y);}
 
        bool merge(int i, int j) {
            if (same(i,j)) return false;
            int x = find(i);
            int y = find(j);
            if (rank[x]>rank[y]) swap(x,y);
            p[x] = y;
            if (rank[x]==rank[y]) rank[y]++;
            setSize[y] += setSize[x];
            numSets--;
            return true;
        }
 
        int size(int x) {
            return setSize[x];
        }
 
        int numDisjointSets() {
            return numSets;
        }
};

int main()
{
    int n,m,x,y,z;
    cin >> n >> m;
    DSU d(n+1);
    vector<array<long long,3>>edges;
    for(int i=0;i<m;i++)
    {
        cin >> x >>  y >> z;
        edges.push_back({z,x,y});
    }
    sort(edges.begin(),edges.end());
    long long  cnt=0,ans=0;
    for(int i=0;i<m;i++){
        if(d.merge(edges[i][1],edges[i][2])){
            cnt++;
            ans+=edges[i][0];
        }
        if(cnt==n-1){
            break;
        }
    }
    if(cnt==n-1){
        cout<<ans<<endl;
    }else{
        cout<<"IMPOSSIBLE"<<endl;
    }
    return 0;   
}