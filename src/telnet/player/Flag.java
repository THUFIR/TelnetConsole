package telnet.player;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
 
public enum Flag {
 
    BACKSTAB() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    CONFUSE() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    CORPSE() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    HEALING() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    DOPING() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    ENERVATE() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    HEARTPLUNGE() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    LOGGEDIN() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            return actions;
        }
    };

    abstract Queue<Action> apply();
}