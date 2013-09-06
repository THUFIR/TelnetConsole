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
        logic.setMonitor(stringEntries);
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
}