import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class MyLinkedSet<T> extends MyLinkedCollection<T> implements MySet<T> {

    public MyLinkedSet(){ super(); }
    public MyLinkedSet(T t){ super(t); }

    @Override
    public boolean add(T o) {
        if(contains(o))
            return false;
        else
            return add(o);
    }

}
