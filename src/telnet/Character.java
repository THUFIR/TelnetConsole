package telnet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

class Character {

    private CharacterState state = CharacterState.INSTANCE;
    private CharacterLogic logic = new CharacterLogic();

    public Character() {
    }

    public void setMonitor(List<Entry> stringEntries) {
        logic.setMonitor(stringEntries);
    }

    public void setFighting(boolean isFighting) {
        state.setIsFighting(isFighting);
    }

    boolean isFighting() {
        return state.isFighting();
    }

    Queue<Command> getFightCommands() {
        Queue<Command> fightCommands = logic.getFightCommands();
        return fightCommands;
    }
}