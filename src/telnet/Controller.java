package telnet;

import java.util.logging.Level;
import telnet.player.Player;
import static java.lang.System.out;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;
import telnet.connection.CharacterDataQueueWorker;
import telnet.connection.UserActions;
import telnet.connection.ConsoleReader;
import telnet.connection.InputStreamWorker;
import telnet.connection.PropertiesReader;
import telnet.player.Action;
import telnet.player.PLayerController;

public final class Controller implements Runnable, Observer {

    private Logger log = Logger.getLogger(Controller.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteInputStreamWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker characterDataQueueWorker = new CharacterDataQueueWorker();
    private ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue<>();
    private EnumSet actions = EnumSet.noneOf(Action.class);
    private Player playerCharacter = Player.INSTANCE;
    private PLayerController cp = new PLayerController();

    private Controller() {
    }

    public void startReadPrintThreads() throws SocketException, IOException {
        remoteInputStreamWorker.print(telnetClient.getInputStream(), remoteCharDataQueue);
        localInputReader.read();
        localInputReader.addObserver(this);
        characterDataQueueWorker.read(remoteCharDataQueue);
        characterDataQueueWorker.addObserver(this);
    }

    private void ExecuteCommandsEnums(long delay) {
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        String commandString = null;
        Queue<Action> commandsQueue = new PriorityQueue<>(actions);
        while (!commandsQueue.isEmpty()) {
            try {
                commandBytes = commandsQueue.remove().toString().getBytes();
                outputStream.write(commandBytes);
                outputStream.write(13);
                outputStream.write(10);
                outputStream.flush();
                commandString = new String(commandBytes, "UTF-8");
                log.log(Level.FINE, "{0}\t{1}", new Object[]{commandString, commandBytes});
                Thread.sleep(delay);   //don't hammer the server???  in microseconds
            } catch (InterruptedException | IOException | NoSuchElementException ex) {
            } finally {
            }
        }
    }

    private void executeUserAction(UserActions cmd) throws IOException {
        byte[] commandBytes = cmd.getCommand().getBytes();
        OutputStream outputStream = telnetClient.getOutputStream();
        outputStream.write(commandBytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }

    @Override
    public void update(Observable o, Object arg) {
        long delay = 0;
        Deque<Action> newCommands = new ArrayDeque<>();
        log.fine("updating...");
        actions = EnumSet.noneOf(Action.class);
        //EnumSet newActions = EnumSet.noneOf(Action.class);
        Deque<Action> newActions = new ArrayDeque<>();
        try {
            if (o instanceof CharacterDataQueueWorker) {
                String remoteOutputMessage = characterDataQueueWorker.getFinalData();
                log.log(Level.FINE, "starting regex..{0}", remoteOutputMessage);
                newActions = cp.processGameData(remoteOutputMessage);
//                newCommands = new PriorityQueue<>(newActions);\
                newCommands = new ArrayDeque<>(newActions);
                delay = 500;
            }
            actions.addAll(newCommands);
            ExecuteCommandsEnums(delay);
        } catch (NullPointerException npe) {
            log.fine(npe.toString());
        }

        if (o instanceof ConsoleReader) {
            try {
                String commandString = localInputReader.getCommand();
                UserActions action = new UserActions(commandString);
                executeUserAction(action);
            } catch (IOException ex) {
            }
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
