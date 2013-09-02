package telnet;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regex {

    /*
HP: 648/648  CP: 657/657  ADRENALINE: 105 ENDORPHINE: 0  BERSERK: 10 None: 0%
[Darts: 0][Blood: 69068][Grafts: 0/0][Co][En]
     */
    String parse(String telnetText) {
        //System.out.println("starting parse...");
        telnetText ="HP: 648/648  CP: 657/657  ADRENALINE: 105 ENDORPHINE: 0  BERSERK: 10 None: 0% [Darts: 0][Blood: 69068][Grafts: 0/0][Co][En]";
        String command = null;
        String name = null;
        String val = null;
        if (telnetText.contains("HP")) {
            System.out.println("trying to parse monitor");
            try {
                Pattern p = Pattern.compile("(\\w+): (\\S+)");
                Matcher m = p.matcher(telnetText);
                List<Map.Entry> l = new ArrayList<>();
                while (m.find()) {
                    name = m.group(1);
                    val = m.group(2);
                    Map.Entry<String, String> entr = new AbstractMap.SimpleEntry<>(name,val);
                    l.add(entr);
                }
            } catch (IllegalStateException e) {
            }
        }
        return command;
    }
}
