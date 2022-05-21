import java.util.ArrayList;

public class Graph<E> {

    private ArrayList<Node<E>> nodes;
    
    private static final double INF = Double.MAX_VALUE;

    private static final String MAX_VALUE = null;
    
    public Graph() {
        nodes = new ArrayList<Node<E>>();
    }
    
    /**
     * @return The ID for this node.
     */
    public int addNode(E data) {
        nodes.add(new Node<E>(data));
        return nodes.size() - 1;
    }
    
    /**
     * Adds an undirected edge, with weight 1, to the graph.
     */
    public void addUndirectedEdge(int nodeID1, int nodeID2) {
        addUndirectedEdge(nodeID1, nodeID2, 1);
    }
    
    /**
     * Adds an undirected edge of the given weight to the graph.
     */
    public void addUndirectedEdge(int nodeID1, int nodeID2, double weight) {
        addDirectedEdge(nodeID1, nodeID2, weight);
        addDirectedEdge(nodeID2, nodeID1, weight);
    }
    
    /**
     * Adds a directed edge of weight 1.
     */
    public void addDirectedEdge(int fromID, int toID) {
        addDirectedEdge(fromID, toID, 1);
    }
    
    /**
     * Adds a directed edge of the given weight.
     */
    public void addDirectedEdge(int fromID, int toID, double weight) {
        nodes.get(fromID).addEdgeTo(toID, weight);
    }
    
    /**
     * Returns a string representation of the graph.
     */
    public String toString() {
        String result = "---------------------------------\n";
        
        for(int i = 0; i < nodes.size(); i++)
            result += i + ": " + nodes.get(i).toString() + "\n";
            
        result += "---------------------------------";
        return result;
    }
    
    /**
     * This is step (2) in the project.
     */


    public void printOneEdgeAtATime() {
        for(int curr_node = 0; curr_node < nodes.size(); curr_node++) {
           Node<E> u = nodes.get(curr_node);
           ArrayList<Integer> adj = u.getAdjacentList();
           
           for (int dest_node = 0; dest_node < adj.size(); dest_node++) {
               System.out.print("Edge from " + curr_node + " " + u.getDataAsString());
               System.out.println(" to " + adj.get(dest_node) + " " + nodes.get(adj.get(dest_node)).getDataAsString() + " with weight "+ nodes.get(curr_node).getWeight(dest_node));
           }
        }
    }
    
    /**
     * Performs the Bellman-Ford algorithm for the given source Node ID, printing out all results.
     * This is step (3) in the project, and optionally step (4) as well if you choose to do it.
     */
    /*
    */
    
    ArrayList<Double> d = new ArrayList<Double> ();
    ArrayList<Integer> pred = new ArrayList<Integer> ();
    
    
    public void bellmanFord(int source) {        
        initialize (source);
        for (int i = 1; i <= nodes.size() - 1; i++) {
            for (int curr_vertex = 0; curr_vertex <= nodes.size() - 1; curr_vertex++) {
                for (int adj_vertex = 0; adj_vertex <= nodes.get(curr_vertex).getAdjacentList().size() - 1; adj_vertex++) {
                    relax (curr_vertex, adj_vertex);
                }
            }
            
        }
        for (int curr_vertex = 0; curr_vertex <= nodes.size() - 1; curr_vertex++) {
            for (int adj_vertex = 0; adj_vertex <= nodes.get(curr_vertex).getAdjacentList().size() - 1; adj_vertex++) {
                if (d.get(curr_vertex) + nodes.get(curr_vertex).getWeight(adj_vertex) < d.get(adj_vertex)) {
                }
            }
        }
        
        
        for (int curr_vertex = 0; curr_vertex <= nodes.size()-1; curr_vertex++) {
            Node<E> u = nodes.get(curr_vertex);
            if (pred.get(curr_vertex) != -1)
                System.out.println(u.getDataAsString() + ": " + d.get(curr_vertex) +", by way of " + nodes.get(pred.get(curr_vertex)).getDataAsString());
            else
                System.out.println(u.getDataAsString() + ": 0.0" + ", by way of " + "N/A");
    
        }
        
        for (int curr_vertex = 0; curr_vertex <= nodes.size()-1; curr_vertex++) {
            Node<E> u = nodes.get(curr_vertex);
            System.out.println("From " + nodes.get(source).getDataAsString() + " to " + (nodes.get(curr_vertex)).getDataAsString() + " distance: " + d.get(curr_vertex) + ", path: " + path(source, curr_vertex));        
       }
    }
        
        public String path(int source, int dest)
        {
           String result= getNode(dest).getDataAsString();
           
          while(source!=dest)
          {
              dest = pred.get(dest);
              result= getNode(dest).getDataAsString() + " " + result;
          }
          
           return result;
        }
        
        public Node getNode(int dest)
        {
         return nodes.get(dest);
        }
    
    private void initialize (int source) {
        d = new ArrayList<Double>();
        pred = new ArrayList<Integer>();        
        
        for(int v = 0; v < nodes.size(); v++) {
            d.add(INF);
            pred.add(-1);
        }
        
        d.set (source, 0.0);
        pred.set (source, -1);

    }

    private void relax (int uID, int uAdjListID) {
        Node<E> u = nodes.get(uID);
        ArrayList<Integer> uAdjList = u.getAdjacentList();
        int vID = uAdjList.get(uAdjListID);
        Node<E> v = nodes.get(vID);
        double weight = u.getWeight(uAdjListID);
        double ud = d.get(uID);
        double vd = d.get(vID);
        
        if (ud + weight < vd) {
            d.set(vID, ud + weight);
            pred.set(vID, uID);
        }
    }
    
}

