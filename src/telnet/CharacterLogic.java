package telnet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

class CharacterLogic {

    private CharacterState state = CharacterState.INSTANCE;

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
                state.setIsFighting(true);
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

    Queue<Command> getFightCommands() {
        Queue<Command> fightCommands = new LinkedList<>();
        Command b = new Command("backstab");
        Command h = new Command("heartplunge");
        Command e = new Command("enervate");
        Command c = new Command("confuse");
        fightCommands.add(b);
        fightCommands.add(h);
        fightCommands.add(e);
        fightCommands.add(c);
        return fightCommands;
    }
}
