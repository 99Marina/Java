public class AVL {
    //корень дерева
    public Node root;

    AVL(){
        this.root = null;
    }

    //узнаем высоту
    int height(Node node){
        return node == null ? -1: node.height;
    }

    //обновляем высоту дерева
    void newHeight(Node node){
        node.height = Math.max(height(node.left), height(node.right));
    }

    //узнаем баланс
    int getBalance(Node node){
        return (node == null) ? 0 :height(node.right) - height(node.left);
    }

    //поиск по ключу
    public Node find(int key) {
        Node n = root;
        //пока не нашли нужный ключ, переходим в левое или правое поддерево(в зависимости от того, больше или меньше искоиый ключ текущего)
        while (n != null) {
            if (n.key == key) {
                break;
            }
            n = n.key < key ? n.right : n.left;
        }
        return n;
    }
    void preOrder(Node node) {

        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void printTree(){
        preOrder(this.root);
    }

    //поворот вправо
    Node rotateRight(Node node){
        Node a = node.left;
        Node b = a.right;
        a.right = node;
        node.left = b;
        newHeight(node);
        newHeight(a);
        return a;
    }

    //поворот влево
    Node rotateLeft(Node node){
        Node a = node.right;
        Node b = a.left;
        a.left = node;
        node.right = b;
        newHeight(node);
        newHeight(b);
        return a;
    }

    //вставка нового узла
    Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalanceTree(node);
    }

    //самый маленький элемент
    Node mostLeftChild(Node node){
        Node n = node;
        while(n.left != null){
            n = n.left;
        }
        return n;
    }

    //удаление узла
    Node delete(Node node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalanceTree(node);
        }
        return node;
    }

    //ребалансировка дерева
    Node rebalanceTree(Node node){
        newHeight(node);
        int balance = getBalance(node);
        if (balance > 1){
            if (height(node.right.right) > height(node.right.left)){
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        } else if(balance < -1){
            if (height(node.left.left) > height(node.left.right)){
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        return node;
    }
}
