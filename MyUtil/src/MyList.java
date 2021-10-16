public interface MyList<E> extends MyCollection<E> {

    E get(int i);

    E set(int i, E element);

    E remove(int i);

    void add(int index, E element);

    int indexOf(Object o);

    // int lastIndexOf(Object o);

}
