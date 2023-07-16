package com.example.demo.challenges;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem Statement
 * You should choose P = 2, so that exactly 4 animals with energy levels (2, 3, 4 and 5) can be saved as these have energies greater than or equal to P.
 * Input Format
 * The first line of input consists of two space-separated integers, N (number of animals) and X (available capacity for animals that can be transported).
 * The second line of input consists of N space-separated integers, representing the energy of all the animals.
 * Requirement:
 * Select exactly 'X' animals with energy 'X' from list 'E'
 * where X <= N (Total no. of animals)
 * <p>
 * Constraints
 * 1 <= N <= 10^5
 * 1 <= X <= N
 * 1 <= arr[i] <= 10^12
 * <p>
 * Arr[i] represents the energy level of the ith animal.
 * <p>
 * Output Format
 * Print the minimum energy level P such that exactly X animals can be saved or transported. If it is not possible to save exactly X animals, then print -1.
 * <p>
 * Sample TestCase 1
 * Input
 * 5 4
 * 1 3 2 4 5
 * Output
 * 2
 */

@Slf4j
public class MaxAnimalByEnergyLevels {

    public static void main(String...args) {

        /*
        Scanner scanner = new Scanner(System.in);

        String inputStrArr = scanner.nextLine();
        log.info(" inputStrArr :" + inputStrArr);
        String[] arrOfStr = inputStrArr.split(" ");
        int numOfAnimals = Integer.parseInt(arrOfStr[0]);
        int capacity = Integer.parseInt(arrOfStr[1]);
        log.info(" numOfAnimals : " + numOfAnimals);
        log.info(" capacity : " + capacity);

        String energyStr = scanner.nextLine();
        log.info(" energyStr : " + energyStr);
        String[] arrOfEnergyStr = energyStr.split(" ");
        */

        int numOfAnimals = 8;
        int capacity = 5;

        String[] arrOfEnergyStr = new String[]{"2", "3", "33", "7", "4", "5", "9", "6"};
        findMinEnergyToSaveAnimal(numOfAnimals, capacity, arrOfEnergyStr);

        arrOfEnergyStr = new String[]{"2", "33", "33", "7", "7", "7", "4", "4"};
        findMinEnergyToSaveAnimal(numOfAnimals, capacity, arrOfEnergyStr);


        arrOfEnergyStr = new String[]{"2", "33", "33", "7", "7", "4", "4", "4"};
        findMinEnergyToSaveAnimal(numOfAnimals, capacity, arrOfEnergyStr);

        arrOfEnergyStr = new String[]{"7", "7", "7", "7", "7", "4", "4", "4"};
        findMinEnergyToSaveAnimal(numOfAnimals, capacity, arrOfEnergyStr);

        arrOfEnergyStr = new String[]{"7", "7", "7", "7", "7", "7", "7", "7"};
        findMinEnergyToSaveAnimal(numOfAnimals, capacity, arrOfEnergyStr);


    }

    private static void findMinEnergyToSaveAnimal(int numOfAnimals, int capacity, String[] arrOfEnergyStr) {
        log.info("------------------------------------");
        log.info("numOfAnimals : " + numOfAnimals);
        log.info("capacity : " + capacity);

        Set<String> energySet = new HashSet<>(List.of(arrOfEnergyStr));

        List<Integer> energyList = new ArrayList<>();
        for (String str : arrOfEnergyStr) {
            energyList.add(Integer.parseInt(str));
        }
        energyList = energyList.stream().sorted().toList();
        log.info("energyList : " + energyList);

        if (numOfAnimals < capacity) {
            log.info("-1");
            return;
        } else if (numOfAnimals < arrOfEnergyStr.length) {
            log.info("-1");
            return;
        }

        Integer energyIndex = energyList.size() - capacity;

        if (energySet.size() != energyList.size()) {
            log.info("Animals with duplicate energy");
            if (energyList.get(energyIndex - 1).equals(energyList.get(energyIndex))) {
                log.info("-1");
                return;
            }
        }
        log.info("energyIndex -> {} : energy -> {}", energyIndex, energyList.get(energyIndex));


    }


}
