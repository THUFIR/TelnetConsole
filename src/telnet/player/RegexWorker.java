package telnet.player;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexWorker {

    private final static Logger log = Logger.getLogger(RegexWorker.class.getName());
    private StatsBean stats = new StatsBean();// = PlayerCharacter.INSTANCE.getStats();
    private Player player = Player.INSTANCE;

    public RegexWorker() {
    }

    public Deque<Action> parseAndUpdatePlayerCharacter(String telnetText) {
        log.fine(telnetText);
        Deque<Action> actions = new ArrayDeque<>();

        stats = Player.INSTANCE.getStats();
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;

        if (telnetText.contains("shoulder nerve firmly with one hand")) {
            Pattern pattern = Pattern.compile("(//[^ ]*$)");
            Matcher matcher = pattern.matcher(telnetText);
            while (matcher.find()) {
                String foo = matcher.group(1);
                log.info(foo);
            }
        }

        if (telnetText.contains("confusing the hell out of")) {
            log.info("trying to confuse");
            Pattern pattern = Pattern.compile("(\\w+)");  //(\w+)\.
            Matcher matcher = pattern.matcher(telnetText);
            String enemy = null;
            while (matcher.find()) {
                enemy = matcher.group();
                log.info(enemy);
            }
            stats = player.getStats();
            stats.setEnemy(enemy);
            player.setStats(stats);
            Action b = new Action("backstab " + enemy);
            actions.add(b);
        }
        if (telnetText.contains("Taking over link-dead copy.") || telnetText.contains("You already have an active copy. Taking it over.")) {
            player.setFlag(Flag.LOGGEDIN, true);
            //          flags.put(Flag.LOGGEDIN, true);
        }

        if (telnetText.contains("You feel crafty enough to try to confuse your enemy again.")) {
            player.setFlag(Flag.CONFUSE, true);
        }
        if (telnetText.contains("Your body closes up some of your wounds")) {
            player.setFlag(Flag.HEALING, true);

        }

        if (telnetText.contains("The refreshing effects of blood doping have worn off.")) {
            player.setFlag(Flag.DOPING, true);

        }

        if (telnetText.contains("died.") || telnetText.contains("Corpse of")) {
            log.fine("saw corpse!!!");
            Player.INSTANCE.setFlag(Flag.CORPSE, true);
        }

        if (telnetText.contains("You can only do this while fighting.")) {
            player.setFlag(Flag.FIGHTING, false);
        }
        if (telnetText.contains("You are fighting")) {
            player.setFlag(Flag.FIGHTING, true);
        }
        if (telnetText.contains("HP:")) {

            try {
                Pattern pattern = Pattern.compile("(\\w+): (\\d+)");
                Matcher matcher = pattern.matcher(telnetText);
                List<Entry> stringEntries = new ArrayList<>();
                while (matcher.find()) {
                    keyName = matcher.group(1);
                    keyVal = matcher.group(2);
                    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(keyName, keyVal);
                    stringEntries.add(entry);
                }
                Pattern p = Pattern.compile("(\\d+)");
                Matcher m;
                for (Entry<String, String> e : stringEntries) {
                    m = p.matcher(e.getValue());
                    while (m.find()) {
                        digitsOnly = m.group(1);
                    }
                }
                stats = new StatsBean(stringEntries);
                player.setStats(stats);
            } catch (IllegalStateException e) {
            }
        }
        return actions;
    }
}
