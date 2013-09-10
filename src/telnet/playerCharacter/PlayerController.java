package telnet.playerCharacter;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Logger;

public class PlayerController {

    private final static Logger log = Logger.getLogger(PlayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;
    private CommandProcessor cp = new CommandProcessor();
    
    public PlayerController(/*Map<PCF, Boolean> flags*/) {
    }

    public Queue<Command> processGameData(String gameData) {
        log.fine(playerCharacter.getFlags().toString());
        RegexWorker rw = new RegexWorker();
        rw.parseAndUpdatePlayerCharacter(gameData);

        Queue<Command> commands = cp.doLogic();

        return commands;
    }
}