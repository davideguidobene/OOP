import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FilosofiACena {
    public final static int NUM_FILOSOFI = 7;

    interface Table {
        void raccogli(int i) throws InterruptedException;
        void deposita(int i);
    }

    public static abstract class AbstractTable implements Table {

        protected boolean[] bacchette = new boolean[NUM_FILOSOFI];

        AbstractTable() {
            for(int i=0; i<NUM_FILOSOFI; i++) {
                bacchette[i] = true;
            }
        }

        protected void pickUpLeft(int i) {
            if(bacchette[i])
                bacchette[i] = false;
            else
                throw new RuntimeException(i + "was already picked up");
        }

        protected void pickUpRight(int i) {
            int ind = (i+1)%NUM_FILOSOFI;
            if(bacchette[ind])
                bacchette[ind] = false;
            else
                throw new RuntimeException(ind + "was already picked up");
        }

        protected void putDownLeft(int i) {
            if(!bacchette[i])
                bacchette[i] = true;
            else
                throw new RuntimeException(i + "was already down");
        }

        protected void putDownRight(int i) {
            int ind = (i+1)%NUM_FILOSOFI;
            if(!bacchette[ind])
                bacchette[ind] = true;
            else
                throw new RuntimeException(ind + "was already down");
        }

        protected void pickUp(int i) {
            pickUpLeft(i);
            pickUpRight(i);
        }

        protected void putDown(int i) {
            putDownLeft(i);
            putDownRight(i);
        }

    }

    public static class TavolaAtomica extends AbstractTable {
        static List<Integer> l = new ArrayList<>();

        TavolaAtomica() {
        }

        @Override
        public synchronized void raccogli(int i) throws InterruptedException {
            while(!bacchette[i] || !bacchette[(i+1)%NUM_FILOSOFI]) {
                this.wait();
            }
            pickUp(i);
        }

        @Override
        public synchronized void deposita(int i) {
            putDown(i);
            this.notifyAll();
        }

        public synchronized static void y() {
            synchronized (TavolaAtomica.class) {
                System.out.println("hello");
            }


        }

    }

    public static void main(String[] args) {
        Thread[] filosofi = new Thread[NUM_FILOSOFI];
        Table t = new TavolaAtomica();
        for(int i=0; i<NUM_FILOSOFI; i++){
            final int index = i;
            filosofi[index] = new Thread(() -> {
                while(true) {
                    // PENSA
                    System.out.println("Filosofo " + index +" pensa");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        t.raccogli(index);  // raccoglie atomicamente le bacchette
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //MANGIA
                    System.out.println("Filosofo " + index +" mangia");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t.deposita(index);  // deposita le bacchette
                }
            });
            filosofi[i].start();
        }
    }

    @NotNull
    public static<A, B> List<B> mapThreads(@NotNull Iterable<A> it, Function<? super A, ? extends B> f){
        List<B> l = new ArrayList<>();
        for(A a: it) {
            Thread t = new Thread(() -> l.add(f.apply(a)));
            t.start();
        }
        return l;
    }

}
