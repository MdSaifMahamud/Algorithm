#include <bits/stdc++.h>
using namespace std;

void dfs1(int node, vector<vector<int>> &adj, vector<int> &vis, stack<int> &st) {
    vis[node] = 1;
    for (auto it : adj[node]) {
        if (!vis[it]) dfs1(it, adj, vis, st);
    }
    st.push(node);
}

void dfs2(int node, vector<vector<int>> &radj, vector<int> &vis, vector<int> &component) {
    vis[node] = 1;
    component.push_back(node);
    for (auto it : radj[node]) {
        if (!vis[it]) dfs2(it, radj, vis, component);
    }
}

int main() {
    int T;
    cin >> T;

    for (int t = 1; t <= T; t++) {
        int N, M;
        cin >> N >> M;

        vector<vector<int>> adj(N + 1), radj(N + 1);
        vector<int> vis(N + 1, 0);

        for (int i = 0; i < M; i++) {
            int a, b;
            cin >> a >> b;
            adj[a].push_back(b);
            radj[b].push_back(a);
        }

        stack<int> st;

        // First pass: DFS on the original graph to fill the stack
        for (int i = 1; i <= N; i++) {
            if (!vis[i]) dfs1(i, adj, vis, st);
        }

        // Second pass: DFS on the reversed graph to find SCCs
        fill(vis.begin(), vis.end(), 0);
        vector<int> scc_id(N + 1, 0);
        int scc_count = 0;

        while (!st.empty()) {
            int node = st.top();
            st.pop();
            if (!vis[node]) {
                vector<int> component;
                dfs2(node, radj, vis, component);
                scc_count++;
                for (auto v : component) {
                    scc_id[v] = scc_count;
                }
            }
        }

        // Condense SCCs into a new graph
        vector<int> in_degree(scc_count + 1, 0);

        for (int u = 1; u <= N; u++) {
            for (auto v : adj[u]) {
                if (scc_id[u] != scc_id[v]) {
                    in_degree[scc_id[v]]++;
                }
            }
        }

        // Count source SCCs
        int source_sccs = 0;
        for (int i = 1; i <= scc_count; i++) {
            if (in_degree[i] == 0) {
                source_sccs++;
            }
        }

        cout << "Case " << t << ": " << source_sccs << "\n";
    }

    return 0;
}
