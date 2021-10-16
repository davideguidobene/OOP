public class TavolaFilosofi extends TavolaAstratta {

    TavolaFilosofi(int n) {
        super(n);
    }

    @Override
    public synchronized void pickUp(int index) throws InterruptedException {
        synchronized (this) {
            int i = (index + 1) % N;
            while (!posate[index] || !posate[i]) {
                this.wait();
            }
            System.out.println(index);
            posate[index] = false;
            posate[i] = true;
        }
    }

    @Override
    public synchronized void putDown(int index) {
        posate[index] = true;
        posate[(index+1)%N] = true;
        this.notifyAll();
    }
}
