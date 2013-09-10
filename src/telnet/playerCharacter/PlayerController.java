package telnet.playerCharacter;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Logger;

public class PlayerController {

    private final static Logger log = Logger.getLogger(PlayerController.class.getName());
    private Player playerCharacter = Player.INSTANCE;
//    private Map<PCF, Boolean> flags = new EnumMap(PCF.class);
    
    public PlayerController(/*Map<PCF, Boolean> flags*/) {
//        playerCharacter.setFlags(flags);
    }

    private Queue<Command> confuse() {
        Queue<Command> commands = new LinkedList<>();
        Command confuse = new Command("confuse");
        Command backstab = new Command("backstab");
        Command heartplunge = new Command("heartplung");
        Command enervate = new Command("enervate");
        commands.add(confuse);
        commands.add(heartplunge);
        commands.add(backstab);
        commands.add(enervate);
        commands.add(confuse);
        Map<PCF, Boolean> flag = new EnumMap(PCF.class);
        flag.put(PCF.CONFUSE, false);
        playerCharacter.putFlag(flag);
        return commands;
    }

    private Queue<Command> corpse() {
        Queue<Command> commands = new LinkedList<>();
        Command draw = new Command("draw");
        Command processCorpse = new Command("process corpse");
        Command getAll = new Command("get all");
        Command monitor = new Command("monitor");
        Command glance = new Command("glance");
        commands.add(draw);
        commands.add(processCorpse);
        commands.add(getAll);
        commands.add(monitor);
        commands.add(glance);
        Map<PCF, Boolean> f = new EnumMap(PCF.class);
        f.put(PCF.CORPSE, true);
        playerCharacter.putFlag(f);
//        playerCharacter.setFlags(f);
        return commands;
    }

    private Queue<Command> healing() {
        log.fine(playerCharacter.toString());
        Queue<Command> commands = new LinkedList<>();
        if (playerCharacter.getStats().getEndorphine() > 0) {
            Command e = new Command("endorphine 5");
            commands.add(e);
        }
        if (playerCharacter.getStats().getBerserk() > 0) {
            Command b = new Command("berserk 0");
            commands.add(b);
        }
        Command m = new Command("monitor");
        Map<PCF, Boolean> flag = new EnumMap(PCF.class);
        flag.put(PCF.HEALING, false);
        playerCharacter.putFlag(flag);
        commands.add(m);
        return commands;
    }

    public Queue<Command> doLogic(String s) {
        log.fine(playerCharacter.getFlags().toString());

        RegexWorker rw = new RegexWorker();
        rw.parseAndUpdatePlayerCharacter(s);

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

        /*
        if (flags.) {
        commands.addAll(confuse());
        }
        if (playerCharacter.getFlags().isCorpse()) {
        commands.addAll(corpse());
        }
        if (playerCharacter.getFlags().isHealing()) {
        commands.addAll(healing());
        }
        if (!playerCharacter.getFlags().isLoggedIn()) {
        commands = new LinkedList<>();
        
        }


        for (Entry<PCF, Boolean> entry : playerCharacter.getFlags().entrySet()) {
            playerCharacter.setFlags(flags);
        }*/
        
        return commands;
    }
}