package concurrency.race_condition;

import concurrency.race_condition.list.ArrayListOperations;
import concurrency.race_condition.list.ListOperations;

public class RaceCondition {
    public static void main(String[] args) {
        try {
            raceConditionArrayList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void raceConditionArrayList() throws InterruptedException {
        ListOperations list = new ArrayListOperations();
        int numOfThreads = 10;
        int numOfElementsPerThread = 100;

        // Simulate Race Condition
        for(int i = 0; i < numOfThreads; i++) {
            new Thread(() -> {
                for(int j = 0; j < numOfElementsPerThread; j++) {
                    list.addElementThreadUnsafe(j);
                }
            }).start();
        }

        System.out.println("====Thread Unsafe====");
        System.out.println("Expected Final size: "+(numOfThreads * numOfElementsPerThread));
        System.out.println("Final size: "+(list.getSize()));
        System.out.println("There can be mismatches in expected and actual final sizes");
        //list.printElements();

        // Solve it with Synchronized keyword
        list.clear();
        for(int i = 0; i < numOfThreads; i++) {
            Thread t = new Thread(() -> {
                for(int j = 0; j < numOfElementsPerThread; j++) {
                    list.addElementThreadSafe(j);
                }
            });
            t.join();
            t.start();
        }

        System.out.println("====Thread Safe====");
        System.out.println("Expected Final size: "+(numOfThreads * numOfElementsPerThread));
        System.out.println("Final size: "+(list.getSize()));
        System.out.println("Expected and actual final sizes will always be the same");
        //list.printElements();

        // Solve it with synchronized version of collection
    }

    public static void raceConditionLinkedList() {
        // Simulate Race Condition

        // Solve it with Synchronized keyword

        // Solve it with synchronized version of collection
    }

    public static void raceConditionHashMap() {
        // Simulate Race Condition

        // Solve it with Synchronized keyword

        // Solve it with synchronized version of collection
    }
}