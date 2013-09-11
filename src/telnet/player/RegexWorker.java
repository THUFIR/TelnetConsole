package telnet.player;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWorker {

    private final static Logger log = Logger.getLogger(RegexWorker.class.getName());
    private Stats stats = new Stats();// = PlayerCharacter.INSTANCE.getStats();
//    private Map<Flag, Boolean> flags = new EnumMap(Flag.class);

    public RegexWorker() {
    }

    public void parseAndUpdatePlayerCharacter(String telnetText) {
        log.fine(telnetText);
        //   flags = Player.INSTANCE.getFlags();
        stats = Player.INSTANCE.getStats();
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;

        if (telnetText.contains("Taking over link-dead copy.") || telnetText.contains("You already have an active copy. Taking it over.")) {
            Player.INSTANCE.setFlag(Flag.LOGGEDIN, true);
            //          flags.put(Flag.LOGGEDIN, true);
        }

        if (telnetText.contains("You feel crafty enough to try to confuse your enemy again.")) {
            //        flags.put(Flag.CONFUSE, true);
        }
        if (telnetText.contains("Your body closes up some of your wounds")) {
            //      flags.put(Flag.HEALING, true);
        }

        if (telnetText.contains("The refreshing effects of blood doping have worn off.")) {
            //           flags.put(Flag.DOPING, true);
        }

        if (telnetText.contains("died.") || telnetText.contains("Corpse of")) {
            log.info("saw corpse!!!");
            Player.INSTANCE.setFlag(Flag.CORPSE, true);
            //         flags.put(Flag.CORPSE, true);
        }

        if (telnetText.contains("You can only do this while fighting.")) {
            //       flags.put(Flag.CONFUSE, false);
        }
        if (telnetText.contains("You are fighting")) {
//            flags.put(Flag.CONFUSE, true);
        }
        if (telnetText.contains("HP:")) {

            try {
                Pattern pattern = Pattern.compile("(\\w+): (\\d+)");
                Matcher matcher = pattern.matcher(telnetText);
                List<Entry> stringEntries = new ArrayList<>();
                while (matcher.find()) {
                    keyName = matcher.group(1);
                    keyVal = matcher.group(2);
                    Map.Entry<String, String> entr = new AbstractMap.SimpleEntry<>(keyName, keyVal);
                    stringEntries.add(entr);
                }
                Pattern p = Pattern.compile("(\\d+)");
                Matcher m;
                for (Entry<String, String> e : stringEntries) {
                    m = p.matcher(e.getValue());
                    while (m.find()) {
                        digitsOnly = m.group(1);
                    }
                }
                stats = new Stats(stringEntries);
            } catch (IllegalStateException e) {
            }
        }
    }
}
