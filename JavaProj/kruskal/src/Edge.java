//ребро графа
public class Edge{
    Vertice v1;
    Vertice v2;
    int weight;

    Edge(Vertice v1, Vertice v2, int weight){
        this.weight = weight;
        this.v1 = v1;
        this.v2 = v2;
    }
}
