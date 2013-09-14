package telnet.player;

import java.util.Deque;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerController {

    private final static Logger log = Logger.getLogger(PlayerController.class.getName());
    private Player player = Player.INSTANCE;  //single player only, ever
    private RegexWorker rw = new RegexWorker();
    // private  TelnetConnection tc;

    public PlayerController() {
    }

    public Deque<Action> processGameData(String gameData) {
        Deque<Action> actions = rw.parseAndUpdatePlayerCharacter(gameData);
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
        return actions;
    }
}
