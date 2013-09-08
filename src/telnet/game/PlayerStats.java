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
                    setHp(Integer.parseInt(val));
                    break;
                case "cp":
                    setCp(Integer.parseInt(val));
                    break;
                case "adrenaline":
                    setAdrenaline(Integer.parseInt(val));
                    break;
                case "endorphine":
                    setEndorphine(Integer.parseInt(val));
                    break;
                case "berserk":
                    setBerserk(Integer.parseInt(val));
                    break;
                case "none":
                    setNone(Integer.parseInt(val));
                    break;
                case "darts":
                    setDarts(Integer.parseInt(val));
                    break;
                case "blood":
                    setBlood(Integer.parseInt(val));
                    break;
                case "grafts":
                    setGrafts(Integer.parseInt(val));
                    break;
            }
        }
    }

    /**
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return the cp
     */
    public int getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * @return the adrenaline
     */
    public int getAdrenaline() {
        return adrenaline;
    }

    /**
     * @param adrenaline the adrenaline to set
     */
    public void setAdrenaline(int adrenaline) {
        this.adrenaline = adrenaline;
    }

    /**
     * @return the endorphine
     */
    public int getEndorphine() {
        return endorphine;
    }

    /**
     * @param endorphine the endorphine to set
     */
    public void setEndorphine(int endorphine) {
        this.endorphine = endorphine;
    }

    /**
     * @return the berserk
     */
    public int getBerserk() {
        return berserk;
    }

    /**
     * @param berserk the berserk to set
     */
    public void setBerserk(int berserk) {
        this.berserk = berserk;
    }

    /**
     * @return the none
     */
    public int getNone() {
        return none;
    }

    /**
     * @param none the none to set
     */
    public void setNone(int none) {
        this.none = none;
    }

    /**
     * @return the darts
     */
    public int getDarts() {
        return darts;
    }

    /**
     * @param darts the darts to set
     */
    public void setDarts(int darts) {
        this.darts = darts;
    }

    /**
     * @return the blood
     */
    public int getBlood() {
        return blood;
    }

    /**
     * @param blood the blood to set
     */
    public void setBlood(int blood) {
        this.blood = blood;
    }

    /**
     * @return the grafts
     */
    public int getGrafts() {
        return grafts;
    }

    /**
     * @param grafts the grafts to set
     */
    public void setGrafts(int grafts) {
        this.grafts = grafts;
    }

    public String toString() {
        return "\n\nhp\t" + hp + "\tcp\t" + cp + "\tadrenaline\t" + adrenaline
                + "\nendorphine\t" + endorphine + "\t\tberserk\t" + berserk
                + "\nenemy\t" + none;
    }
}
