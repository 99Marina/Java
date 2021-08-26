public class kruskal {
    Edge[] edges;
    Vertice[] vertices;
    Edge[] res;
    static int sum;

    kruskal(Edge[] edges) {
        this.edges = new Edge[edges.length];
        for (int i = 0; i < edges.length; ++i){
            this.edges[i] = edges[i];
        }
    }



    void countTeams(){
        Vertice[] v = new Vertice[edges.length*2];

        int k = 0;

        v[0] = edges[0].v1;
        k++;
        boolean q = false;
        for (int i = 0; i < edges.length; ++i){
            q = false;
            for (int j = 0; j < k; ++j){
                if (edges[i].v1.name.equals(v[j].name)){
                  q = true;
                }
            }
            if (!q){
                v[k] = edges[i].v1;
                v[k].team = k;
                k++;
            }
        }


        for (int i = 0; i < edges.length; ++i){
            q = false;
            for (int j = 0; j < k; ++j){
                if (edges[i].v2.name.equals(v[j].name)){
                    q = true;
                }
            }
            if (!q){
                v[k] = edges[i].v2;
                v[k].team = k;
                k++;
            }
        }

        vertices = new Vertice[k];
        for (int i = 0; i < k; ++i){
            vertices[i] = v[i];
        }

        for (int i = 0; i < edges.length; ++i){
            for (int j = 0; j < vertices.length; ++j){
                if (edges[i].v1.name.equals(vertices[j].name)){
                    edges[i].v1.team = vertices[j].team;
                }

                if (edges[i].v2.name.equals(vertices[j].name)){
                    edges[i].v2.team = vertices[j].team;
                }
            }
        }


    }

    boolean isAllTeamsEqual(){
        int t = vertices[0].team;
        boolean q = (t == vertices[1].team);
        for (int i = 2; i < vertices.length; ++i){
            q = q & (t == vertices[i].team);
        }
        return q;
    }

    void changeTeams(){
        LinkList linkList = new LinkList();
        for (int i = 0; i < edges.length; ++i){
                if(!isAllTeamsEqual()) {
                    int v1, v2;
                    v1 = edges[i].v1.team;
                    v2 = edges[i].v2.team;
                    if (v1 != v2) {
                        linkList.addElement(linkList.getSize(), edges[i]);
                        sum += edges[i].weight;
                        for (int k = 0; k < vertices.length; ++k) {
                            if (vertices[k].team == v2) {
                                vertices[k].team = v1;
                                for (int j = 0; j < edges.length; ++j){
                                    if (vertices[k].name.equals(edges[j].v1.name)){
                                        edges[j].v1.team = v1;
                                    }

                                    if (vertices[k].name.equals(edges[j].v2.name)){
                                        edges[j].v2.team = v1;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    break;
                }

        }
        res = new Edge[linkList.getSize()];
        for (int i = 0; i < res.length; ++i){
            res[i] = linkList.getElement(i).data;
        }
    }

    void printRes(){
        for (int i = 0; i < res.length; ++i){
            System.out.println(res[i].v1.name + " " + res[i].v2.name);
        }
        System.out.println(sum);
    }



}
