package kas.concurrente;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackoffLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY = 0;
    private static final int MAX_DELAY = 1000;

    @Override
    public void lock() {
        // backoff??
        while (true) {
            while (state.get());
            if (!state.getAndSet(true)){
                return;
            } else {
                //backoff
            }

        }
    }

    @Override
    public void unlock() {
        this.state.set(false);
    }
    
}
