package telnet;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class PlayerLogic {

    private final static Logger log = Logger.getLogger(PlayerLogic.class.getName());
//    private PlayerCharacter pc = new PlayerCharacter();

    public PlayerLogic() {
    }

    private Queue<Command> confuse(PlayerCharacter pc) {
        Queue<Command> commands = new LinkedList<>();
        Command c = new Command("confuse");
        Command b = new Command("backstab");
        Command hp = new Command("heartplung");
        Command e = new Command("enervate");
        commands.add(c);
        commands.add(hp);
        commands.add(b);
        commands.add(e);
        commands.add(c);
        pc.setConfuse(false);
        return commands;
    }

    private Queue<Command> corpse(PlayerCharacter pc) {
        Queue<Command> commands = new LinkedList<>();
        Command d = new Command("draw");
        Command p = new Command("process corpse");
        Command ga = new Command("get all");
        Command m = new Command("monitor");
        Command g = new Command("glance");
        commands.add(d);
        commands.add(p);
        commands.add(ga);
        commands.add(m);
        commands.add(g);
        pc.setCorpse(false);
        return commands;
    }

    private Queue<Command> healing(PlayerCharacter pc) {
        Queue<Command> commands = new LinkedList<>();
        if (pc.getEndorphine() > 0) {
            Command e = new Command("endorphine 5");
            commands.add(e);
        }
        if (pc.getBerserk() > 0) {
            Command b = new Command("berserk 0");
            commands.add(b);
        }
        Command m = new Command("monitor");
        commands.add(m);
        return commands;
    }

    public Queue<Command> getCommands(PlayerCharacter pc) {
        Queue<Command> commands = new LinkedList<>();
        if (pc.isConfuse()) {
            commands.addAll(confuse(pc));
        }
        if (pc.isCorpse()) {
            commands.addAll(corpse(pc));
        }
        if (pc.isHealing()) {
            commands.addAll(healing(pc));
        }
        if (!pc.isLoggedIn()) {
            commands = new LinkedList<>();
        }
        return commands;
    }
}