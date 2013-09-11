package telnet.player;

import java.util.Deque;
import java.util.logging.Logger;

public class PLayerController {

    private final static Logger log = Logger.getLogger(PLayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;
    private ActionGenerator actionGenerator = new ActionGenerator();
    private RegexWorker rw = new RegexWorker();

    public PLayerController() {
    }

    public Deque<Action> processGameData(String gameData) {
        log.fine(playerCharacter.getFlags().toString());
        rw.parseAndUpdatePlayerCharacter(gameData);
        log.fine("updated character??...I think...");
        return actionGenerator.generateActions();
    }
}