package basic.knowledge.henry.ThreadRelevant._26threadSafe.treadSafeandHIghPerformance;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MonitorVehicleTracker {
    private final ConcurrentHashMap<String, ImmutablePoint> locations;
    private final Map<String, ImmutablePoint> unmodifiedLocations;

    public MonitorVehicleTracker(Map<String, ImmutablePoint> pointMap) {
        locations = new ConcurrentHashMap<>(pointMap);
        unmodifiedLocations = Collections.unmodifiableMap(locations);
    }

    public Map<String, ImmutablePoint> getLocations() {
        return unmodifiedLocations;
    }

    public void setLocation(String id, int x, int y) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        if (locations.replace(id, new ImmutablePoint(x, y)) == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
    }

    public ImmutablePoint getLocation(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("param is null");
        }
        return locations.get(id);
    }

}
