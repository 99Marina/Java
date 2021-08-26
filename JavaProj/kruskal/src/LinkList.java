public class LinkList{
    private ListElement head; //Первый элемент
    private ListElement tail; //Последний элемент
    private int size; //Размер списка

    //Конструктор
    public LinkList(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return this.head == null;
    }


    //Добавление элемента
    public boolean addElement(int index, Edge data){
        //Если индекс корректный
        if(index >= 0 && index <= this.size) {
            ListElement e = new ListElement(data);
            //Если список пуст, первый и последний элемент будут ссылаться на новый элемент
            if (this.isEmpty()) {
                this.head = e;
                this.tail = e;
                this.size++;
                return true;
            } else {
                //Если индекс равен размеру, вставляем элемент в конец
                if (index == this.size) {
                    //next текущего последнего указывает на новый
                    this.tail.next = e;
                    //последний ссылается на новый
                    tail = e;
                    this.size++;
                    return true;
                    //Вставляем элемент в начало
                } else if (index == 0){
                    //next нового указывает на первый текущий
                    e.next = head;
                    //первый ссылается  на новый
                    head = e;
                    size++;
                    return true;
                } else {
                    //запоминаем элемент, стоящий перед вставляемым
                    ListElement temp = this.getElement(index - 1);
                    //next нового элемента ссылается на элемент, кторый раньше имел этот индекс
                    e.next = this.getElement(index);
                    //элемент, который имеет индекс на 1 меньше нового, ссылается на новый
                    temp.next = e;
                    this.size++;
                    return true;
                }
            }
        } else {
            System.out.println("Index should be between 0 and "+ this.size);
            return false;
        }
    }

    //Удаление элемента
    public boolean deleteElement(int index){
        //Если лист пуст, сообщить об этом и выйти, вернув false, т.к. элемент не удален
        if (this.isEmpty()){
            System.out.println("List is empty!");
            return false;
        }
        //Если один элемент, удалить лист
        if (this.size == 1){
            this.deleteStructure();
            return true; //Элемент удален
        }
        //Если индекс некорректный , соббщить и вернуть false, т.к. элемент не удален
        if (index < 0 || index >= this.size){
            System.out.println("Incorrect index! It should be between 0 and "+(this.size - 1));
            return false;
        }
        //Удаление первого элемента
        if (index == 0){
            //Первый ссылается на второй
            head = head.next;
            size--;
            return true; //Элемент удален => возвращаем true
        }
        //Удаление последнего
        if (index == this.size - 1 ){
            //Последний ссылается на предпоследний
            tail = this.getElement(index - 1);
            size--;
            return true; //Элемент удален => возвращаем true
        }
        //Если оказались здесь,значит элемент вставляется в середину
        //e ссылается на элемент с индексом на 1 меньше вставляемого
        ListElement e = this.getElement(index - 1);

        //"пропускаем" удаляемый элемент
        e.next = e.next.next;
        size--;
        return true; //Элемент удален => возвращаем true
    }

    //Удаление листа, все зануляем
    public void deleteStructure(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //Преобразование двумерного массива в лист
    static public LinkList arrayToList(Edge[][] array){
        //создаем лист
        LinkList linkList = new LinkList();
        int t = 0;
        //перезаписываем все элементы
        for (int i = 0; i < array.length ; ++i){
            for (int j = 0; j < array[i].length; ++j){
                linkList.addElement(t, array[i][j]);
                t++;
            }
        }
        //возвращаем лист
        return linkList;
    }

    //Получить элемент по индексу
    public ListElement getElement(int index){
        ListElement e = this.head;
        int i = 0;
        //идем по листу, пока не дойдем до нужного индекса
        while(i < index){
            e = e.next;
            ++i;
        }
        return e;
    }

    //печать листа
    public void print(){
        //ссылаемся на первый элемент
        ListElement e = head;

        int i = 0;
        //пока не дошли до конца
        while (e != tail){
            //печатаем индекс и значение
            System.out.print(i+": "+e.data+"; ");
            //переход к следующему элементу
            e = e.next;
            //учеличение индекса
            i++;
        }
        //Выводи последний
        System.out.print(i+": "+e.data+" ");
    }

}
