package telnet;

import java.util.Observable;

class Triggers extends Observable {

    String parse(String telnetText) {
        String command = "";
        if (telnetText.contains("Press Return to continue:")) {
            command = "\n";
        }
        if (telnetText.contains("U.S. forecasts and climate data")) {
            return "3";
        }
        return command;
    }
}

