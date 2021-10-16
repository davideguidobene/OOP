import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class FunctionalPrimitives {

    @NotNull
    public static <A, B> List<B> map(@NotNull Iterable<A> it, Function<? super A, ? extends B> f) {
        List<B> l = new ArrayList<>();
        for(A a : it) {
            l.add(f.apply(a));
        }
        return l;
    }

    public static <A, B> B fold(@NotNull Iterable<A> it, B zero, BiFunction<? super A, ? super B, ? extends B> f) {
        B acc = zero;
        for(A a : it) {
            acc = f.apply(a, acc);
        }
        return acc;
    }

    public static <A, B> List<B> map2(@NotNull Iterable<? extends A> it, Function<A, B> f) {
        return fold(it, new ArrayList<>(), (a, acc) -> {acc.add(f.apply(a)); return acc;});
    }

    public static <A> void iter(@NotNull Iterable<? extends A> it, Consumer<A> c) {
        for(A a : it) {
            c.accept(a);
        }
    }

    public static <A> void filter(@NotNull Collection<? extends A> it, Function<A, Boolean> f) {
        for(A a: it){
            if(!f.apply(a))
                it.remove(a);
        }
    }

    public static <A> Collection<A> pure_filter(Iterable<? extends A> it, Function<A, Boolean> f){
        Collection<A> c = new ArrayList<>();
        for(A a: it) {
            if(f.apply(a))
                c.add(a);
        }
        return c;
    }

}
