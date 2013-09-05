package telnet;

import java.util.LinkedList;
import java.util.Queue;

public class Fight {

    CharacterState s = CharacterState.INSTANCE;

    public Fight() {
    }

    public Queue<String> getCommands() {
        Queue<String> q = new LinkedList<>();
        if (s.isBackstab() == true) {
            q.add("backstab");
        }
        if (s.isHeartplung() == true) {
            q.add("heartplunge");
        }
        if (s.isEnervate() == true) {
            q.add("enervate");
            q.add("dart");
        }
        if (s.isConfuse()) {
            q.add("confuse");
        }

        return q;
    }
}
