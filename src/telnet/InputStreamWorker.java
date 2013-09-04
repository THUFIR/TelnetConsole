package telnet;

import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class InputStreamWorker {

    private final static Logger LOG = Logger.getLogger(InputStreamWorker.class.getName());

    public InputStreamWorker() {
    }

    public void print(final InputStream inputStream,  final ConcurrentLinkedQueue<Character> charQueue) {

        Thread print = new Thread() {

            StringBuilder sb = new StringBuilder();

            @Override
            public void run() {
                try {
                    char ch = (char) inputStream.read();
                    sb.append(ch);
                    while (255 > ch && ch >= 0) {
                        charQueue.add(ch);
                        ch = (char) inputStream.read();
                        System.out.print(ch);
                    }
                } catch (IOException ex) {
                    out.println("cannot read inputStream:\t" + ex);
                }
            }
        };
        print.start();
    }
}