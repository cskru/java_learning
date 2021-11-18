package concurrency.race_condition;

import concurrency.race_condition.list.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceCondition {
    public static void main(String[] args) {
        try {
            // Testing Array List for race conditions and solving them in a variety of ways
            raceConditionArrayList();

            // Testing Linked List for race conditions and solving them in a variety of ways
            raceConditionLinkedList();

            // Testing HashMap for race conditions and solving them in a variety of ways
            raceConditionHashMap();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void raceConditionArrayList() throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int numOfThreads = 100;
        int numOfElementsPerThread = 100;

        // Simulate Race Condition
        System.out.println("\n====Thread Unsafe====");
        concurrentAddToList(UnsafeList.class, numOfThreads, numOfElementsPerThread, false);
        System.out.println("There can be mismatches in expected and actual final sizes");

        // Solve it with Synchronized keyword
        System.out.println("\n====Thread Safe With Synchronize keyword====");
        concurrentAddToList(SafeListWithSynchronize.class, numOfThreads, numOfElementsPerThread, true);
        System.out.println("Expected and actual final sizes will always be the same");

        // Solve it with synchronized version of collection
        System.out.println("\n====Thread Safe with Synchronized Collection====");
        concurrentAddToList(SafeSynchronizedList.class, numOfThreads, numOfElementsPerThread, true);
        System.out.println("Expected and actual final sizes will always be the same");

        // Solve it with Vector
        System.out.println("\n====Thread Safe with Vector====");
        concurrentAddToList(SafeVectorList.class, numOfThreads, numOfElementsPerThread, true);
        System.out.println("Expected and actual final sizes will always be the same");

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

    private static void concurrentAddToList(Class<? extends ListOperations> listClass,  int numOfThreads, int numOfElementsPerThread, boolean isSafe) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        ListOperations list = listClass.getConstructor().newInstance();

        var threadPool = Executors.newFixedThreadPool(numOfThreads);
        threadPool = Executors.newFixedThreadPool(numOfThreads);

        for(int i = 0; i < numOfThreads; i++) {
            threadPool.submit(() -> {
                for(int j = 0; j < numOfElementsPerThread; j++) {
                    list.add(j);
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(24L, TimeUnit.HOURS);
        //list.printElements();

        System.out.println("Expected Final size: "+(numOfThreads * numOfElementsPerThread));
        System.out.println("Final size: "+(list.getSize()));

        if(isSafe) {
            assert (numOfThreads * numOfElementsPerThread) == list.getSize();
        }
    }
}