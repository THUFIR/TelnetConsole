package telnet.game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class PlayerLogic {

    private final static Logger log = Logger.getLogger(PlayerLogic.class.getName());
    private PlayerCharacter playerCharacter = PlayerCharacter.INSTANCE;
    private PlayerFlags flags = new PlayerFlags();

    public PlayerLogic() {
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
        flags.setConfuse(false);
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
        flags.setCorpse(false);
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
        flags.setHealing(false);
        commands.add(m);
        return commands;
    }

    public Queue<Command> doLogic() {
        log.fine("should print...");
        flags = playerCharacter.getFlags();
        log.fine(flags.toString());
        Queue<Command> commands = new LinkedList<>();
        log.fine(playerCharacter.getFlags().toString());
        if (playerCharacter.getFlags().isConfuse()) {
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
        playerCharacter.setFlags(flags);
        return commands;
    }
}