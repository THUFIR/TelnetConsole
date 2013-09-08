package telnet.game;

import java.util.List;
import java.util.Map.Entry;

public class PlayerStats {

    private int hp = 0;
    private int cp = 0;
    private int adrenaline = 0;
    private int endorphine = 0;
    private int berserk = 0;
    private int none = 0;
    private int darts = 0;
    private int blood = 0;
    private int grafts = 0;

    public PlayerStats() {
    }

    public PlayerStats(List<Entry> stringEntries) {

        String key = null, val = null;
        for (Entry e : stringEntries) {
            key = e.getKey().toString();
            key = key.toLowerCase();
            val = e.getValue().toString();
            if (key.contains("hp")) {
                key = "hp";
            }
            if (key.contains("cp")) {
                key = "cp";
            }
            if (key.contains("adrenaline")) {
                key = "adrenaline";
            }
            if (key.contains("endorphine")) {
                key = "darts";
            }
            if (key.contains("berserk")) {
                key = "darts";
            }
            if (key.contains("none")) {
                key = "none";
            }
            if (key.contains("blood")) {
                key = "blood";
            }
            if (key.contains("grafts")) {
                key = "grafts";
            }
            switch (key) {
                case "hp":
                    hp = Integer.parseInt(val);
                    break;
                case "cp":
                    cp = Integer.parseInt(val);
                    break;
                case "adrenaline":
                    adrenaline = Integer.parseInt(val);
                    break;
                case "endorphine":
                    endorphine = Integer.parseInt(val);
                    break;
                case "berserk":
                    berserk = Integer.parseInt(val);
                    break;
                case "none":
                    none = Integer.parseInt(val);
                    break;
                case "darts":
                    darts = Integer.parseInt(val);
                    break;
                case "blood":
                    blood = Integer.parseInt(val);
                    break;
                case "grafts":
                  grafts=Integer.parseInt(val);
                    break;
            }
        }
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getAdrenaline() {
        return adrenaline;
    }

    public void setAdrenaline(int adrenaline) {
        this.adrenaline = adrenaline;
    }

    public int getEndorphine() {
        return endorphine;
    }

    public void setEndorphine(int endorphine) {
        this.endorphine = endorphine;
    }

    public int getBerserk() {
        return berserk;
    }

    public void setBerserk(int berserk) {
        this.berserk = berserk;
    }

    public int getNone() {
        return none;
    }
    
    public void setNone(int none) {
        this.none = none;
    }

    public int getDarts() {
        return darts;
    }

    public void setDarts(int darts) {
        this.darts = darts;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getGrafts() {
        return grafts;
    }

    public void setGrafts(int grafts) {
        this.grafts = grafts;
    }

    public String toString() {
        return "\n\nhp\t" + hp + "\tcp\t" + cp + "\tadrenaline\t" + adrenaline
                + "\nendorphine\t" + endorphine + "\t\tberserk\t" + berserk
                + "\nenemy\t" + none;
    }
}
