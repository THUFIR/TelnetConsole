package telnet.player;

import java.util.EnumSet;
import java.util.logging.Logger;

public class PLayerController {

    private final static Logger log = Logger.getLogger(PLayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;
    private ActionGenerator cp = new ActionGenerator();
    
    public PLayerController() {
    }

    public EnumSet processGameData(String gameData) {
        log.fine(playerCharacter.getFlags().toString());
        RegexWorker rw = new RegexWorker();
        rw.parseAndUpdatePlayerCharacter(gameData);
        return cp.generateActions();
    }
}