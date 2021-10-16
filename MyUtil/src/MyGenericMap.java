import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MyGenericMap<K, V> implements MyMap<K, V> {

    @NotNull
    private List<Pair<K, V>> l = new ArrayList<>();
    @NotNull
    private Function<Object, ?> f;

    MyGenericMap (@NotNull Function<Object, ?> f) {
        this.f = f;
    }

    @Override
    public V get(Object key) {
        for(Pair<K, V> p : l) {
            if((f.apply(p.first)).equals(f.apply(key)))
                return p.second;
        }
        return null;
    }

    @Override
    public V put(@NotNull K key, @NotNull V value) {
        for(Pair<K, V> p : l) {
            if((f.apply(p.first)).equals(f.apply(key))) {
                V s = p.second;
                p.second = value;
                return s;
            }
        }
        l.add(new Pair<>(key, value));
        return null;
    }

}
