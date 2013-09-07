package telnet.connection;

import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class CharacterDataQueueWorker extends Observable {

    private final static Logger LOG = Logger.getLogger(CharacterDataQueueWorker.class.getName());
    private static final long SLEEP_MILLS = 1000; // not the best way to wait for more input
    final StringBuilder currentData = new StringBuilder();

    public CharacterDataQueueWorker() {
    }

    public void read(final ConcurrentLinkedQueue<Character> remoteCharDataQueue) {
        Thread makeString = new Thread() {

            @Override
            public void run() {
                do {
                    try {
                        do {
                            Character ch = remoteCharDataQueue.remove();
                            currentData.append(ch);
                        } while (true);
                    } catch (NoSuchElementException nse) {
                        try {
                            setChanged();
                            notifyObservers();
                            Thread.sleep(SLEEP_MILLS);
                        } catch (InterruptedException | NumberFormatException ex) {
                        }
                    }
                } while (true);
            }
        };
        makeString.start();
    }

    public String getFinalData() {
        String finalData = currentData.toString();
        currentData.delete(0, currentData.length());
        return finalData;
    }
}