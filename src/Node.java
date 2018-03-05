public class Node<E> {
    private E data;
    private Node next;

    public Node(){
        this(null, null);
    }

    public Node(E info){
        this(info, null);
    }

    public Node(E info, Node next){
        this.data = info;
        this.next = next;
    }

    public void changeData(E info){
        this.data = info;
    }

    public E getDate(){
        return this.data;
    }

    public void changeNext(Node next){
        this.next = next;
    }

    public Node getNext(){
        return this.next;
    }

}
