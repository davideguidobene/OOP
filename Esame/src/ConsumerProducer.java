import java.util.function.Function;

public class ConsumerProducer {

    private final static int N_FILOSOFI = 10;

    public static void main(String[] args) {
        Thread[] filosofi = new Thread[N_FILOSOFI];
        Tavola t = new TavolaFilosofi(N_FILOSOFI);
        for(int i=0; i<N_FILOSOFI; i++) {
            final int index = i;
            filosofi[i] = new Thread(() -> {
                try {
                    //sit
                    t.pickUp(index);
                    //eat
                    t.putDown(index);
                } catch (InterruptedException ex) {
                    System.out.println(ex + ": philosopher " + index + " was interrupted");
                }
            });
            filosofi[i].start();
        }
        for(int i=0; i<N_FILOSOFI; i++) {
            try {
                filosofi[i].join();
            } catch (InterruptedException ex) {
                System.out.println(ex + ": philosopher " + i + " didn't join correctly to main thread");
            }
        }
        void
    }

}
