package telnet;

public class Command {

    private String command = "help";

    private Command() {
    }

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
