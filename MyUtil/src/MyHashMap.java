public class MyHashMap<K, V> extends MyGenericMap<K, V> {


    MyHashMap() {
        super(Object::hashCode);
    }
}
