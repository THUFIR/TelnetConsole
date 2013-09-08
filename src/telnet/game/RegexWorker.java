package telnet.game;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWorker {

    private final static Logger LOG = Logger.getLogger(RegexWorker.class.getName());
    private PlayerFlags flags = new PlayerFlags();// = PlayerCharacter.INSTANCE.getFlags();
    private PlayerStats stats = new PlayerStats();// = PlayerCharacter.INSTANCE.getStats();

    public RegexWorker() {
    }

    public void parseWithRegex(String telnetText) {
        LOG.fine(telnetText);
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;

        if (telnetText.contains("Taking over link-dead copy.") || telnetText.contains("You already have an active copy. Taking it over.")) {
            flags.setLoggedIn(true);
        }

        if (telnetText.contains("You feel crafty enough to try to confuse your enemy again.")) {
            flags.setConfuse(true);
        }
        if (telnetText.contains("Your body closes up some of your wounds")) {
            flags.setHealing(true);
        }

        if (telnetText.contains("The refreshing effects of blood doping have worn off.")) {
            flags.setDoping(true);
        }

        if (telnetText.contains("died.") || telnetText.contains("Corpse of")) {
            flags.setCorpse(true);
        }

        if (telnetText.contains("You can only do this while fighting.")) {
            flags.setConfuse(false);
        }
        if (telnetText.contains("You are fighting")) {
            flags.setConfuse(true);
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
                stats = new PlayerStats(stringEntries);
            } catch (IllegalStateException e) {
            }
        }
        PlayerCharacter.INSTANCE.setFlags(flags);  //do both
        PlayerCharacter.INSTANCE.setStats(stats);
    }
}
