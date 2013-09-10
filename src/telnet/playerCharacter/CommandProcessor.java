package telnet.playerCharacter;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Logger;

public class CommandProcessor {

    private final static Logger log = Logger.getLogger(PlayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;

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

    public EnumSet doLogic() {
        log.info(playerCharacter.getFlags().toString());
        EnumSet es = EnumSet.noneOf(CmdEnum.class);
        for (Entry<PCF, Boolean> entry : playerCharacter.getFlags().entrySet()) {
            PCF key = entry.getKey();
            Boolean val = entry.getValue();
            switch (key) {
                case CORPSE:
                    log.info("corpse detected..");
                    es.addAll(corpseEnums());
                    break;
            }
        }
        return es;
    }
}
