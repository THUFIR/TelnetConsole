package telnet.player;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class ActionGenerator {

    private final static Logger log = Logger.getLogger(PLayerController.class.getName());

    public ActionGenerator() {
    }

    public Deque<Action> generateActions() {
        log.fine("trying actions...");
        Deque<Action> actions = new ArrayDeque<>();
        Flag flag = null;
        for (Entry<Flag, Boolean> entry : Player.INSTANCE.getFlags().entrySet()) {
            if (Flag.LOGGEDIN != entry.getKey()) {
                if (entry.getValue()) {
                    flag = entry.getKey();
                    Player.INSTANCE.setFlag(flag, false);
                    actions.addAll(flag.getActionsForState());
                }
            }
        }
        return actions;
    }
}
