import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class MyStaticLinkedList<E> implements MyList<E> {

    @Nullable
    protected Node<E> head;

    protected static class Node<T> {

        @Nullable
        protected T key;
        @Nullable
        protected Node<T> next, prev;

        private Node(@Nullable T key) {
            this.key = key;
            next = null;
            prev = null;
        }

        private Node(T key, @Nullable Node<T> next) {
            this(key);
            this.next = next;
            if(this.next != null)
                this.next.prev = this;
        }

        protected Node(@Nullable Node<T> prev, T key, Node<T> next) {
            this(key, next);
            this.prev = prev;
            if (this.prev != null)
                this.prev.next = this;
        }

    }

    public MyStaticLinkedList(E e){
        add(e);
    }

    @Override
    public boolean add(E o) {
        head = new Node<>(null, o, head);
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

            private Node<E> n = head;

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
                Node<E> p = n.prev.prev;
                if(p != null)
                    p.next = n;
                n.prev = p;
            }
        };

    }

    public boolean isEmpty() {
        return head == null;
    }

        /*
        Fine metodi di MyCollection,
        inizio metodi di MyList
        */


    @Override
    public E get(int i) {
        int j = 0;
        for(E e : this) {
            if(j == i)
                return e;
            j++;
        }
        throw new IndexOutOfBoundsException("index " + i + " was too big");
    }

    @Override
    public E set(int i, E element) {
        int j = 0;
        Node<E> n = head;
        while(j<i) {
            j++;
            if(n==null)
                throw new IndexOutOfBoundsException("index " + i + " was too big");
            n = n.next;
        }
        if(n==null)
            throw new IndexOutOfBoundsException("index " + i + " was too big");
        E r = n.key;
        n.key = element;
        return r;
    }

    @Override
    public E remove(int i) {
        E e = get(i);
        remove(e);
        return e;
    }

    @Override
    public void add(int index, E element) {
        int j = 0;
        Node<E> n = head;
        while(j < index-1) {
            j++;
            if(n==null)
                throw new IndexOutOfBoundsException("index " + index + " was too big");
            n = n.next;
        }
        if(n==null)
            throw new IndexOutOfBoundsException("index " + index + " was too big");
        new Node<>(n, element, n.next);
    }

    public int indexOf(Object o){
        int i = 0;
        for(E e: this){
            if((e==null && o==null) || (e!=null && e.equals(o)))
                return i;
            i++;
        }
        return -1;
    }

}
