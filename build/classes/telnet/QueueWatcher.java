package telnet;

import java.io.IOException;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class QueueWatcher extends Observable {

    private final static Logger LOG = Logger.getLogger(QueueWatcher.class.getName());
    private static final long SLEEP_MILLS = 1000; // not the best way to wait for more input
    private String finalData = "";

    public QueueWatcher() {
    }

    public void read(final ConcurrentLinkedQueue<Character> dataFromMUD) throws SocketException, IOException {
        final StringBuilder currentData = new StringBuilder();

        Thread makeString = new Thread() {

            @Override
            public void run() {
                do {
                    try {
                        do {
                            char ch = dataFromMUD.remove();
                            currentData.append(ch);
                        } while (true);
                    } catch (NoSuchElementException nse) {
                        setChanged();
                        notifyObservers();
                        finalData = currentData.toString();
                        currentData.delete(0, currentData.length());
                        try {
                            Thread.sleep(SLEEP_MILLS);
                        } catch (InterruptedException ex) {
                        }
                    }
                } while (true);
            }
        };
        makeString.start();
    }

    String getFinalData() {
        return finalData;
    }
}