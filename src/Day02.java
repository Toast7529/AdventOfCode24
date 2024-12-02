import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Day02 {
    public static void partOne(String filePath) {
        int numberOfSafe = 0;
        try {
            File myFile = new File(filePath);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String[] line = myScanner.nextLine().split("\\s+");
                List<Integer> numbers = new ArrayList<>();
                for (String s : line) {
                    numbers.add(Integer.parseInt(s));
                }

                // Skip reports with fewer than 2 levels
                if (numbers.size() < 2) continue;

                boolean isSafe = true;
                boolean increasing = false;
                boolean initialized = false;
                for (int i = 0; i < numbers.size() - 1; i++) {
                    int diff = numbers.get(i + 1) - numbers.get(i);

                    if (abs(diff) < 1 || abs(diff) > 3) {
                        isSafe = false;
                        break;
                    }

                    if (!initialized) {
                        increasing = diff > 0;
                        initialized = true;
                    } else if ((diff > 0) != increasing) {
                        isSafe = false;
                        break;
                    }
                }

                if (isSafe) {
                    numberOfSafe++;
                }
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(numberOfSafe);
    }

    public static void partTwo(String filePath) {
        int numberOfSafe = 0;
        try {
            File myFile = new File(filePath);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String[] line = myScanner.nextLine().split("\\s+");
                List<Integer> numbers = new ArrayList<>();
                for (String s : line) {
                    numbers.add(Integer.parseInt(s));
                }

                if (isSafe(numbers)) {
                    numberOfSafe++;
                    continue;
                }

                boolean canBeMadeSafe = false;
                for (int i = 0; i < numbers.size(); i++) {
                    List<Integer> modified = new ArrayList<>(numbers);
                    modified.remove(i);

                    if (isSafe(modified)) {
                        canBeMadeSafe = true;
                        break;
                    }
                }

                if (canBeMadeSafe) {
                    numberOfSafe++;
                }
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(numberOfSafe);
    }

    private static boolean isSafe(List<Integer> numbers) {
        if (numbers.size() < 2) return false;

        boolean increasing = false;
        boolean initialized = false;

        for (int i = 0; i < numbers.size() - 1; i++) {
            int diff = numbers.get(i + 1) - numbers.get(i);

            if (abs(diff) < 1 || abs(diff) > 3) {
                return false; // Invalid difference
            }

            if (!initialized) {
                increasing = diff > 0;
                initialized = true;
            } else if ((diff > 0) != increasing) {
                return false; // Trend changed
            }
        }
        return true; // All checks passed
    }
}
