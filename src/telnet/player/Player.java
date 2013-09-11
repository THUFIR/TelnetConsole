package telnet.player;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

public enum Player {

    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(Player.class.getName());
    private Stats stats = new Stats();
    private Map<Flag, Boolean> flagMap = new EnumMap(Flag.class);

    private Player() {
        flagMap.put(Flag.BACKSTAB, false);
        flagMap.put(Flag.CONFUSE, false);
        flagMap.put(Flag.CORPSE, false);
        flagMap.put(Flag.DOPING, false);
        flagMap.put(Flag.ENERVATE, false);
        flagMap.put(Flag.HEALING, false);
        flagMap.put(Flag.HEARTPLUNGE, false);
        flagMap.put(Flag.LOGGEDIN, false);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Map<Flag, Boolean> getFlags() {
        return flagMap;
    }

    void setFlag(Flag flag, boolean b) {
        flagMap.put(flag, b);
    }
}