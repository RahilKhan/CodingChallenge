package com.example.demo.challenges.platformChallenge;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Find the minimum number of platform required to accommodate the given arrival and departure timings in a station.
 * A = { 20:00, 12:00, 15:15, 18:30, 13:45 }
 * D = { 23:55, 18:45, 17:55, 19:45, 17:10 }
 * Index 0 of A -> Arrival time and D -> departure time and so on.
 */
@Slf4j
public class Platform {

    public static void main(String... args) {
        ArrayList<LocalTime> arrivalList = new ArrayList();
        arrivalList.add(LocalTime.parse("20:00"));
        arrivalList.add(LocalTime.parse("15:15"));
        arrivalList.add(LocalTime.parse("12:00"));
        arrivalList.add(LocalTime.parse("18:30"));
        arrivalList.add(LocalTime.parse("13:45"));

        ArrayList<LocalTime> departurelList = new ArrayList();
        departurelList.add(LocalTime.parse("23:55"));
        departurelList.add(LocalTime.parse("17:55"));
        departurelList.add(LocalTime.parse("18:45"));
        departurelList.add(LocalTime.parse("19:45"));
        departurelList.add(LocalTime.parse("17:10"));

        List<Train> trainList = new ArrayList<>();
        trainList.add(new Train(LocalTime.parse("20:00"), LocalTime.parse("23:55")));
        trainList.add(new Train(LocalTime.parse("12:00"), LocalTime.parse("18:45")));
        trainList.add(new Train(LocalTime.parse("15:15"), LocalTime.parse("17:55")));
        trainList.add(new Train(LocalTime.parse("18:30"), LocalTime.parse("19:45")));
        trainList.add(new Train(LocalTime.parse("13:45"), LocalTime.parse("17:10")));
        log.info("trainList : {} ", trainList);

        findMinPlatforms(arrivalList, departurelList);
    }

    public static void findMinPlatforms(ArrayList<LocalTime> arrivalList, ArrayList<LocalTime> departurelList) {

        int platformRequired = 1, result = 1;
        int loopIndex = arrivalList.size();

        for (int i = 0; i < loopIndex; i++) {
            platformRequired = 1;
            for (int j = 0; j < loopIndex; j++) {
                if (i != j) {
                    if ((arrivalList.get(i).isAfter(arrivalList.get(j)) && arrivalList.get(i).isBefore(arrivalList.get(j))) ||
                            (departurelList.get(i).isAfter(arrivalList.get(j)) && departurelList.get(i).isBefore(departurelList.get(j)))) {
                        platformRequired++;
                    }
                }
            }
        }
        result = Math.max(result, platformRequired);
        log.info("Platform required : {}", result);
    }

}
