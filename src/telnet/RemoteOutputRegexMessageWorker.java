package telnet;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteOutputRegexMessageWorker {

    private PlayerBean playerCharacter = new PlayerBean();
    private ConcurrentLinkedQueue<Command> commandsQueue;

    public RemoteOutputRegexMessageWorker() {
    }

    //died.
    public void parseWithRegex(String telnetText, ConcurrentLinkedQueue<Command> commandsQueue) {
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;

        if (telnetText.startsWith("Welcome ")) {
            playerCharacter.setLoggedIn(true);
        }

        if (telnetText.contains("died.") || telnetText.contains("Corpse of")) {
            playerCharacter.setCorpse(true);
        }

        if (telnetText.contains("You can only do this while fighting.")) {
            playerCharacter.setFighting(false);
        }
        if (telnetText.contains("You are fighting")) {
            playerCharacter.setFighting(true);
        }
        if (telnetText.contains("HP:")) {

            try {


                /*
                // ******CODE DOING OUTPUT HERE, JUST FOR TESTING
                Pattern p2 = Pattern.compile("(\\w+): (\\d+/{0,1}\\d*%{0,1})");
                Matcher m2 = p2.matcher(telnetText);
                String g1;
                String g2;
                while (m2.find()) {
                g1 = m2.group(1);
                g2 = m2.group(2);
                System.out.println(g1 + "\t" + g2);
                }
                //***********END CODE DOING OUTPUT HERE
                 */

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
                playerCharacter.setFighting(true);
            } catch (IllegalStateException e) {
            }
        }
    }
}
