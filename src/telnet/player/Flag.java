package telnet.player;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
 
public enum Flag {
 
    FIGHT() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    HEAL() {
 
        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.DRAW);
            actions.add(Action.PROCESS);
 
            return actions;
        }
    };

    abstract Queue<Action> apply();
}