package com.kursach;

import java.util.*;
import java.util.concurrent.*;

public class SquareCalculator {
    public static void main(String[] args) {
        // Генерація масиву випадкових дробових чисел у діапазоні [0.5, 99.5]
        Random random = new Random();
        int size = 50;
        double[] numbers = random.doubles(size, 0.5, 99.5).toArray();

        System.out.println("Generated array of numbers: " + Arrays.toString(numbers));

        // Розбивка масиву на частини по 10 елементів
        List<double[]> chunks = new ArrayList<>();
        int chunkSize = 10;
        for (int i = 0; i < numbers.length; i += chunkSize) {
            chunks.add(Arrays.copyOfRange(numbers, i, Math.min(numbers.length, i + chunkSize)));
        }

        // ExecutorService для виконання завдань
        ExecutorService executor = Executors.newFixedThreadPool(chunks.size());
        List<Future<Set<Double>>> futures = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        // Callable для обчислення квадратів
        for (double[] chunk : chunks) {
            Callable<Set<Double>> task = () -> {
                Set<Double> resultSet = new CopyOnWriteArraySet<>();
                for (double number : chunk) {
                    resultSet.add(Math.pow(number, 2));
                }
                System.out.println(Thread.currentThread().getName() + " processed: " + resultSet);
                return resultSet;
            };
            futures.add(executor.submit(task));
        }
        System.out.println(Thread.currentThread().getName());
        // Збір результатів та перевірка стану завдань
        Set<Double> allResults = new CopyOnWriteArraySet<>();
        for (Future<Set<Double>> future : futures) {
            try {
                if (!future.isCancelled() && !future.isDone()) {
                    Set<Double> result = future.get();
                    allResults.addAll(result);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Виведення результатів
        System.out.println("Squares of numbers: " + allResults);

        // Виведення часу виконання
        long endTime = System.currentTimeMillis();
        System.out.println("Program execution time: " + (endTime - startTime) + " ms");

        // Завершення роботи ExecutorService
        executor.shutdown();
    }
}
