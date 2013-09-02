package telnet;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

class Stats {

    List<Entry> monitor;

    void setMonitor(List<Entry> stringEntries) {
        monitor = new ArrayList<>();
        String key = null, stringVal = null;
        int intVal = 0;
        for (Entry e : stringEntries) {
            key = (String) e.getKey();
            if (key.contains("HP")) {
                System.out.println("set key hp:\t" + key);
            }
            if (key.contains("Darts")) {
                key = "Darts";
            }
            if (key.contains("Blood")) {
                key = "Blood";
            }
            stringVal = (String) e.getValue();
            intVal = Integer.parseInt(stringVal);
            Entry<String, Integer> entry = new SimpleEntry<>(key, intVal);
            monitor.add(e);
        }
        System.out.println("monitor entries to follow");
        for (Entry e : monitor) {
            System.out.println("entry\t"+e.getKey() + e.getValue().toString());
        }
    }
}
