package telnet.game;

import java.util.Queue;
import java.util.logging.Logger;

public enum PlayerCharacter {

    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(PlayerCharacter.class.getName());
    private PlayerStats stats = new PlayerStats();
    private PlayerFlags flags = new PlayerFlags();
    private RegexWorker regexWorker = new RegexWorker();
    private PlayerLogic playerLogic = new PlayerLogic();

    private PlayerCharacter() {
    }

    public Queue<Command> processRemoteOutput(String remoteOutputMessage) {
        log.fine("trying to process..." + remoteOutputMessage);
        regexWorker.parseWithRegex(remoteOutputMessage);
        Queue<Command> commands = playerLogic.getCommands();
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