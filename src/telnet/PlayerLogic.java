package telnet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Logger;

public class PlayerLogic {

    
    private final static Logger LOG = Logger.getLogger(PlayerLogic.class.getName());
    private PlayerState state = PlayerState.INSTANCE;

    public PlayerLogic() {
    }

    void setMonitor(List<Entry> stringEntries) {
        String key = null, val = null;
        for (Entry e : stringEntries) {
            key = e.getKey().toString();
            key = key.toLowerCase();
            val = e.getValue().toString();
            if (key.contains("hp")) {
                key = "hp";
            }
            if (key.contains("darts")) {
                key = "darts";
            }
            if (key.contains("blood")) {
                key = "blood";
            }
            switch (key) {
                case "hp":
                    state.setHP(val);
                case "cp":
                    state.setCP(val);
                case "adrenaline":
                    state.setAdrenaline(val);
                case "endorphine":
                    state.setEndorphine(val);
                case "berserk":
                    state.setBerserk(val);
                case "none":
                    state.setEnemy(val);
                case "darts":
                    state.setDarts(val);
                case "blood":
                    state.setBlood(val);
                case "grafts":
                    state.setGrafts(val);
            }
        }
    }

    private Queue<Command> confuse() {
        Queue<Command> commands = new LinkedList<>();
        Command c = new Command("confuse");
        commands.add(c);
        state.setConfuse(false);
        return commands;
    }

    private Queue<Command> corpse() {
        Queue<Command> commands = new LinkedList<>();
        Command d = new Command("draw");
        Command p = new Command("process corpse");
        Command ga = new Command("get all");
        Command g = new Command("glance");
        Command m = new Command("monitor");
        commands.add(d);
        commands.add(p);
        commands.add(ga);
        commands.add(g);
        commands.add(m);
        state.setCorpse(false);
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
        if (!state.isLoggedIn()) {
            commands = new LinkedList<>();
        }
        for (Command command : commands) {
            LOG.fine(command.toString());
        }
        return commands;
    }
}