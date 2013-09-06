package telnet;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteOutputRegexMessageWorker {

    private final static Logger LOG = Logger.getLogger(RemoteOutputRegexMessageWorker.class.getName());
    private PlayerState playerCharacter = PlayerState.INSTANCE;
    private ConcurrentLinkedQueue<Command> commandsQueue;

    public RemoteOutputRegexMessageWorker() {
    }

    //The refreshing effects of blood doping have worn off.
    //died.
    public void parseWithRegex(String telnetText, ConcurrentLinkedQueue<Command> commandsQueue) {
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;

        if (telnetText.contains("Taking over link-dead copy.") || telnetText.contains("You already have an active copy. Taking it over.")) {
            playerCharacter.setLoggedIn(true);
        }

        if (telnetText.contains("You feel crafty enough to try to confuse your enemy again.")) {
            playerCharacter.setConfuse(true);
        }
        if (telnetText.contains("Your body closes up some of your wounds")) {
            playerCharacter.setHealing(true);
        }

        if (telnetText.contains("The refreshing effects of blood doping have worn off.")) {
            playerCharacter.setDoping(true);
        }

        if (telnetText.contains("died.") || telnetText.contains("Corpse of")) {
            playerCharacter.setCorpse(true);
        }

        if (telnetText.contains("You can only do this while fighting.")) {
            playerCharacter.setConfuse(false);
        }
        if (telnetText.contains("You are fighting")) {
            playerCharacter.setConfuse(true);
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
                playerCharacter.setMonitor(stringEntries);
            } catch (IllegalStateException e) {
            }
        }
    }
}
