package telnet;

import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

class PlayerCharacter {

    private PlayerState state = PlayerState.INSTANCE;
    private PlayerLogic logic = new PlayerLogic();

    public PlayerCharacter() {
    }

    public void setMonitor(List<Entry> stringEntries) {
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

    public void setConfuse(boolean confuse) {
        state.setConfuse(confuse);
    }

    boolean getConfuse() {
        return state.isConfuse();
    }

    Queue<Command> getCommands() {
        Queue<Command> commands = logic.getCommands();
        return commands;
    }

    void setCorpse(boolean corpse) {
        state.setCorpse(corpse);
    }

    boolean isLoggedIn() {
        return state.isLoggedIn();
    }

    void setLoggedIn(boolean loggedIn) {
        state.setLoggedIn(loggedIn);
    }

    void setDoping(boolean healing) {
        state.setDoping(healing);
    }

    public String toString() {
        return "foo";
    }

    void setHealing(boolean healing) {
        state.setHealing(true);
    }
}