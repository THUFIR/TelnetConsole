package telnet.player;

import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class ActionGenerator {

    private final static Logger log = Logger.getLogger(PLayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;
    private boolean backstab = false;
    private boolean confuse = false;
    private boolean corpse = false;
    private boolean doping = false;
    private boolean enervate = false;
    private boolean heartplung = false;
    private boolean healing = false;
    private boolean loggedIn = false;

    public ActionGenerator() {
    }

    private EnumSet attackActions() {
        EnumSet es = EnumSet.noneOf(Actions.class);
        es.add(Actions.CONFUSE);
        es.add(Actions.BACKSTAB);
        return es;
    }

    private EnumSet healingActions() {
        EnumSet es = EnumSet.noneOf(Actions.class);
        es.add(Actions.DRAW);
        es.add(Actions.PROCESS);
        return es;
    }

    private void getPlayerState() {
        Map<Flags, Boolean> flaggs = Player.INSTANCE.getFlags();
        log.info(flaggs.toString());
        for (Entry<Flags, Boolean> entry : playerCharacter.getFlags().entrySet()) {
            Flags key = entry.getKey();
            Boolean val = entry.getValue();
            switch (key) {
                case BACKSTAB:
                    backstab = val;
                    break;
                case CONFUSE:
                    confuse = val;
                    break;
                case CORPSE:
                    corpse = val;
                    break;
                case DOPING:
                    doping = val;
                    break;
                case ENERVATE:
                    enervate = val;
                    break;
                case HEALING:
                    healing = val;
                    break;
                case LOGGEDIN:
                    loggedIn = val;
                    break;
            }
        }
    }

    public EnumSet generateActions() {
        Map<Flags, Boolean> flaggs = Player.INSTANCE.getFlags();
        EnumSet setOfCommands = EnumSet.noneOf(Actions.class);
        if (loggedIn) {
            if (corpse) {
                setOfCommands.addAll(healingActions());
            }
        }
        return setOfCommands;
    }
}
