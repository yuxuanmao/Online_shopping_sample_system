package project;

import java.util.Queue;

public interface GraphInterface
{
  boolean isEmpty();
  // Returns true if this graph is empty; otherwise, returns false.

  boolean isFull();
  // Returns true if this graph is full; otherwise, returns false.
  
  void addVertex(City vertex) throws GraphIsFullException, VertexExistsException;
  // Preconditions:  Vertex is not already in this graph.
  //                 Vertex is not null.
  // Throws GraphIsFullException if the graph is full
  // Otherwise adds vertex to this graph.

  void addEdge(City fromVertex, City toVertex);
  // Adds an edge with the specified weight from fromVertex to toVertex.

  Queue<City> getToVertices(City vertex);
  // Returns a queue of the vertices that are adjacent from vertex.

  void clearMarks();
  // Sets marks for all vertices to false.

  void markVertex(City vertex);
  // Sets mark for vertex to true.

  boolean isMarked(City vertex);
  // Returns true if vertex is marked; otherwise, returns false.
    
}