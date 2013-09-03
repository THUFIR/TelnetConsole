package telnet;

import java.io.IOException;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class DataProcessor extends Observable {

    private final static Logger LOG = Logger.getLogger(DataProcessor.class.getName());
    private static final long SLEEP_MILLS = 1000; // not the best way to wait for more input
    final StringBuilder currentData = new StringBuilder();

    public DataProcessor() {
    }

    public void read(final ConcurrentLinkedQueue<Character> dataFromMUD) throws SocketException, IOException {

        //System.out.println("DataProcessor read...");
        Thread makeString = new Thread() {

            @Override
            public void run() {
                do {
                    try {
                        do {
                            char ch = dataFromMUD.remove();
                            currentData.append(ch);
                            //System.out.print("appended..." + ch);
                        } while (true);
                    } catch (NoSuchElementException nse) {
                        try {
                            //System.out.println("dp...nse");
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

    String getFinalData() {
        String finalData = currentData.toString();
        currentData.delete(0, currentData.length());
        return finalData;
    }
}