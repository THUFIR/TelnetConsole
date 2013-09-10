package telnet.playerCharacter;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

public enum Player {

    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(Player.class.getName());
    private Stats stats = new Stats();
    private Map<Flags, Boolean> ef = new EnumMap(Flags.class);

    private Player() {
        ef.put(Flags.BACKSTAB, false);
        ef.put(Flags.CONFUSE, false);
        ef.put(Flags.CORPSE, false);
        ef.put(Flags.DOPING, false);
        ef.put(Flags.ENERVATE, false);
        ef.put(Flags.HEALING, false);
        ef.put(Flags.HEARTPLUNGE, false);
        ef.put(Flags.LOGGEDIN, false);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Map<Flags, Boolean> getFlags() {
        return ef;
    }

    public void setFlags(Map<Flags, Boolean> newFlags) {
        ef.putAll(newFlags);
        log.fine(ef.toString());
    }

    public void putFlag(Map<Flags, Boolean> flag) {
        ef.putAll(flag);
    }
}