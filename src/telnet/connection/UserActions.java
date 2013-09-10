package telnet.connection;

public class UserActions {

    private String command;

    public UserActions(String command) {
        this.command = command.toLowerCase();
    }

    public String getCommand() {
        return command;
    }

    private void setCommand(String command) {
        this.command = command;
    }
}
