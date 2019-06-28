import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsCollector {
    private HashMap<String, Integer> eventTypeCounter = new HashMap<String, Integer>();
    private HashMap<String, Integer> dataCounter = new HashMap<String, Integer>();

    public HashMap<String, Integer> getEventTypeCounter() {
        return eventTypeCounter;
    }

    public void setEventTypeCounter(HashMap<String, Integer> eventTypeCounter) {
        this.eventTypeCounter = eventTypeCounter;
    }

    public HashMap<String, Integer> getDataCounter() {
        return dataCounter;
    }

    public void setDataCounter(HashMap<String, Integer> dataCounter) {
        this.dataCounter = dataCounter;
    }

    public void updateStatistics(String eventType, String data) {
        // Event Type
        Integer val = eventTypeCounter.get(eventType);
        if (val == null) val = 0;
        val++;
        eventTypeCounter.put(eventType, val);

        // Data
        val = dataCounter.get(data);
        if (val == null) val = 0;
        val++;
        dataCounter.put(data, val);
    }

    public List<String> getEventTypesCounters() {
        List<String> etCounter = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : eventTypeCounter.entrySet()) {
            etCounter.add(entry.getKey() + ": " + entry.getValue());
        }
        return etCounter;
    }

    public List<String> getDataCounters() {
        List<String> dataCounterList = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : dataCounter.entrySet()) {
            dataCounterList.add(entry.getKey() + ": " + entry.getValue());
        }
        return dataCounterList;
    }
}
