package telnet.playerCharacter;

import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class CommandProcessor {

    private final static Logger log = Logger.getLogger(PlayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;
    private boolean backstab = false;
    private boolean confuse = false;
    private boolean corpse = false;
    private boolean doping = false;
    private boolean enervate = false;
    private boolean heartplung = false;
    private boolean healing = false;
    private boolean loggedIn = false;

    public CommandProcessor() {
    }

    private EnumSet confuseEnums() {
        EnumSet es = EnumSet.noneOf(CmdEnum.class);
        es.add(CmdEnum.CONFUSE);
        es.add(CmdEnum.BACKSTAB);
        return es;
    }

    private EnumSet corpseEnums() {
        EnumSet es = EnumSet.noneOf(CmdEnum.class);
        es.add(CmdEnum.DRAW);
        es.add(CmdEnum.PROCESS);
        return es;
    }

    private void populate() {
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

    public EnumSet doLogic() {
        Map<Flags, Boolean> flaggs = Player.INSTANCE.getFlags();
        EnumSet setOfCommands = EnumSet.noneOf(CmdEnum.class);
        return setOfCommands;
    }
}
