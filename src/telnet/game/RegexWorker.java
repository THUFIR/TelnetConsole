package telnet.game;

import telnet.game.PlayerCharacter;
import telnet.game.PlayerStats;
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
    private PlayerCharacter playerCharacter = PlayerCharacter.INSTANCE;

    public RegexWorker() {
    }

    public void parseWithRegex(String telnetText) {
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;

        if (telnetText.contains("Taking over link-dead copy.") || telnetText.contains("You already have an active copy. Taking it over.")) {
            playerCharacter.getFlags().setLoggedIn(true);
        }

        if (telnetText.contains("You feel crafty enough to try to confuse your enemy again.")) {
            playerCharacter.getFlags().setConfuse(true);
        }
        if (telnetText.contains("Your body closes up some of your wounds")) {
            playerCharacter.getFlags().setHealing(true);
        }

        if (telnetText.contains("The refreshing effects of blood doping have worn off.")) {
            playerCharacter.getFlags().setDoping(true);
        }

        if (telnetText.contains("died.") || telnetText.contains("Corpse of")) {
            playerCharacter.getFlags().setCorpse(true);
        }

        if (telnetText.contains("You can only do this while fighting.")) {
            playerCharacter.getFlags().setConfuse(false);
        }
        if (telnetText.contains("You are fighting")) {
            playerCharacter.getFlags().setConfuse(true);
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
                PlayerStats mon = new PlayerStats(stringEntries);
                playerCharacter.setStats(mon);
            } catch (IllegalStateException e) {
            }
        }
    }
}
