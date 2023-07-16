package com.example.demo.challenges.codility;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TimeToLeaveCache<K, V> {

    private final Map<K, CacheEntry<V>> cache;
    private final TimeProvider timeProvider;
    private final long capacity;

    interface TimeProvider{
        long currentTimeInMillis();
    }

    public TimeToLeaveCache(long capacity, TimeProvider timeProvider) {
        this.cache = new HashMap<>((int) capacity);
        this.timeProvider = timeProvider;
        this.capacity = capacity;
    }

    public void add(K key, V value, long timeToLeave) {
        if (key == null || value == null || timeToLeave < 0) {
            throw new IllegalArgumentException("Invalid input parameters!");
        }

        long currentTime = timeProvider.currentTimeInMillis();
        long timeToLeaveInMillis = currentTime + timeToLeave;

        if (cache.size() >= capacity) {
            CacheEntry<V> leastRecentEntry = getLeastRecentEntry();
            if (leastRecentEntry == null) {
                return;
            }

            if (leastRecentEntry.getExpiryTime() < timeToLeaveInMillis) {
                cache.remove(leastRecentEntry.getKey());
                add(key, value, timeToLeaveInMillis - currentTime);
            }
        } else {
            cache.put(key, new CacheEntry<>(value, timeToLeaveInMillis));
        }
    }

    public V get(K key) {
        CacheEntry<V> entry = cache.get(key);
        if (entry == null || isExpired(entry)) {
            return null;
        }

        entry.updateLastAccessTime();
        return entry.getValue();
    }

    public int size() {
        return (int) cache.entrySet().stream().filter(entry -> !isExpired(entry.getValue())).count();
    }

    private boolean isExpired(CacheEntry<V> entry) {
        return entry.getExpiryTime() < timeProvider.currentTimeInMillis();
    }

    private CacheEntry<V> getLeastRecentEntry() {
        return cache.values()
                .stream()
                .min(Comparator.comparingLong(CacheEntry::getLastAccessTime))
                .orElse(null);
    }

    private class CacheEntry<V> {
        private final V value;
        private final long expiryTime;
        private long lastAccessTime;

        public CacheEntry(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
            this.lastAccessTime = System.currentTimeMillis();
        }

        public V getValue() {
            return value;
        }

        public long getExpiryTime() {
            return expiryTime;
        }

        public long getLastAccessTime() {
            return lastAccessTime;
        }

        public void updateLastAccessTime() {
            this.lastAccessTime = System.currentTimeMillis();
        }

        public K getKey() {
            // note that getKey method wasn't declared
            // since it wasn't part of the problem description
            throw new UnsupportedOperationException();
        }
    }
}
