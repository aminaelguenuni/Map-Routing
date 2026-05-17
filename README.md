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

## Author

Created by Amina El Guenuni.
