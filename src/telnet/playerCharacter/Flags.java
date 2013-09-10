package telnet.playerCharacter;

public enum Flags {

    BACKSTAB, CONFUSE, CORPSE, DOPING, ENERVATE, HEARTPLUNGE, HEALING, LOGGEDIN;
}

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