package telnet;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class PlayerLogic {

    private final static Logger log = Logger.getLogger(PlayerLogic.class.getName());
    private PlayerState state = PlayerState.INSTANCE;

    public PlayerLogic() {
    }

    private Queue<Command> confuse() {
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
        state.setConfuse(false);
        return commands;
    }

    private Queue<Command> corpse() {
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
        state.setCorpse(false);
        return commands;
    }

    private Queue<Command> healing() {
        Queue<Command> commands = new LinkedList<>();
        int end = state.getEndorphine();
        if (state.getEndorphine() > 0) {
            Command e = new Command("endorphine 0");
            commands.add(e);
        }
        if (state.getBerserk() > 0) {
            Command b = new Command("berserk 0");
            commands.add(b);
        }
        Command m = new Command("monitor");
        commands.add(m);
        return commands;
    }

    public Queue<Command> getCommands() {
        Queue<Command> commands = new LinkedList<>();
        if (state.isConfuse()) {
            commands.addAll(confuse());
        }
        if (state.isCorpse()) {
            commands.addAll(corpse());
        }
        if (state.isHealing()) {
            commands.addAll(healing());
        }
        if (!state.isLoggedIn()) {
            commands = new LinkedList<>();
        }
        return commands;
    }
}