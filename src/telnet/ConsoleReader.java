package telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.logging.Logger;

public class ConsoleReader extends Observable {

    private final static Logger LOG = Logger.getLogger(ConsoleReader.class.getName());
    private String command = "help";

    public ConsoleReader() {
    }

    public String getCommand() {
        return command;
    }

    public void read() {
        Thread read = new Thread() {

            @Override
            public void run() {
                BufferedReader bufferedInput = new BufferedReader(new InputStreamReader(System.in));
                do {
                    try {
                        command = bufferedInput.readLine();
                        setChanged();
                        notifyObservers();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    } finally {
                    }
                } while (true);
            }
        };
        read.start();
    }
}