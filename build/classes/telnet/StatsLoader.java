package telnet;

import java.util.List;
import java.util.Map.Entry;

class StatsLoader {

    Stats s = Stats.INSTANCE;

    public void setMonitor(List<Entry> stringEntries) {
        String key = null, val = null;
        for (Entry e : stringEntries) {
            key = e.getKey().toString();
            key = key.toLowerCase();
            val = e.getValue().toString();
            if (key.contains("hp")) {
                key = "hp";
            }
            if (key.contains("darts")) {
                key = "darts";
                s.war();
            }
            if (key.contains("blood")) {
                key = "blood";
            }
            switch (key) {
                case "hp":
                    s.setHP(val);
                case "cp":
                    s.setCP(val);
                case "adrenaline":
                    s.setAdrenaline(val);
                case "endorphine":
                    s.setEndorphine(val);
                case "berserk":
                    s.setBerserk(val);
                case "none":
                    s.setEnemy(val);
                case "darts":
                    s.setDarts(val);
                case "blood":
                    s.setBlood(val);
                case "grafts":
                    s.setGrafts(val);
            }
        }
    }
}