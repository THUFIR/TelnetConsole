package telnet.playerCharacter;

public class Command {

    private String command = "help";

    private Command() {
    }

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    
    public String toString () {
        return command;
    }
}
