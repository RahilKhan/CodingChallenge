package com.example.demo.challenges.codility;
//package com.codility.tasks.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
// import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * Task description
 * Implement a data structure which allows key–value elements to be added and retrieved.
 * The data structure should fulfill the following requirements:
 * <p>
 * It is possible to add and retrieve an element;
 * Adding an element replaces a value whose key already exists in the structure;
 * Elements are added with a Time-To-Leave expressed in milliseconds;
 * Once the Time-To-Leave of an element is reached, it is considered to be non-existent;
 * The structure has a limited capacity defined in the constructor call;
 * An element whose Time-To-Leave is lower than the lowest Time-To-Leave in the structure should not be added;
 * When adding an element to a full structure, the pair with the lowest Time-To-Leave shall be replaced
 * if there's no other entry with the same key already stored;
 * The data structure provides information about its current size.
 *
 * <p>
 * Implementation hints:
 * Use the TimeProvider class in order to get the current time in milliseconds.
 * Please do not use System.currentTimeMillis().
 * After getting milliseconds from TimeProvider add offset milliseconds that will be actual Time-To-Leave value.
 * If the input is invalid (e.g. null parameters), throw an IllegalArgumentException.
 * Using threads may not lead to a correct solution; consider alternative approaches.
 *
 * <p>
 * Time-To-Leave explained:
 * Time-To-Leave is a time after which an added element is considered to be non-existent (it leaves the data structure);
 * Once Time-To-Leave is reached:
 * The element expires and, it's no longer possible to retrieve it from the structure;
 * The element is not counted in the size of the structure.
 */

interface TimeProviderInf {
    long getMillis();
}

public class CachingDataStructure {

    private int maxSize;
    private TimeProviderInf timeProviderInf;

    private TreeMap<Long, String> timeMap;
    private Map<String, String> dataMap;
    private long ttl = 0;
    private static final long OFFSET = 0;

    CachingDataStructure(TimeProviderInf timeProviderInf, int maxSize) {
        this.timeProviderInf = timeProviderInf;
        this.maxSize = maxSize;
        timeMap = new TreeMap<>();
        dataMap = new HashMap<>(maxSize);
        this.ttl = timeProviderInf.getMillis() + OFFSET;
    }

    public void put(String key, String value, long timeToLeaveInMilliseconds) {
        System.out.println(" key : " + key
                + "\n value : " + value
                + "\n timeToLeaveInMilliseconds : " + timeToLeaveInMilliseconds);

        /** timeMap & dataMap should have equal size */
        if (!(timeMap.size() == dataMap.size())) {
            throw new IllegalArgumentException();
        }

        if (timeToLeaveInMilliseconds <= 0 || key == null) {
            throw new IllegalArgumentException();
        }

        /** Removing data with expired timeToLeaveInMilliseconds from timeMap & dataMap */
        if (timeToLeaveInMilliseconds > this.ttl && timeMap.containsKey(timeToLeaveInMilliseconds)) {
            timeMap.remove(timeMap.firstKey());
            dataMap.remove(timeMap.get(timeMap.firstKey()));
        }

        /**
         * Check if timeToLeaveInMilliseconds is not lower than the lowest ttl in timeMap.
         * true -> add/update timeMap and dataMap
         */
        if (timeMap.size() == 0 || timeToLeaveInMilliseconds > timeMap.firstKey()) {
            if (!timeMap.containsKey(timeToLeaveInMilliseconds) && size() == maxSize) {
                timeMap.remove(timeMap.firstKey());
                dataMap.remove(timeMap.get(timeMap.firstKey()));
            }
            timeMap.put(timeToLeaveInMilliseconds, key);
            dataMap.put(key, value);
        } else {
            AtomicLong ttlKeyToRemove = new AtomicLong();
            if (!timeMap.isEmpty()) {
                timeMap.forEach((ttl, ttlKey) -> {
                    if (ttlKey == key) {
                        ttlKeyToRemove.set(ttl);
                    }
                });
                timeMap.remove(ttlKeyToRemove.get());
                timeMap.put(timeToLeaveInMilliseconds, key);
            }
        }
    }

    public Optional<String> get(String key) {
        if (dataMap == null) {
            return Optional.empty();
        }

        AtomicBoolean rmv = new AtomicBoolean(false);
        AtomicLong ttlKy = new AtomicLong();
        timeMap.forEach((ttl, ttlKey) -> {
            if (ttlKey.equals(key) && ttl < this.ttl) {
                ttlKy.set(ttl);
                rmv.set(true);
            }
        });
        if (rmv.get()) {
            timeMap.remove(ttlKy.get());
            dataMap.remove(key);
            return Optional.empty();
        }

        if (dataMap.containsKey(key))
            return Optional.of(dataMap.get(key));

        return Optional.empty();
        // throw new NotImplementedException();
    }

    public int size() {
        if (timeMap.size() == 0) {
            return 0;
        }
        AtomicInteger mapSize = new AtomicInteger();
        timeMap.forEach((key, value) -> {
            if (key != this.ttl)
                mapSize.getAndIncrement();
        });
        return mapSize.get();
        // throw new NotImplementedException();
    }

}