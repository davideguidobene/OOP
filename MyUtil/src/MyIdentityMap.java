public class MyIdentityMap<K, V> extends MyGenericMap<K, V> {

    MyIdentityMap() {
        super(o -> o);
    }
}
