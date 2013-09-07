package telnet.game;

public class PlayerFlags {

    private boolean backstab = false;
    private boolean heartplunge = false;
    private boolean enervate = false;
    private boolean confuse = false;
    private boolean corpse = false;
    private boolean loggedIn = false;
    private boolean doping = false;
    private boolean healing = false;

    public PlayerFlags() {
    }

    public boolean isBackstab() {
        return backstab;
    }

    public void setBackstab(boolean backstab) {
        this.backstab = backstab;
    }

    public boolean isHeartplunge() {
        return heartplunge;
    }

    public void setHeartplunge(boolean heartplunge) {
        this.heartplunge = heartplunge;
    }

    public boolean isEnervate() {
        return enervate;
    }

    public void setEnervate(boolean enervate) {
        this.enervate = enervate;
    }

    public boolean isConfuse() {
        return confuse;
    }

    public void setConfuse(boolean confuse) {
        this.confuse = confuse;
    }

    public boolean isCorpse() {
        return corpse;
    }

    public void setCorpse(boolean corpse) {
        this.corpse = corpse;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isDoping() {
        return doping;
    }

    public void setDoping(boolean doping) {
        this.doping = doping;
    }

    
    public boolean isHealing() {
        return healing;
    }

    public void setHealing(boolean healing) {
        this.healing = healing;
    }
}
