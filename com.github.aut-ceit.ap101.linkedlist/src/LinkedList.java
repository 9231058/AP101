public class LinkedList {
    private Node root;
    private int length;

    public LinkedList() {
        this.root = null;
        this.length = 0;
    }

    public void append(Object object) {
        this.length++;

        if (this.root == null) {
            this.root  = new Node(object);
        } else {
            Node el = this.root;
            while (el.getNext() != null) {
                el = el.getNext();
            }
            Node n = new Node(object);
            el.setNext(n);
            n.setPrevious(el);
        }
    }

    public Object get(int index) {
        Node el = this.root;
        int i = 0;

        if (index >= this.length) {
            return null;
        }

        while (i < index) {
            el = el.getNext();
            i++;
        }

        return el.getValue();
    }

    public int getLength() {
        return length;
    }
}
