package telnet;

import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.net.telnet.TelnetClient;

public final class Controller implements Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamReader serverReader = new InputStreamReader();
    private ConsoleReader consoleReader = new ConsoleReader();
    private QueueWatcher dataProcessor = new QueueWatcher();
    private Triggers triggers = new Triggers();
    private final ConcurrentLinkedQueue<Character> telnetData = new ConcurrentLinkedQueue();
    private OutputStream outputStream = telnetClient.getOutputStream();

    public void readPrintParse(final InputStream inputStream) throws SocketException, IOException {
        serverReader.print(inputStream, telnetData);
        serverReader.addObserver(this);
        consoleReader.read();
        consoleReader.addObserver(this);
        dataProcessor.read(telnetData);
        dataProcessor.addObserver(this);
        triggers.addObserver(this);
    }

    private void sendCommand(String command) {
        out.println("command\t\t" + command);
        try {
            byte[] bytes = command.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                out.println("byte\t" + bytes[i]);
            }
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException | NullPointerException ex) {
            out.println("no command sent\t" + command);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String command = "help";

        if (o instanceof QueueWatcher) {
            String data = dataProcessor.getFinalData();
            out.println("noSuchElementException...\n" + data);
            command = triggers.parse(data);
            sendCommand(command);
        }

        if (o instanceof ConsoleReader) {
            command = consoleReader.getCommand();
            out.println("your command\t\t" + command);
            sendCommand(command);
        }
    }

    public Controller() throws SocketException, IOException {
        Properties props = PropertiesReader.getProps();
        InetAddress host = InetAddress.getByName(props.getProperty("host"));
        int port = Integer.parseInt(props.getProperty("port"));
        telnetClient.connect(host, port);
        readPrintParse(telnetClient.getInputStream());
    }
/*
    public static void main(String[] args) throws SocketException, IOException {
        new Controller();
    }

     * 
     */
   }