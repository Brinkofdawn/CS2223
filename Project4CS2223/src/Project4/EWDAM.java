package Project4;

import java.util.Stack;

/*************************************************************************
 *  Compilation:  javac EdgeWeightedDigraph.java
 *  Execution:    java EdgeWeightedDigraph V E
 *  Dependencies: Bag.java DirectedEdge.java
 *
 *  An edge-weighted digraph, implemented using adjacency lists.
 *
 *************************************************************************/

/**
 *  The <tt>EdgeWeightedDigraph</tt> class represents a edge-weighted
 *  digraph of vertices named 0 through <em>V</em> - 1, where each
 *  directed edge is of type {@link DirectedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add a directed edge
 *  to the digraph and iterate over all of edges incident from a given vertex.
 *  It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of @link{Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the edges incident from a given vertex, which takes
 *  time proportional to the number of such edges.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class EWDAM {
    private final int V;
    private int E;
    private Double[][] adjM;
    
    /**
     * Initializes an empty edge-weighted digraph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     * @throws java.lang.IllegalArgumentException if <tt>V</tt> < 0
     */
    public EWDAM(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
       adjM = new Double[V][V];
    }

    /**  
     * Initializes an edge-weighted digraph from an input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     * @param in the input stream
     * @throws java.lang.IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws java.lang.IllegalArgumentException if the number of vertices or edges is negative
     */
    public EWDAM(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight = in.readDouble();
            addEdge(v, w, weight);
        }
    }


    /**
     * Returns the number of vertices in the edge-weighted digraph.
     * @return the number of vertices in the edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the edge-weighted digraph.
     * @return the number of edges in the edge-weighted digraph
     */
    public int E() {
        return E;
    }
    
    public Double[][] adjM() {
		return adjM;
	}

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the directed edge <tt>e</tt> to the edge-weighted digraph.
     * @param e the edge
     * @throws java.lang.IndexOutOfBoundsException unless endpoints of edge are between 0 and V-1
     */
    public void addEdge(int h, int t, double weight) {
        validateVertex(h);
        validateVertex(t);
        adjM[h][t]=weight;
        E++;
    }


    /**
     * Returns the directed edges incident from vertex <tt>v</tt>.
     * @return the directed edges incident from vertex <tt>v</tt> as an Iterable
     * @param v the vertex
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
     */
    public Iterable<DirectedEdge> adj(int v) {
		int w = 0;
		validateVertex(v);
		Stack<DirectedEdge> edges = new Stack<DirectedEdge>();
		for(Double weight : adjM[v]) {
			if(weight != null) {
				edges.push(new DirectedEdge(v, w, weight));
			}
			w++;
		}
		return edges;
	}

    /**
     * Returns all directed edges in the edge-weighted digraph.
     * To iterate over the edges in the edge-weighted graph, use foreach notation:
     * <tt>for (DirectedEdge e : G.edges())</tt>.
     * @return all edges in the edge-weighted graph as an Iterable.
     */
    public Iterable<DirectedEdge> edges() {
		int v = 0, w = 0;
		Stack<DirectedEdge> edges = new Stack<DirectedEdge>();
		for(Double[] weights : adjM) {
			for(Double weight : weights) {
				if(weight != null) {
					edges.push(new DirectedEdge(v, w, weight));
				}
				w++;
			}
			v++;
			w = 0;
		}
		return edges;
	}

    /**
     * Returns a string representation of the edge-weighted digraph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *   followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Double[] edges : adjM) {
			sb.append("| ");
			for (Double weight : edges) {
				if (weight != null) {
					sb.append(String.format("%.2f |", weight));
				} else {
					sb.append("     |");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}


}