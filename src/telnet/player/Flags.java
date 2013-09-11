package telnet.player;

public enum Flags {

    BACKSTAB, CONFUSE, CORPSE, DOPING, ENERVATE, HEARTPLUNGE, HEALING, LOGGEDIN;
}

/*
 * package bloch;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public enum Flag {

    FIGHT("+") {

        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.BACKSTAB);
            actions.add(Action.CONFUSE);
            return actions;
        }
    },
    HEAL("-") {

        @Override
        Queue<Action> apply() {
            Deque<Action> actions = new ArrayDeque<>();
            actions.add(Action.DRAW);
            actions.add(Action.PROCESS);
            return actions;
        }
    };
    private final String symbol;

    Flag(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract Queue<Action> apply();
}

 */






/*
 * 
 * 
 * 
 * 
 * 
 * package enums;

import java.util.EnumMap;
import java.util.Map;

public class Enums {

    //    private Map<PlayerCharacterFlags, Boolean> ef = new EnumMap(PlayerCharacterFlags.class);
    private static Map<Flags, Boolean> m = new EnumMap(Flags.class);

    public static void main(String[] args) {
        System.out.println("values are");
        for (Flags flag : Flags.values()) {
            System.out.println(flag);
        }
        System.out.println("fight enum..");
        Flags fight = Flags.FIGHT;
        System.out.println(fight);

            Flags f = Flags.FIGHT;
            boolean b = false;
            m.put(f, b);

            Flags fl = Flags.FLIGHT;
            boolean b2 = true;
            m.put(fl, b2);

        //for (Map.Entry<String, String> entry : map.entrySet())

        for (Map.Entry<Flags, Boolean> entry : m.entrySet()) {
            System.out.println(entry.getKey()+entry.getValue().toString());
        }
    }
}

 * 
 * 
 * 
 * 
 */