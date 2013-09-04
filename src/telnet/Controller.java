package telnet;

import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.net.telnet.TelnetClient;

public final class Controller implements Runnable, Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteOutputWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker remoteDataQueueWorker = new CharacterDataQueueWorker();
    private Regex regex = new Regex();
    private final ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue();
    private final ConcurrentLinkedQueue<Command> commandsQueue = new ConcurrentLinkedQueue();
    private Fight fight = new Fight();
    private Stats s = Stats.INSTANCE;

    private Controller() {
    }

    public void readPrintParse() throws SocketException, IOException {
        remoteOutputWorker.print(telnetClient.getInputStream(), remoteCharDataQueue);
        localInputReader.read();
        localInputReader.addObserver(this);
        remoteDataQueueWorker.read(remoteCharDataQueue);
        remoteDataQueueWorker.addObserver(this);
    }

    private void sendCommand(String command) {
        OutputStream outputStream = telnetClient.getOutputStream();
        if (command != null) {
            try {
                byte b = 10;
                byte[] bytes = command.getBytes();
                outputStream.write(bytes);
                outputStream.write(10);
                outputStream.flush();
            } catch (IOException | NullPointerException ex) {
                out.println("Controller.sendCommand.no valid command\t" + command + "\t" + ex);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String command = "help";

        if (o instanceof CharacterDataQueueWorker) {
            String remoteOutputMessage = remoteDataQueueWorker.getFinalData();
            regex.parse(remoteOutputMessage);
            Queue<String> commands = fight.getCommands();
            Iterator<String> it = commands.iterator();
            while (it.hasNext()) {
                command = commands.remove();
                sendCommand(command);
            }
            s.peace();
        }

        if (o instanceof ConsoleReader) {
            command = localInputReader.getCommand();
            sendCommand(command);
        }
    }

    @Override
    public void run() {
        try {
            Properties props = PropertiesReader.getProps();
            InetAddress host = InetAddress.getByName(props.getProperty("host"));
            int port = Integer.parseInt(props.getProperty("port"));
            telnetClient.connect(host, port);
          //  outputStream = telnetClient.getOutputStream();
            readPrintParse();
        } catch (UnknownHostException ex) {
            out.println(ex);
        } catch (SocketException ex) {
            out.println(ex);
        } catch (IOException ex) {
            out.println(ex);
        }
    }

    public static void main(String[] args) throws SocketException, IOException {
        new Controller().run();
    }
}
