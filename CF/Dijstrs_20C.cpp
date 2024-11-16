#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

class Edge {
public:
    int to;
    int weight;

    Edge(int to, int weight) {
        this->to = to;
        this->weight = weight;
    }
};

// Comparator for the priority queue
struct CompareEdge {
    bool operator()(const Edge& a, const Edge& b) {
        return a.weight > b.weight;  // Min-heap based on edge weight
    }
};

class Graph {
private:
    int vertices;
    vector<vector<Edge>> adjLists;

public:
    Graph(int vertices) {
        this->vertices = vertices;
        adjLists.resize(vertices);
    }

    void addEdge(int i, int j, int weight) {
        adjLists[i - 1].push_back(Edge(j - 1, weight));  // Adjust for 1-indexed to 0-indexed
        adjLists[j - 1].push_back(Edge(i - 1, weight));
    }

    void dijkstras(int src, int dest) {
        src--;  // Adjust for 0-indexed
        dest--; // Adjust for 0-indexed

        vector<long long> dist(vertices, INT_MAX);
        dist[src] = 0;

        priority_queue<Edge, vector<Edge>, CompareEdge> pq;
        pq.push(Edge(src, 0));

        vector<int> path;  // Store path only when necessary
        bool found = false;  // Flag for early exit

        while (!pq.empty() && !found) {
            Edge ed = pq.top();
            pq.pop();
            int u = ed.to;

            if (u == dest) {
                found = true;  // Exit as soon as we reach the destination
                continue;
            }

            for (const Edge& nbr : adjLists[u]) {
                int v = nbr.to;
                int weight = nbr.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.push(Edge(v, dist[v]));
                }
            }
        }

        if (dist[dest] == INT_MAX) {
            cout << "-1" << endl;
            return;
        }

        // Backtrack to find the path
        int current = dest;
        while (current != src) {
            path.push_back(current + 1);  // Convert to 1-indexed for output
            int next = -1;
            for (const Edge& nbr : adjLists[current]) {
                if (dist[current] - nbr.weight == dist[nbr.to]) {
                    next = nbr.to;
                    break;
                }
            }
            if (next == -1) {  // If we can't find the next step
                cout << "-1" << endl;
                return;
            }
            current = next;
        }
        path.push_back(src + 1);

        for (auto it = path.rbegin(); it != path.rend(); ++it) {
            cout << *it << " ";
        }
        cout << endl;
    }
};

int main() {
    int n, m;
    cin >> n >> m;

    Graph grp(n);
    for (int i = 0; i < m; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        grp.addEdge(x, y, z);
    }

    grp.dijkstras(1, n);  // Find the shortest path from vertex 1 to vertex n

    return 0;
}
