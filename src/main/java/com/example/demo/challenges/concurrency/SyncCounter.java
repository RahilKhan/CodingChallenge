package com.example.demo.challenges.concurrency;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SyncCounter {

    private int counter = 0;

    public synchronized void incCounter(){
        counter++;
    }

    public int getCounter(){
        return counter;
    }
}
