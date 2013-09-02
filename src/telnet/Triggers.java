package telnet;

import java.util.Observable;

class Triggers extends Observable {
    String parse(String s) {       
        String cmd = "";
        /*
        if (s.contains("Press Return to continue:")) {
            cmd = "\n";
        }
         * 
         */
        /*
        if (s.contains("U.S. forecasts and climate data")) {
            return "3";
        }
         * 
         */
        return cmd;
    }
}
