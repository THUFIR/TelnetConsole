package telnet;

import static java.lang.System.out;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

public final class Controller implements Runnable, Observer {

    private final static Logger LOG = Logger.getLogger(Controller.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteInputStreamWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker remoteDataQueueWorker = new CharacterDataQueueWorker();
    private RemoteOutputRegexMessageWorker remoteMessageWorker = new RemoteOutputRegexMessageWorker();
    private final ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Command> commandsQueue = new ConcurrentLinkedQueue<>();
    private PlayerCharacter playerCharacter = new PlayerCharacter();

    private Controller() {
    }

    public void startReadPrintThreads() throws SocketException, IOException {
        remoteInputStreamWorker.print(telnetClient.getInputStream(), remoteCharDataQueue);
        localInputReader.read();
        localInputReader.addObserver(this);
        remoteDataQueueWorker.read(remoteCharDataQueue);
        remoteDataQueueWorker.addObserver(this);
    }

    private void sendCommands() {
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        String fileString = null;
        while (!commandsQueue.isEmpty()) {
            try {
                commandBytes = commandsQueue.remove().getCommand().getBytes();
                outputStream.write(commandBytes);
                outputStream.write(13);
                outputStream.write(10);
                outputStream.flush();
                fileString = new String(commandBytes, "UTF-8");
                LOG.log( Level.INFO, "{0}\t{1}", new Object[]{fileString, commandBytes});
                Thread.sleep(10);   //don't hammer the server???  in microseconds
            } catch (InterruptedException | IOException | NoSuchElementException ex) {
            } finally {
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof CharacterDataQueueWorker) {
            String remoteOutputMessage = remoteDataQueueWorker.getFinalData();
            remoteMessageWorker.parseWithRegex(remoteOutputMessage, commandsQueue);
            Queue<Command> newCommands = playerCharacter.getCommands();
            commandsQueue.addAll(newCommands);
        }

        if (o instanceof ConsoleReader) {
            String commandString = localInputReader.getCommand();
            Command command = new Command(commandString);
            commandsQueue.add(command);
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
