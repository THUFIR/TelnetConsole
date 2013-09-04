package telnet;

public enum CharacterState {

    INSTANCE;
    private int hp = 0;
    private int cp = 0;
    private int adrenaline = 0;
    private int endorphine = 0;
    private int berserk = 0;
    private int enemy = 0;
    private int darts = 0;
    private int blood = 0;
    private int grafts = 0;
    private boolean backstab = false;
    private boolean heartplunge = false;
    private boolean enervate = false;
    private boolean confuse = false;
    private boolean isFighting = false;

    public void setHP(String s) {
        hp = Integer.parseInt(s);
    }

    public int getHP() {
        return hp;
    }

    public void setCP(String s) {
        cp = Integer.parseInt(s);
    }

    public int getCP() {
        return cp;
    }

    public void setAdrenaline(String s) {
        adrenaline = Integer.parseInt(s);
    }

    public int getAdrenaline() {
        return adrenaline;
    }

    public void setEndorphine(String s) {
        endorphine = Integer.parseInt(s);
    }

    public int getEndorphine() {
        return endorphine;
    }

    public void setBerserk(String s) {
        berserk = Integer.parseInt(s);
    }

    public int getBerserk() {
        return berserk;
    }

    public void setEnemy(String s) {
        enemy = Integer.parseInt(s);
    }

    public int getEnemy() {
        return enemy;
    }

    public void setDarts(String s) {
        darts = Integer.parseInt(s);
    }

    public int getDarts() {
        return darts;
    }

    public void setBlood(String s) {
        blood = Integer.parseInt(s);
    }

    public int getBlood() {
        return blood;
    }

    public void setGrafts(String s) {
        grafts = Integer.parseInt(s);
    }

    public int getGrafts() {
        return grafts;
    }

    public void setBackstab(String s) {
        backstab = Boolean.parseBoolean(s);
    }

    public boolean getBackstab() {
        return backstab;
    }

    public void setHeartplunge(String s) {
        heartplunge = Boolean.parseBoolean(s);
    }

    public boolean getHeartplunge() {
        return heartplunge;
    }

    public void setEnervate(String s) {
        enervate = Boolean.parseBoolean(s);
    }

    public boolean getEnervate() {
        return enervate;
    }

    public void setConfuse(String s) {
        confuse = Boolean.parseBoolean(s);
    }

    public boolean getConfuse() {
        return confuse;
    }

    public boolean isFighting() {
        return isFighting;
    }

    public String toString() {
        return "\n\nhp\t" + hp + "\tcp\t" + cp + "\tadrenaline\t" + adrenaline
                + "\nendorphine\t" + endorphine + "\t\tberserk\t" + berserk
                + "\nenemy\t" + enemy + "\t\tdarts\t" + darts + "\tblood\t" + blood
                + "\ngrafts\t" + grafts + "\t\tbackstab\t" + backstab
                + "\nheartplunge\t" + heartplunge + "\tenervate\t" + enervate
                + "\nconfuse\t" + confuse + "\n\n";
    }

    public void setIsFighting(boolean isFighting) {
        this.isFighting = isFighting;
        if (isFighting) {
            backstab = true;
            heartplunge = true;
            enervate = true;
            confuse = true;
        } else {
            backstab = false;
            heartplunge = false;
            enervate = false;
            confuse = false;
        }
    }
}
