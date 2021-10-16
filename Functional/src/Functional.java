import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Functional {

    public static <A, B> List<B> map(Iterable<? extends A> l, Function<A, B> f) {

        List<B> res = new ArrayList<B>();
        for(A a: l) {
            res.add(f.apply(a));
        }
        return res;

    }

    public static <A, B> B fold(Iterable<? extends A> l, B zero, BiFunction<A, B, B> f) {

        B acc = zero;
        for (A a: l) {
            acc = f.apply(a, acc);
        }
        return acc;

    }

    public static <A, B> List<B> map2(Iterable<? extends A> l, Function<A, B> f) {

        return Functional.fold(l, new ArrayList<B>(), (BiFunction<A, List<B>, List<B>>) (a, b) -> {
            b.add(f.apply(a));
            return b;
        });

    }
}
