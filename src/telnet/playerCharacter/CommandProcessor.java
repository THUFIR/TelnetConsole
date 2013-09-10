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



    public Queue<Command> doLogic() {
        log.fine(playerCharacter.getFlags().toString());
        Queue<Command> commands = new LinkedList<>();

        for (Entry<PCF, Boolean> entry : playerCharacter.getFlags().entrySet()) {
            PCF key = entry.getKey();
            Boolean val = entry.getValue();
            switch (key) {
                case CONFUSE:
                    log.info("ok, confuzed..");
                    break;
            }
        }


        return commands;
    }
}
