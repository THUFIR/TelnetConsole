package telnet.player;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Deque;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import telnet.connection.TelnetConnection;

public class PlayerController {

    private final static Logger log = Logger.getLogger(PlayerController.class.getName());
    private static Player player = Player.INSTANCE;  //single player only, ever
    private static RegexWorker rw = new RegexWorker();
    private static TelnetConnection tc;

    public PlayerController() throws UnknownHostException, SocketException, IOException {
        tc = new TelnetConnection();
    }

    public static void processGameData() {
        String gameData = tc.getGameData();
        Deque<Action> actions = rw.parseAndUpdatePlayerCharacter(gameData);
        //Deque<Action> actions = new ArrayDeque<>();
        Flag flag = null;
        for (Entry<Flag, Boolean> entry : player.getFlags().entrySet()) {
            if (entry.getKey() != Flag.LOGGEDIN) {
                if (entry.getValue()) {
                    flag = entry.getKey();
                    log.log(Level.INFO, "detected\t{0}", flag);
                    player.setFlag(flag, false);
                    actions.addAll(flag.getActionsForState());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            processGameData();
        }
    }
}




/*
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
    }*/