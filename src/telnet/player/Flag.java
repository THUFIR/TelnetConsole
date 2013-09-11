package telnet.player;


import java.util.ArrayDeque;
import java.util.Deque;
 
public enum Flag {
 
    BACKSTAB() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    CONFUSE() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    CORPSE() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.DRAW);
            actions.add(Action.TRANSFUSE);
            return actions;
        }
    },
    HEALING() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    DOPING() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    ENERVATE() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    HEARTPLUNGE() {
 
        @Override
        Deque<Action> getActionsForState() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
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