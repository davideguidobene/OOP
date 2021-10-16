import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class MyLinkedCollection<E> implements MyCollection<E> {

    @Nullable
    protected Node head = null;

    protected class Node {

        @Nullable
        protected E key; // tengo il campo key @Nullable per mantenere la massima flessibilità. Naturalmente è possibile anhche dichiararlo @NotNull per un'implementazione più semplice
        @Nullable
        protected Node next = null;
        @Nullable
        protected Node prev = null;

        private Node(@Nullable E key) {
            this.key = key;
        }

        private Node(E key, @Nullable Node next) {
            this(key);
            this.next = next;
            if(this.next != null)
                this.next.prev = this;
        }

        protected Node(@Nullable Node prev, E key, Node next) {
            this(key, next);
            this.prev = prev;
            if(this.prev != null)
                this.prev.next = this;
        }

    }

    public MyLinkedCollection(){}

    public MyLinkedCollection(E e){
        add(e);
    }

    @Override
    public boolean add(E o) {
        head = new Node(null, o, head);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for(E e: this) {
            if((e==null && o==null) || (e!=null && e.equals(o)))
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {

        Iterator<E> i = this.iterator();
        while(i.hasNext()){
            E e = i.next();
            if((e==null && o==null) || (e!=null && e.equals(o))) {
                i.remove();
                return true;
            }
        }
        return false;

    }

    @Override
    public int size() {
        int i=0;
        for (E ignored : this) {
            i++;
        }
        return i;
    }

    @Override
    public void clear() {
        Iterator<E> i = this.iterator();
        while(i.hasNext()){
            i.next();
            i.remove();
        }
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {

            private Node n = head;

            @Override
            public boolean hasNext() {
                return (n.next != null);
            }

            @Override
            public E next() {
                E e = n.key;
                n = n.next;
                return e;
            }

            @Override
            public void remove() {
                assert n.prev != null;
                Node p = n.prev.prev;
                if(p != null)
                    p.next = n;
                n.prev = p;
            }
        };

    }

    public boolean isEmpty() {
        return head == null;
    }

}
