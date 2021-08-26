import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static void sort(Edge arr[]){
        int n = arr.length;
        for (int i = 1; i < n; ++i){
            Edge key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].weight > key.weight) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) throws IOException {
        FileReader fileIn = new FileReader("file.txt");;
        Scanner scanner = new Scanner(fileIn);
        LinkList linkList = new LinkList();

        while (scanner.hasNext()) {
            linkList.addElement(0, new Edge(new Vertice(scanner.next(), 0), new Vertice(scanner.next(), 0), scanner.nextInt()));
        }

        scanner.close();
        fileIn.close();
        Edge[] edges = new Edge[linkList.getSize()];
        for (int i = 0; i < edges.length; ++i){
            edges[i] = new Edge(linkList.getElement(i).data.v1, linkList.getElement(i).data.v2, linkList.getElement(i).data.weight);
        }

        sort(edges);

        kruskal kruskal = new kruskal(edges);
        kruskal.countTeams();
        kruskal.changeTeams();
        kruskal.printRes();

    }
}
