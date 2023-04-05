package helpers;

import java.io.Serializable;

public class SerializableRunnable implements Runnable, Serializable {
    private final transient Runnable runnable;
    public SerializableRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}