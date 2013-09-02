package telnet;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regex {

    private Stats s = new Stats();

    String parse(String telnetText) {
        String command = null;
        String keyName = null;
        String keyVal = null;
        String digitsOnly = null;
        if (telnetText.contains("HP:")) {
            //System.out.println("monitor trigger..");
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
                s.setMonitor(stringEntries);
            } catch (IllegalStateException e) {
            }
        }
        return command;
    }
}
