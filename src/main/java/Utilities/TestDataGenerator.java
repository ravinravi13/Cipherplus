package Utilities;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestDataGenerator {



    private Faker faker;
    private DateTimeFormatter dateFormatter ;
    private Random random;

    public TestDataGenerator() {
        this.faker = new Faker();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.random = new Random();
    }

    public String generateEventName() {
        return faker.company().name();
    }

    public String generateEventDescription() {
        return faker.lorem().sentence();
    }

    public String generateLocation() {
        return faker.address().city();
    }

    public int generateRandomPointsValue() {
        return random.nextInt(201) - 100; // Random value between -100 and 100
    }

    public String generateRandomDate(boolean valid) {
        if (valid) {
            // Generating a future date within the range of 5 to 10 years from now
            LocalDate futureDate = LocalDate.now().plusYears(random.nextInt(6) + 5);
            return futureDate.format(dateFormatter);
        } else {
            return "30-02-2023"; // Returning an invalid date for the sake of example
        }
    }

    public String generateApprover() {
        return faker.name().fullName();
    }

    public static String generateFutureDate(String dateOpened) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date openedDate = sdf.parse(dateOpened); // Parse the opened date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(openedDate);
            calendar.add(Calendar.DAY_OF_MONTH, 30); // Add 30 days to the opened date
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int generateRandomQuantity() {
        int negativeNumber = -5; // You can define a constant or default value
        return random.nextInt(6) + negativeNumber; // Adjusts randomly based on the set value
    }












}
