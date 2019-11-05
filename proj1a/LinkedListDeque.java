public class LinkedListDeque<T> {

    public class LLDNode{
        T value;
        LLDNode prev, next;

        public LLDNode(T value, LLDNode prev, LLDNode next){
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private int count = 0;
    private LLDNode sentinel;

    public LinkedListDeque(){
        sentinel = new LLDNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new LLDNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        for (int i = 0; i < other.size(); i++){
            addLast(other.get(i));
        }
    }

    public void addFirst(T item){
        LLDNode toAdd = new LLDNode(item, sentinel, sentinel.next);
        sentinel.next.prev = toAdd;
        sentinel.next = toAdd;
        this.count++;
    }

    public void addLast(T item){
        LLDNode toAdd = new LLDNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = toAdd;
        sentinel.prev = toAdd;
        this.count++;
    }

    public boolean isEmpty(){
        // if both sentinel pointers are to itself, there can be nothing else in the list
        return (sentinel.next == sentinel && sentinel.prev == sentinel);
    }

    public int size(){
        return this.count;
    }

    public void printDeque(){
        LLDNode head = sentinel.next;
        while (head != sentinel){
            System.out.print(head.value.toString() + " ");
            head = head.next;
        }
        System.out.print("\n");
    }

    public T removeFirst(){
        if (sentinel.next == sentinel) { return null; }
        this.count--;
        LLDNode next = sentinel.next;
        next.prev.next = next.next;
        next.next.prev = next.prev;
        return next.value;
    }

    public T removeLast(){
        if (sentinel.prev == sentinel) { return null; }
        this.count--;
        LLDNode last = sentinel.prev;
        last.prev.next = last.next;
        last.next.prev = last.prev;
        return last.value;
    }

    public T get(int index){
        if (index > size()) return null;
        LLDNode head = sentinel.next;
        while (head != sentinel){
            if (index == 0) {
                return head.value;
            }
            index--;
            head = head.next;
        }
        return null;
    }

    public T getRecursive(int index){
        if (index > size()) return null;
        LLDNode head = sentinel.next;
        return getRecursive(index, head);
    }

    private T getRecursive(int index, LLDNode node){
        if (index == 0) { return node.value; }
        return getRecursive(index - 1, node.next);
    }
}