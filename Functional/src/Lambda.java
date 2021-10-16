import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Lambda {

    public static void useless() {
    }

    public interface MyFunction<T, R> {

        R apply(T t);
        default void sprint() {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {

        // method reference
        Function<Double, Long> fs = Math::round; //static
        Function<Object, Integer> f = Object::hashCode; //non-static
        Consumer<String> cs = System.out::println;
        Supplier<Double> ss = Math::random;
        Runnable rs = Lambda::useless;

        BiFunction<Double, Double, Double> bs = Math::pow;

        // lambda
        Function<Integer, Integer> fl = i -> 2*i; // Function<Integer, Integer> fl = i -> {return 2*i;};
        Consumer<Iterable<?>> ccl = (it) -> {for(Object o : it){System.out.println(o);}};
        Supplier<Integer> sl = () -> 5;
        Runnable rl = () -> System.out.println("hello");

        Function<? super Collection<Integer>, Integer> weirdfl = (Iterable<Integer> a) -> {int sum=0; for(Integer i:a){sum+=i;} return sum;};

        // anonymus class
        Consumer<Integer> ca = new Consumer<>() {
            @Override
            public void accept(Integer i) {
                System.out.println(i);
            }
        };
        Supplier<Integer> pa = new Supplier<>() {
            @Override
            public Integer get() {
                return 1;
            }
        };

        // MyFun
        MyFunction<Double, Long> mf = Math::round;

        //Threads
        Thread t = new Thread(() -> System.out.println("hello"));

    }

}
