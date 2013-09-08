package telnet.game;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.logging.Logger;

public enum PlayerCharacter {

    INSTANCE;  //only one player can play the client
    private final static Logger log = Logger.getLogger(PlayerCharacter.class.getName());
    private PlayerStats stats = new PlayerStats();
    private PlayerFlags flags = new PlayerFlags();
    private RegexWorker regexWorker;// = new RegexWorker();
    private PlayerLogic playerLogic;// = new PlayerLogic();

    PlayerCharacter() {
        List<Entry> statsEntries = new ArrayList<>();
        Map.Entry<String, String> hp = new SimpleEntry<>("hp", "0");
        Map.Entry<String, String> cp = new SimpleEntry<>("hp", "0");
        statsEntries.add(hp);
        statsEntries.add(cp);
        stats = new PlayerStats(statsEntries);
        List<Entry> flagsEntries = new ArrayList<>();
        Map.Entry<String, Boolean> loggedIn = new SimpleEntry<>("loggedIn", true);
        Map.Entry<String, Boolean> confused = new SimpleEntry<>("confused", false);
        statsEntries.add(hp);
        statsEntries.add(hp);
        flags = new PlayerFlags(flagsEntries);
        regexWorker = new RegexWorker();
        playerLogic = new PlayerLogic();
    }

    public Queue<Command> processRemoteOutput(String remoteOutputMessage) {
        log.fine("trying to process..." + remoteOutputMessage);
        regexWorker.parseWithRegex(remoteOutputMessage);
        Queue<Command> commands = playerLogic.getCommands();
        for (Command c : commands) {
            log.fine(c.toString());
        }
        return commands;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public PlayerFlags getFlags() {
        return flags;
    }

    public void setFlags(PlayerFlags flags) {
        this.flags = flags;
    }
}