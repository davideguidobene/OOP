import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class MyLinkedList<E> extends MyLinkedCollection<E> implements MyList<E> {

    public MyLinkedList() { super(); }

    public MyLinkedList(E e){
        super(e);
    }

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
        Node n = head;
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
        Node n = head;
        while(j < index-1) {
            j++;
            if(n==null)
                throw new IndexOutOfBoundsException("index " + index + " was too big");
            n = n.next;
        }
        if(n==null)
            throw new IndexOutOfBoundsException("index " + index + " was too big");
        new Node(n, element, n.next);
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
