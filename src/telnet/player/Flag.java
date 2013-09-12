package telnet.player;

import java.util.ArrayDeque;
import java.util.Deque;

public enum Flag {

    BACKSTAB() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    },
    CONFUSE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action confuse = new Action("confuse");
            actions.add(confuse);
            return actions;
        }
    },
    CORPSE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action draw = new Action("draw");
            Action transfuse = new Action("transfuse");
            Action process = new Action("process corpse");
            actions.add(draw);
            actions.add(process);
            return actions;
        }
    },
    HEALING() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action endorphine = new Action("endorphine 5");
            actions.add(endorphine);
            return actions;
        }
    },
    DOPING() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    },
    ENERVATE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action enervate = new Action("enervate");
            actions.add(enervate);
            return actions;
        }
    },
    HEARTPLUNGE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action heartplunge = new Action("heartplunge");
            actions.add(heartplunge);
            return actions;
        }
    },
    LOGGEDIN() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    }, FIGHTING() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action a = new Action("confuse");
            Action b = new Action("backstab");
            Action h = new Action("heartplunge");
            Action e = new Action("enervate");
            actions.add(b);
            actions.add(a);
            actions.add(b);
            actions.add(h);
            actions.add(e);
            return actions;
        }
    };

    abstract Deque<Action> getActionsForState();
}