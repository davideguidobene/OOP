public abstract class TavolaAstratta implements Tavola {

    protected boolean[] posate;
    protected final int N;

    TavolaAstratta(int n) {
        N = n;
        posate = new boolean[N];
        for(int i=0; i<N; i++) {
            posate[i] = true;
        }
    }

}
