package telnet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

class CharacterState {

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
                s.setIsFighting(true);
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

    public void setFighting(boolean isFighting) {
        s.setIsFighting(isFighting);
    }

    boolean isFighting() {
        return s.isFighting();
    }

    Queue<Command> getFightCommands() {
        Queue<Command> fightCommands = new LinkedList<>();
        Command b = new Command("backstab");
        Command h = new Command("heartplunge");
        Command e = new Command("enervate");
        Command d = new Command("dart");
        Command c = new Command("confuse");
        fightCommands.add(b);
        fightCommands.add(h);
        fightCommands.add(e);
        fightCommands.add(d);
        fightCommands.add(c);
        return fightCommands;
    }
}