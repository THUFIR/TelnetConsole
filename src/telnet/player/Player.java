package telnet.player;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

public enum Player {

    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(Player.class.getName());
    private Stats stats = new Stats();
    private Map<Flag, Boolean> ef = new EnumMap(Flag.class);

    private Player() {
        ef.put(Flag.BACKSTAB, false);
        ef.put(Flag.CONFUSE, false);
        ef.put(Flag.CORPSE, false);
        ef.put(Flag.DOPING, false);
        ef.put(Flag.ENERVATE, false);
        ef.put(Flag.HEALING, false);
        ef.put(Flag.HEARTPLUNGE, false);
        ef.put(Flag.LOGGEDIN, false);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Map<Flag, Boolean> getFlags() {
        return ef;
    }

    public void setFlags(Map<Flag, Boolean> newFlags) {
        ef.putAll(newFlags);
        log.fine(ef.toString());
    }

    public void putFlag(Map<Flag, Boolean> flag) {
        ef.putAll(flag);
    }
}