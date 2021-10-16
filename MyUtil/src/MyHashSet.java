public class MyHashSet<T> extends MyLinkedSet<T> {

    public MyHashSet(){ super(); }
    public MyHashSet(T t){ super(t); }

    @Override
    public boolean add(T o) {
        for(T e: this) {
            if((e==null && o==null) || (e!=null && e.hashCode() == o.hashCode()))
                return true;
        }
        return false;
    }

}
