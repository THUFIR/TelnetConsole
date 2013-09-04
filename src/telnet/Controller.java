package telnet;

import static java.lang.System.out;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.net.telnet.TelnetClient;

public final class Controller implements Runnable, Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteOutputWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker remoteDataQueueWorker = new CharacterDataQueueWorker();
    private RemoteOutputRegexMessageWorker remoteMessageWorker = new RemoteOutputRegexMessageWorker();
    private final ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue();
    private final ConcurrentLinkedQueue<Command> commandsQueue = new ConcurrentLinkedQueue();

    private Controller() {
    }

    public void startReadPrintThreads() throws SocketException, IOException {
        remoteOutputWorker.print(telnetClient.getInputStream(), remoteCharDataQueue);
        localInputReader.read();
        localInputReader.addObserver(this);
        remoteDataQueueWorker.read(remoteCharDataQueue);
        remoteDataQueueWorker.addObserver(this);
    }

    private void sendCommands() {
        String commandString = null;
        Iterator it = commandsQueue.iterator();
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        while (it.hasNext()) {
            try {
                commandString = commandsQueue.remove().getCommand();
                out.println("trying command\t" + commandString);
                commandBytes = commandString.getBytes();
                outputStream.write(commandBytes);
                outputStream.write(10);
                outputStream.flush();
            } catch (IOException | NoSuchElementException ex) {
                out.println("sendCommand\n" + ex);
                break;
            } finally {
                out.println("sendCommands..finally");
                break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof CharacterDataQueueWorker) {
            String remoteOutputMessage = remoteDataQueueWorker.getFinalData();
            remoteMessageWorker.parseWithRegex(remoteOutputMessage);
            out.println("cdqw");
            //sendCommands();
        }

        if (o instanceof ConsoleReader) {
            String commandString = localInputReader.getCommand();
            Command command = new Command(commandString);
            commandsQueue.add(command);
            out.println("cr");
            sendCommands();
        }
    }

    @Override
    public void run() {
        try {
            Properties props = PropertiesReader.getProps();
            InetAddress host = InetAddress.getByName(props.getProperty("host"));
            int port = Integer.parseInt(props.getProperty("port"));
            telnetClient.connect(host, port);
            startReadPrintThreads();
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
