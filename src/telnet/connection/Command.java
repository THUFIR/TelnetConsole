package telnet.connection;

public class Command {

    private String command;

    public Command(String command) {
        this.command = command.toLowerCase();
    }

    public String getCommand() {
        return command;
    }

    private void setCommand(String command) {
        this.command = command;
    }
}
