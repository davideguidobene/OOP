import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DoubleIterator<T> implements Iterator<T> {

    private final int size;
    private int pos;
    private final ArrayList<T> a;

    @Contract(pure = true)
    public DoubleIterator(@NotNull ArrayList<T> a) {
        size = a.size();
        pos = -2;
        this.a = a;
    }

    public DoubleIterator(@NotNull Collection<T> c) {
        this (new ArrayList<T>(c));
    }

    @Override
    public boolean hasNext() {
        return pos+2 < size;
    }

    @Override
    public T next() {
        return a.get(pos+2);
    }
}
