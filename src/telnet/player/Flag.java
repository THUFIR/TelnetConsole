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
            return actions;
        }
    },
    CORPSE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action d = new Action("draw");
            Action t = new Action("transfuse");
            Action p = new Action("process corpse");
            actions.add(d);
            actions.add(p);
            return actions;
        }
    },
    HEALING() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action e = new Action("endorphine 5");
            actions.add(e);
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
            return actions;
        }
    },
    HEARTPLUNGE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    },
    LOGGEDIN() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    },    FIGHTING() {

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