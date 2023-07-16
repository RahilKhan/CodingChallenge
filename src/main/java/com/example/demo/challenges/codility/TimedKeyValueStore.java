package com.example.demo.challenges.codility;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

interface TimeProvider {
    static long getTime(){
        return 10L;
    };
}
public class TimedKeyValueStore<K, V> {
    private final Map<K, TimedValue<V>> map;
    private final ReentrantLock lock;
    private final long capacity;
    private final long minTtl;

    public TimedKeyValueStore(long capacity, long minTtl) {
        if (capacity <= 0 || minTtl <= 0) {
            throw new IllegalArgumentException("capacity and minTtl must be positive");
        }
        this.map = new HashMap<>(Math.toIntExact(capacity));
        this.lock = new ReentrantLock();
        this.capacity = capacity;
        this.minTtl = minTtl;
    }

    public void put(K key, V value, long ttl) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key and value must not be null");
        }
        if (ttl <= 0) {
            throw new IllegalArgumentException("ttl must be positive");
        }
        long now = TimeProvider.getTime();
        long expireTime = now + ttl;
        this.lock.lock();
        try {
            if (this.map.containsKey(key)) {
                TimedValue<V> tv = this.map.get(key);
                if (tv.isExpired(now)) {
                    // expired entry can be replaced directly
                    this.map.put(key, new TimedValue<>(value, expireTime));
                    return;
                }
                // we only replace an existing entry if its expire time is earlier than the new one
                if (expireTime > tv.getExpireTime()) {
                    this.map.put(key, new TimedValue<>(value, expireTime));
                }
            } else {
                if (this.map.size() >= this.capacity) {
                    // remove the lowest ttl entry if there is no other entry with the same key already stored
                    K minKey = null;
                    long minExpireTime = Long.MAX_VALUE;
                    for (Map.Entry<K, TimedValue<V>> entry : this.map.entrySet()) {
                        if (entry.getValue().getExpireTime() < minExpireTime) {
                            minKey = entry.getKey();
                            minExpireTime = entry.getValue().getExpireTime();
                        }
                    }
                    if (minKey != null) {
                        this.map.remove(minKey);
                    }
                }
                // add the new entry
                if (expireTime >= now + this.minTtl) {
                    this.map.put(key, new TimedValue<>(value, expireTime));
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        long now = TimeProvider.getTime();
        this.lock.lock();
        try {
            TimedValue<V> tv = this.map.get(key);
            if (tv == null || tv.isExpired(now)) {
                return null;
            }
            return tv.getValue();
        } finally {
            this.lock.unlock();
        }
    }

    public int size() {
        long now = TimeProvider.getTime();
        this.lock.lock();
        try {
            int count = 0;
            for (TimedValue<V> tv : this.map.values()) {
                if (!tv.isExpired(now)) {
                    count++;
                }
            }
            return count;
        } finally {
            this.lock.unlock();
        }
    }

    private static class TimedValue<V> {
        private final V value;
        private final long expireTime;

        TimedValue(V value, long expireTime) {
            this.value = value;
            this.expireTime = expireTime;
        }

        V getValue() {
            return value;
        }

        long getExpireTime() {
            return expireTime;
        }

        boolean isExpired(long now) {
            return now >= expireTime;
        }
    }
}