package telnet;

import telnet.game.Command;
import telnet.game.PlayerCharacter;
import static java.lang.System.out;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;
import telnet.connection.CharacterDataQueueWorker;
import telnet.connection.ConsoleReader;
import telnet.connection.InputStreamWorker;
import telnet.connection.PropertiesReader;

public final class Controller implements Runnable, Observer {

    private Logger log = Logger.getLogger(Controller.class.getName());
    private TelnetClient telnetClient = new TelnetClient();
    private InputStreamWorker remoteInputStreamWorker = new InputStreamWorker();
    private ConsoleReader localInputReader = new ConsoleReader();
    private CharacterDataQueueWorker characterDataQueueWorker = new CharacterDataQueueWorker();
    private ConcurrentLinkedQueue<Character> remoteCharDataQueue = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Command> commandsQueue = new ConcurrentLinkedQueue<>();
    private PlayerCharacter playerCharacter = PlayerCharacter.INSTANCE;

    private Controller() {
    }

    public void startReadPrintThreads() throws SocketException, IOException {
        remoteInputStreamWorker.print(telnetClient.getInputStream(), remoteCharDataQueue);
        localInputReader.read();
        localInputReader.addObserver(this);
        characterDataQueueWorker.read(remoteCharDataQueue);
        characterDataQueueWorker.addObserver(this);
    }

    private void executeCommands(long delay) {
        byte[] commandBytes = null;
        OutputStream outputStream = telnetClient.getOutputStream();
        String commandString = null;
        while (!commandsQueue.isEmpty()) {
            try {
                commandBytes = commandsQueue.remove().getCommand().getBytes();
                outputStream.write(commandBytes);
                outputStream.write(13);
                outputStream.write(10);
                outputStream.flush();
                commandString = new String(commandBytes, "UTF-8");
                log.fine(commandString + "\t" + commandBytes);
                Thread.sleep(delay);   //don't hammer the server???  in microseconds
            } catch (InterruptedException | IOException | NoSuchElementException ex) {
            } finally {
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        long delay = 0;
        Queue<Command> newCommands = new LinkedList<>();
        log.fine("updating...");
        try {
            if (o instanceof CharacterDataQueueWorker) {
                String remoteOutputMessage = characterDataQueueWorker.getFinalData();
                log.fine("starting regex.." + remoteOutputMessage);
                newCommands = playerCharacter.processRemoteOutput(remoteOutputMessage);
                delay = 500;
            }
        } catch (NullPointerException npe) {
            log.fine(npe.toString());
        }

        if (o instanceof ConsoleReader) {
            String commandString = localInputReader.getCommand();
            Command command = new Command(commandString);
            newCommands.add(command);
        }
        for (Command c : newCommands) {
            log.fine(c.toString());
        }
        commandsQueue.addAll(newCommands);
        executeCommands(delay);
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
