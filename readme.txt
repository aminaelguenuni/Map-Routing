
# Optimized Dijkstra's Shortest Path for Geographic Maps
 
This project is a high-performance implementation of Dijkstra's algorithm built for large-scale geographic road networks.  
By combining early termination, incremental state reset, and an A* heuristic that exploits Euclidean geometry, it handles thousands of repeated shortest-path queries significantly faster than a standard implementation.
 
Tested on a graph of **87,575 intersections** and **121,961 roads** representing the continental United States — reducing a 20+ minute baseline run to under 2 minutes on 50,000 short queries.
 
---
 
## Motivation
 
Standard Dijkstra's algorithm examines the entire graph for every query, making it too slow for real-world applications like GPS navigation that need to answer thousands of route requests per second.  
This project tackles that problem by exploiting the geometry of maps — since vertices have real coordinates, we can intelligently guide the search toward the destination instead of expanding blindly in all directions.  
The result is an algorithm that is **faster**, **smarter**, and scales to large national road networks.
 
---
 
## Features
 
- Early termination — stops the search the moment the destination is settled, skipping unnecessary work.
- Incremental reset — tracks only vertices modified per query and resets only those, avoiding a full O(V) reinitialization between every query.
- A* heuristic — uses Euclidean distance to the destination to bias the search toward the target and shrink the explored region.
- Three client modes: single query with turtle graphics visualization, batch path output, and batch distance output.
---
 
## Performance
 
| Input File | Queries | Optimized | Baseline | Speedup |
|---|---|---|---|---|
| `usa-1000long.txt` | 1,000 | 21.2s | 45.4s | ~2x |
| `usa-5000short.txt` | 5,000 | 12.4s | 270.5s | ~22x |
| `usa-50000short.txt` | 50,000 | 119.5s | >20 min | >10x |
 
Early termination helps most on short queries; A* helps most on long ones.  
Together they reduce runtime and vertices examined by up to two orders of magnitude.
 
---
 
## How It Works
 
**Idea 1 — Early termination + incremental reset**
 
Only the source is inserted into the priority queue initially. As vertices are relaxed, they are added to a `touched` list.  
Between queries, only those vertices are reset to ∞ instead of the full graph. The search exits as soon as the destination is popped from the PQ.
 
**Idea 2 — A* heuristic**
 
When relaxing edge `v → w`, the priority of `w` is updated as:
 
```
priority(w) = dist[v] + edgeWeight(v, w) + euclidean(w, dest) − euclidean(v, dest)
```
 
The correction term tilts the search geometrically toward the destination.  
The heuristic is admissible and consistent, so the true shortest path is always returned.
 
---
 
## Usage
 
Compile:
 
```bash
javac *.java
```
 
Single query with visualization:
 
```bash
java ShortestPath input6.txt
```
 
Batch distances:
 
```bash
java Distances usa.txt < queries.txt
```
 
Batch paths:
 
```bash
java Paths usa.txt < queries.txt
```
 
---
 
## Input Format
 
```
<num_vertices> <num_edges>
<vertex_index> <x> <y>
...
<vertex_u> <vertex_v>
...
<source> <sink>
```
 
