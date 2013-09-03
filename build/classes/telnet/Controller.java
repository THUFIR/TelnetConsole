package telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.net.telnet.TelnetClient;

public final class Controller implements Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamReader serverReader = new InputStreamReader();
    private ConsoleReader consoleReader = new ConsoleReader();
    private DataProcessor dataProcessor = new DataProcessor();
    private Regex regex = new Regex();
    private final ConcurrentLinkedQueue<Character> telnetData = new ConcurrentLinkedQueue();
    private OutputStream outputStream;
    private Fight fight = new Fight();
    private Stats s = Stats.INSTANCE;

    public void readPrintParse(final InputStream inputStream) throws SocketException, IOException {
        serverReader.print(inputStream, telnetData);
        consoleReader.read();
        consoleReader.addObserver(this);
        dataProcessor.read(telnetData);
        dataProcessor.addObserver(this);
    }

    private void sendCommand(String command) {
        if (command != null) {
            try {
                byte b = 10;
                byte[] bytes = command.getBytes();
                outputStream.write(bytes);
                outputStream.write(10);
                outputStream.flush();
            } catch (IOException | NullPointerException ex) {
                //out.println("Controller.sendCommand.no valid command\t" + command + "\t" + ex);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String command = "help";

        if (o instanceof DataProcessor) {
            String data = dataProcessor.getFinalData();
            regex.parse(data);
            Queue<String> commands = fight.getCommands();
            Iterator<String> it = commands.iterator();
            while (it.hasNext()) {
                command = commands.remove();
                sendCommand(command);
            }
            s.peace();
        }

        if (o instanceof ConsoleReader) {
            command = consoleReader.getCommand();
            sendCommand(command);
        }
    }

    public Controller() throws SocketException, IOException {
        Properties props = PropertiesReader.getProps();
        InetAddress host = InetAddress.getByName(props.getProperty("host"));
        int port = Integer.parseInt(props.getProperty("port"));
        telnetClient.connect(host, port);
        outputStream = telnetClient.getOutputStream();
        readPrintParse(telnetClient.getInputStream());
    }

    public static void main(String[] args) throws SocketException, IOException {
        new Controller();
    }
}