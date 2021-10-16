import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface MyCollection<T> extends Iterable<T> {

    boolean add(T o);

    boolean contains(Object o);

    boolean remove(Object o);

    int size();

    void clear();

    boolean isEmpty();

}
