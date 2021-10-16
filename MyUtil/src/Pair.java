import org.jetbrains.annotations.NotNull;

public class Pair<K, V> {

    @NotNull
    public K first;
    @NotNull
    public V second;

    Pair(@NotNull K first, @NotNull V second) {
        this.first = first;
        this.second = second;
    }

}
