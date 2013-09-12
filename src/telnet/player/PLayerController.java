package telnet.player;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class PLayerController {

    private final static Logger log = Logger.getLogger(PLayerController.class.getName());
    private Player player = Player.INSTANCE;  //single player only, ever
    private RegexWorker rw = new RegexWorker();

    public PLayerController() {
    }

    public Deque<Action> processGameData(String gameData) {
        rw.parseAndUpdatePlayerCharacter(gameData);
        Deque<Action> actions = new ArrayDeque<>();
        Flag flag = null;
        for (Entry<Flag, Boolean> entry : player.getFlags().entrySet()) {
            if (entry.getKey() != Flag.LOGGEDIN) {
                if (entry.getValue()) {
                    flag = entry.getKey();
                    log.info("detected\t" + flag);
                    player.setFlag(flag, false);
                    actions.addAll(flag.getActionsForState());
                }
            }
        }
        return actions;
    }
}