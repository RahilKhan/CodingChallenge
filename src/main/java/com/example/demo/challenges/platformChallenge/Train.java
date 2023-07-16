package com.example.demo.challenges.platformChallenge;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.function.Predicate;

@Getter
@Setter
public class Train{ // implements Comparable {

    private LocalTime arrival;
    private LocalTime departure;

    public Train(LocalTime arrival, LocalTime departure) {
        this.arrival = arrival;
        this.departure = departure;
    }

    Predicate<Train> isTrainBefore = train -> (
            (train.arrival.isBefore(this.arrival) && train.arrival.isBefore(this.departure)) &&
                    train.departure.isBefore(this.arrival) && train.departure.isBefore(this.departure)
    );
    Predicate<Train> isTrainAfter = train -> (
            (train.arrival.isAfter(this.arrival) && train.arrival.isAfter(this.departure)) &&
                    train.departure.isAfter(this.arrival) && train.departure.isAfter(this.departure)
    );

/*
    @Override
    public int compareTo(Object obj) {
        Train train = (Train) obj;
        if ((train.arrival.isBefore(this.arrival) && train.arrival.isBefore(this.departure)) &&
                train.departure.isBefore(this.arrival) && train.departure.isBefore(this.departure)
        )
            return -1;
        if ((train.arrival.isAfter(this.arrival) && train.arrival.isAfter(this.departure)) &&
                train.departure.isAfter(this.arrival) && train.departure.isAfter(this.departure)
        )
            return -1;

        return 0;
    }
*/
}