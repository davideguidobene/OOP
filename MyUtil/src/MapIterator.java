import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class MapIterator<T, R> implements Iterator<R> {

    List<T> l;
    int pos;
    Function<T, R> f;

    MapIterator(List<T> l, Function<T, R> f) {
        this.l = l;
        pos = 0;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        return pos<l.size();
    }

    @Override
    public R next() {
        return f.apply(l.get(pos));
    }
}
