package telnet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;
import telnet.connection.CharacterDataQueueWorker;
import telnet.connection.ConsoleReader;
import telnet.connection.InputStreamWorker;
import telnet.connection.PropertiesReader;
import telnet.player.Action;
import telnet.player.PlayerController;

public  class Connection implements Observer, Runnable {

    private Logger log = Logger.getLogger(Connection.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteInputStreamWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker characterDataQueueWorker = new CharacterDataQueueWorker();
    private ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue<>();
    private Deque<Action> actions = new ArrayDeque<>();
    private PlayerController playerController = new PlayerController();

    public Connection() throws UnknownHostException, SocketException, IOException {
        Properties props = PropertiesReader.getProps();
        InetAddress host = InetAddress.getByName(props.getProperty("host"));
        int port = Integer.parseInt(props.getProperty("port"));
        telnetClient.connect(host, port);
    }

    public void startReadPrintThreads() throws SocketException, IOException {
        remoteInputStreamWorker.print(telnetClient.getInputStream(), remoteCharDataQueue);
        localInputReader.read();
        localInputReader.addObserver(this);
        characterDataQueueWorker.read(remoteCharDataQueue);
        characterDataQueueWorker.addObserver(this);
    }

    private void sendActions(long delay) {
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        while (!actions.isEmpty()) {
            try {
                Action action = actions.remove();
                sendAction(action);
                Thread.sleep(delay);
            } catch (InterruptedException | IOException | NoSuchElementException ex) {
            } finally {
            }
        }
    }

    private void sendAction(Action action) throws IOException {
        byte[] actionBytes = action.getCommand().getBytes();
        OutputStream outputStream = telnetClient.getOutputStream();
        outputStream.write(actionBytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }

    @Override
    public void update(Observable o, Object arg) {
        long delay = 0;
        try {
            if (o instanceof CharacterDataQueueWorker) {
                String remoteOutputMessage = characterDataQueueWorker.getFinalData();
                actions = playerController.processGameData(remoteOutputMessage);
                delay = 5;
            }
            sendActions(delay);
        } catch (NullPointerException npe) {
            log.fine(npe.toString());
        }

        if (o instanceof ConsoleReader) {
            try {
                String userInput = localInputReader.getCommand();
                Action action = new Action(userInput);
                sendAction(action);
            } catch (IOException ex) {
            }
        }
    }

    
    
    public static void main(String[] args) throws  UnknownHostException, SocketException, IOException {
        new Connection().run();
    }

    @Override
    public void run() {
        try {
            startReadPrintThreads();
        } catch (SocketException ex) {
        } catch (IOException ex) {
        }
    }
}
