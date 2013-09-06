package telnet;

import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Logger;

class PlayerCharacter {

    
    private Logger log = Logger.getLogger(PlayerCharacter.class.getName());
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
                    setHP(val);
                case "cp":
                    setCP(val);
                case "adrenaline":
                    setAdrenaline(val);
                case "endorphine":
                    setEndorphine(val);
                case "berserk":
                    setBerserk(val);
                case "none":
                    setEnemy(val);
                case "darts":
                    setDarts(val);
                case "blood":
                    setBlood(val);
                case "grafts":
                    setGrafts(val);
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
        Queue<Command> commands = logic.getCommands(this);
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
        return state.toString();
    }

    void setHealing(boolean healing) {
        state.setHealing(true);
    }

    int getEndorphine() {
        return state.getEndorphine();
    }

    int getBerserk() {
        return state.getBerserk();
    }

    boolean isConfuse() {
        return state.isConfuse();
    }

    boolean isCorpse() {
        return state.isCorpse();
    }

    boolean isHealing() {
        return state.isHealing();
    }

    private void setGrafts(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setBlood(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setDarts(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setEnemy(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setBerserk(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setEndorphine(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setAdrenaline(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setCP(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setHP(String val) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}