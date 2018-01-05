public class Node {
    private Node next;
    private Node previous;
    private Object value;

    public Node(Object value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public Object getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
