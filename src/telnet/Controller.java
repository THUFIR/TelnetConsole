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
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.commons.net.telnet.TelnetClient;

public final class Controller implements Runnable, Observer {

    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteOutputWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker remoteDataQueueWorker = new CharacterDataQueueWorker();
    private RemoteOutputRegexMessageWorker remoteMessageWorker = new RemoteOutputRegexMessageWorker();
    private ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Command> commandsQueue = new ConcurrentLinkedQueue();
    private CharacterState characterState = new CharacterState();

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
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        while (!commandsQueue.isEmpty()) {
            try {
                commandBytes = commandsQueue.remove().getCommand().getBytes();
                outputStream.write(commandBytes);
                outputStream.write(10);
                outputStream.flush();
                Thread.sleep(10);
            } catch (IOException | NoSuchElementException ex) {
                break;
            } finally {
                break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof CharacterDataQueueWorker) {
            String remoteOutputMessage = remoteDataQueueWorker.getFinalData();
            remoteMessageWorker.parseWithRegex(remoteOutputMessage, commandsQueue);
            if (characterState.isFighting()) {
                Command b = new Command("backstab");
                Command h = new Command("heartplunge");
                Command e = new Command("enervate");
                Command d = new Command("dart");
                Command c = new Command("confuse");
                commandsQueue.add(b);
                commandsQueue.add(h);
                commandsQueue.add(e);
                commandsQueue.add(d);
                commandsQueue.add(c);
            } else {
                Command bk = new Command("berserk 0");
                commandsQueue = new ConcurrentLinkedQueue<>();
            }
            sendCommands();
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
