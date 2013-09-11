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
        EnumSet actions = EnumSet.noneOf(Action.class);
        actions.add(Action.CONFUSE);
        actions.add(Action.BACKSTAB);
        return actions;
    }

    private EnumSet killedMonsterActions() {
        EnumSet actions = EnumSet.noneOf(Action.class);
        actions.add(Action.DRAW);
        actions.add(Action.PROCESS);
        Map<Flag, Boolean> pflags = Player.INSTANCE.getFlags();
        pflags.put(Flag.CORPSE, false);
        Player.INSTANCE.setFlags(pflags);
        return actions;
    }

    private void initBooleanState() {
        Map<Flag, Boolean> flaggs = Player.INSTANCE.getFlags();
        log.fine(flaggs.toString());
        for (Entry<Flag, Boolean> entry : playerCharacter.getFlags().entrySet()) {
            Flag key = entry.getKey();
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
        log.fine("trying actions...");
        initBooleanState();
        EnumSet setOfActions = EnumSet.noneOf(Action.class);
        if (loggedIn) {
            if (corpse) {
                setOfActions.addAll(killedMonsterActions());
            }
        }
        return setOfActions;
    }
}
