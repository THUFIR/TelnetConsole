package telnet;

import java.util.LinkedList;
import java.util.Queue;

public class Fight {

    Stats s = Stats.INSTANCE;

    public Fight() {
    }

    public Queue<String> getCommands() {
        Queue<String> q = new LinkedList<>();
        if (s.getBackstab() == true) {
            q.add("backstab");
        }
        if (s.getHeartplunge() == true) {
            q.add("heartplunge");
        }
        if (s.getEnervate() == true) {
            q.add("enervate");
            q.add("dart");
        }
        if (s.getConfuse()) {
            q.add("confuse");
        }

        return q;
    }
}
