package telnet;

import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class InputStreamReader extends Observable {

    private final static Logger LOG = Logger.getLogger(InputStreamReader.class.getName());

    public InputStreamReader() {
    }

    public void print(final InputStream inputStream, final ConcurrentLinkedQueue<Character> clq) {

        Thread print = new Thread() {

            StringBuilder sb = new StringBuilder();

            @Override
            public void run() {
                try {
                    char ch = (char) inputStream.read();
                    sb.append(ch);
                    while (255 > ch && ch >= 0) {
                        clq.add(ch);
                        ch = (char) inputStream.read();
                    }
                } catch (IOException ex) {
                    out.println("cannot read inputStream:\t" + ex);
                }
            }
        };
        print.start();
    }
}