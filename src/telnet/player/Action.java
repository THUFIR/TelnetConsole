package telnet.player;

public class Action {

    private String command;

    public Action(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String toString() {
        return command;
    }
}
