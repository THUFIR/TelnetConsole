package telnet.game;

import java.lang.Boolean;
import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

public enum PlayerCharacter {

    //new EnumMap<Country, String>
    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(PlayerCharacter.class.getName());
    private PlayerStats stats = new PlayerStats();
    private PlayerFlags flags = new PlayerFlags();
    private Map<PlayerCharacterFlags, Boolean> ef = new EnumMap(PlayerCharacterFlags.class);

    private PlayerCharacter() {
        ef.put(PlayerCharacterFlags.CORPSE, false);
        ef.put(PlayerCharacterFlags.FIGHT, false);
        ef.put(PlayerCharacterFlags.FLIGHT, false);
        ef.put(PlayerCharacterFlags.LOGGED_IN, false);
    }

    public Queue<Command> processRemoteOutput(String remoteOutputMessage) {
        log.fine("trying to process...");
        RegexWorker regexWorker = new RegexWorker();
        regexWorker.parseAndUpdatePlayerCharacter(remoteOutputMessage);
        PlayerLogic playerLogic = new PlayerLogic();
        Queue<Command> newCommands = playerLogic.doLogic();
        for (Command c : newCommands) {
            log.fine(c.toString());
        }
        return newCommands;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public PlayerFlags getFlags() {
        log.fine(flags.toString());
        return flags;
    }

    public void setFlags(PlayerFlags flags) {
        this.flags = flags;
    }
}