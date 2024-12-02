import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;

public class Day01 {
    public static void partOne(String filePath) {
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();
        int totalDistance = 0;
        try {
            File myFile = new File(filePath);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String[] numbers = myScanner.nextLine().split("\\s+");
                left.add(Integer.parseInt(numbers[0]));
                right.add(Integer.parseInt(numbers[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (!left.isEmpty() && !right.isEmpty()) {
            totalDistance += abs(left.poll() - right.poll());
        }
        System.out.println(totalDistance);
    }

    public static void partTwo(String filePath) {
        HashMap<Integer, Integer> occurrences = new HashMap<>();
        List<Integer> left = new ArrayList<>();
        int totalDistance = 0;
        try {
            File myFile = new File(filePath);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String[] numbers = myScanner.nextLine().split("\\s+");
                int rightNumber = Integer.parseInt(numbers[1]);
                left.add(Integer.parseInt(numbers[0]));
                occurrences.put(rightNumber, occurrences.getOrDefault(rightNumber, 0) + 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int number : left) {
            totalDistance += number * occurrences.getOrDefault(number, 0);
        }
        System.out.println(totalDistance);
    }
}
