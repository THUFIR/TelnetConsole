package telnet.player;

import java.util.ArrayDeque;
import java.util.Deque;

public enum Flag {

    BACKSTAB() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    CONFUSE() {

        @Override
        Deque<Action> getActionsForState() {
             Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    CORPSE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    HEALING() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    DOPING() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    ENERVATE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    HEARTPLUNGE() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            Action b = new Action("backstab");
            Action c = new Action("confuse");
            actions.add(b);
            actions.add(c);
            return actions;
        }
    },
    LOGGEDIN() {

        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    };

    abstract Deque<Action> getActionsForState();
}