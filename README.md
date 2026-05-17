# Optimized Dijkstra's Shortest Path for Geographic Maps

A high-performance implementation of **Dijkstra’s Shortest Path Algorithm** optimized for large-scale geographic road networks.

By combining:

- **Early termination**
- **Incremental state reset**
- **A\* heuristic search using Euclidean geometry**

this project answers repeated shortest-path queries dramatically faster than a standard implementation.

Tested on a graph containing:

- **87,575 intersections**
- **121,961 roads**

representing the continental United States.

The optimized version reduced a **20+ minute baseline runtime** to **under 2 minutes** on **50,000 shortest-path queries**.

---

## Overview

Traditional Dijkstra’s algorithm explores the graph uniformly outward from the source vertex. While correct, this becomes inefficient for real-world applications like:

- GPS navigation
- Mapping systems
- Traffic routing
- Logistics optimization

This project improves performance by exploiting the geometric structure of road networks. Since map vertices have coordinates, the algorithm can intelligently prioritize vertices closer to the destination.

The result is a shortest-path solver that is:

- Faster
- More scalable
- Better suited for repeated routing queries on large maps

---

## Features

### Early Termination
Stops the search immediately once the destination vertex is settled, avoiding unnecessary exploration.

### Incremental Reset
Instead of resetting all vertices between queries (`O(V)`), the algorithm only resets vertices modified during the previous query.

### A\* Heuristic Optimization
Uses Euclidean distance to guide the search toward the destination, significantly reducing explored nodes.

### Multiple Client Modes
Supports:

- Single-query visualization with turtle graphics
- Batch shortest-path output
- Batch distance computation

---

## Performance

| Input File | Queries | Optimized Runtime | Baseline Runtime | Speedup |
|---|---:|---:|---:|---:|
| `usa-1000long.txt` | 1,000 | 21.2s | 45.4s | ~2× |
| `usa-5000short.txt` | 5,000 | 12.4s | 270.5s | ~22× |
| `usa-50000short.txt` | 50,000 | 119.5s | >20 min | >10× |

### Observations

- **Early termination** provides the biggest gains on short queries.
- **A\*** provides the biggest gains on long-distance queries.
- Combined optimizations reduce:
  - runtime
  - vertices examined
  - memory overhead

by up to **two orders of magnitude**.

---

## Algorithm Details

## 1. Early Termination + Incremental Reset

Instead of initializing every vertex for each query:

- Only the source vertex is inserted into the priority queue initially.
- Relaxed vertices are tracked in a `touched` list.
- Between queries, only touched vertices are reset.

This avoids expensive full-graph reinitialization.

The search also exits immediately once the destination is removed from the priority queue.

---

## 2. A\* Heuristic

When relaxing an edge `v → w`, the priority becomes:

```text
priority(w) =
    dist[v]
    + edgeWeight(v, w)
    + euclidean(w, destination)
    - euclidean(v, destination)
```

This geometric correction term biases the search toward the destination.

The heuristic is:

- **Admissible**
- **Consistent**

which guarantees that the algorithm still returns the true shortest path.

---

## Project Structure

| File | Description |
|---|---|
| `ShortestPath.java` | Interactive shortest-path visualization |
| `Distances.java` | Batch shortest-distance queries |
| `Paths.java` | Batch shortest-path queries |
| `Graph.java` | Graph representation |
| `Dijkstra.java` | Optimized shortest-path implementation |

---

## Compilation

```bash
javac *.java
```

---

## Usage

### Single Query with Visualization

```bash
java ShortestPath input6.txt
```

### Batch Distance Queries

```bash
java Distances usa.txt < queries.txt
```

### Batch Path Queries

```bash
java Paths usa.txt < queries.txt
```

---

## Input Format

```text
<num_vertices> <num_edges>

<vertex_index> <x> <y>
...

<vertex_u> <vertex_v>
...

<source> <sink>
```

### Example

```text
5 6

0 10.0 20.0
1 15.0 30.0
2 40.0 10.0
...

0 1
1 2
...

0 2
```

---

## Key Concepts Demonstrated

- Dijkstra’s Algorithm
- A\* Search
- Priority Queues
- Graph Optimization
- Geometric Heuristics
- Large-Scale Graph Processing
- Performance Engineering

---

## Results

Compared to a baseline implementation, this optimized approach:

- Processes repeated shortest-path queries dramatically faster
- Scales effectively to continental-sized road networks
- Reduces unnecessary graph exploration
- Maintains exact shortest-path correctness

---

## Future Improvements

Potential extensions include:

- Bidirectional Dijkstra / A\*
- Contraction Hierarchies
- Parallel query processing
- Dynamic edge weights for traffic simulation
- Real map integration using OpenStreetMap

---

## Author

Created by Amina El Guenuni.
