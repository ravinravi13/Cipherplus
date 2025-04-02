package Utilities;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataGenerator {



    private Faker faker;
    private DateTimeFormatter dateFormatter ;
    private Random random;

    public TestDataGenerator() {
        this.faker = new Faker();
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
            LocalDate futureDate = LocalDate.now().plusDays(random.nextInt(30) + 1);
            return futureDate.format(dateFormatter);
        } else {
            return "2023-02-30"; // Invalid date
        }
    }

    public String generateApprover() {
        return faker.name().fullName();
    }















}
