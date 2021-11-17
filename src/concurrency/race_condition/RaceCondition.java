package concurrency.race_condition;

import concurrency.race_condition.list.ArrayListOperations;
import concurrency.race_condition.list.ListOperations;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
        int numOfThreads = 100;
        int numOfElementsPerThread = 100;

        var threadPool = Executors.newFixedThreadPool(numOfThreads);

        // Simulate Race Condition
        for(int i = 0; i < numOfThreads; i++) {
            threadPool.submit(() -> {
                for(int j = 0; j < numOfElementsPerThread; j++) {
                    list.addElementThreadUnsafe(j);
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(24L, TimeUnit.HOURS);


        System.out.println("====Thread Unsafe====");
        System.out.println("Expected Final size: "+(numOfThreads * numOfElementsPerThread));
        System.out.println("Final size: "+(list.getSize()));
        System.out.println("There can be mismatches in expected and actual final sizes");
        //list.printElements();

        // Solve it with Synchronized keyword
        list.clear();
        threadPool = Executors.newFixedThreadPool(numOfThreads);
        for(int i = 0; i < numOfThreads; i++) {
            threadPool.submit(() -> {
                for(int j = 0; j < numOfElementsPerThread; j++) {
                    list.addElementThreadSafe(j);
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(24L, TimeUnit.HOURS);

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