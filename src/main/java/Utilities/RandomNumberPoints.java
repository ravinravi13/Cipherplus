package Utilities;

import java.util.Random;

public class RandomNumberPoints {


    private static int runCount = 0; // Static variable to track the number of runs
    private static Random random;

    public RandomNumberPoints() {
        random = new Random();
    }

    public static int generateRandomNumber() {
        // Generate a random number with a specified number of digits
        int numberOfDigits = random.nextInt(3) + 1; // Randomly choose between 1 to 3 digits
        int randomNumber;

        if (runCount % 2 == 0) {
            // First run (even runCount): Generate a positive number
            randomNumber = generatePositiveNumber(numberOfDigits);
            System.out.println("Positive Random Number: " + randomNumber);
        } else {
            // Second run (odd runCount): Generate a negative number
            randomNumber = generateNegativeNumber(numberOfDigits);
            System.out.println("Negative Random Number: " + randomNumber);
        }

        runCount++; // Increment the run count
        return randomNumber; // Return the generated number
    }

    private static int generatePositiveNumber(int digits) {
        // Generate a positive number with the specified number of digits
        int min = (int) Math.pow(10, digits - 1); // Minimum value for the given number of digits
        int max = (int) Math.pow(10, digits) - 1; // Maximum value for the given number of digits
        return random.nextInt(max - min + 1) + min; // Generate a random number in the range
    }

    private static int generateNegativeNumber(int digits) {
        // Generate a negative number with the specified number of digits
        int positiveNumber = generatePositiveNumber(digits); // Generate a positive number
        return -positiveNumber; // Return the negative of that number
    }







}









































