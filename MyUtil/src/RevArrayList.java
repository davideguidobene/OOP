import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class RevArrayList<E> extends ArrayList<E> {

    @NotNull
    @Override
    public Iterator<E> iterator(){

        return new Iterator<>() {

            int pos = size();

            @Override
            public boolean hasNext() {
                return pos - 1 >= 0;
            }

            @Override
            public E next() {
                pos--;
                return get(pos);
            }
        };
    }

}
