package telnet.game;

import java.util.Queue;
import java.util.logging.Logger;

public enum PlayerCharacter {

    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(PlayerCharacter.class.getName());
    private PlayerStats stats;
    private PlayerFlags flags;
    private RegexWorker regexWorker = new RegexWorker();

    public Queue<Command> processRemoteOutput(String remoteOutputMessage) {
        log.fine("trying to process..." + remoteOutputMessage);
        regexWorker.parseWithRegex(remoteOutputMessage);
        Queue<Command> commands = PlayerLogic.getCommands();
        for (Command c : commands) {
            log.fine(c.toString());
        }
        return commands;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public PlayerFlags getFlags() {
        return flags;
    }

    public void setFlags(PlayerFlags flags) {
        this.flags = flags;
    }
}