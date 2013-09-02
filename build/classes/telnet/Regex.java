package telnet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regex {

    /*
    String input = "A: 123/456  BC: 78/90  NEw: 105 E: 0  BK: 10 None: 0%";
    Matcher matcher = REGEX_PATTERN.matcher(input);
    while (matcher.find()) {
    System.out.println("Name: " + matcher.group(1));
    System.out.println("Number: " + matcher.group(2));
    }
     * 
     * .compile("(\\w+): +(\\S+)");
     */
    String parse(String telnetText) {
        //System.out.println("starting parse...");
        String command = null;
        if (telnetText.contains("HP")) {
            System.out.println("trying to parse monitor");
            try {
                Pattern p = Pattern.compile("(\\w+): (\\S+)");
                Matcher m = p.matcher(telnetText);
                String[] s = new String[7];
                while (m.find()) {
                    System.out.println("Name: " + m.group(1));
                    System.out.println("Number: " + m.group(2));
                }
            } catch (IllegalStateException e) {
            }
        }
        return command;
    }
}
