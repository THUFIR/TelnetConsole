package telnet.playerCharacter;

import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

public enum Player {

    //new EnumMap<Country, String>
    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(Player.class.getName());
    private Stats stats = new Stats();
    private Map<PCF, Boolean> ef = new EnumMap(PCF.class);

    private Player() {
        ef.put(PCF.BACKSTAB, false);
        ef.put(PCF.CONFUSE, false);
        ef.put(PCF.CORPSE, false);
        ef.put(PCF.DOPING, false);
        ef.put(PCF.ENERVATE, false);
        ef.put(PCF.HEALING, false);
        ef.put(PCF.HEARTPLUNGE, false);
        ef.put(PCF.LOGGEDIN, false);
    }

    /*
    public Queue<Command> processRemoteOutput(String remoteOutputMessage) {
        log.fine("trying to process...");
        RegexWorker regexWorker = new RegexWorker();
        regexWorker.parseAndUpdatePlayerCharacter(remoteOutputMessage);
        CommandProcessor playerLogic = new CommandProcessor();
        Queue<Command> newCommands = playerLogic.doLogic();
        for (Command c : newCommands) {
            log.info(c.toString());
        }
        return newCommands;
    }
*/
    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Map<PCF, Boolean> getFlags() {
        return ef;
    }

    public void setFlags(Map<PCF, Boolean> newFlags) {
        ef.putAll(newFlags);
        log.fine(ef.toString());
    }

    public void putFlag(Map<PCF, Boolean> flag) {
        ef.putAll(flag);
    }
}