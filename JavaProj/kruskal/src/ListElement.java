
//элемент списка
public class ListElement {
    public Edge data; //содержимое
    public ListElement next; //ссылка на след. элемент

    public ListElement(Edge data){
        this.data = data;
        this.next = null;
    }
}
