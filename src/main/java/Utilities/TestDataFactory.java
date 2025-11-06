package Utilities;

import com.github.javafaker.Faker;
import java.util.*;

public class TestDataFactory {

    private static final ThreadLocal<Faker> faker =
            ThreadLocal.withInitial(() -> new Faker(new Locale("en"), new Random()));

    private static final String[] FEMALE_TITLES = {"Mrs", "Ms", "Miss"};
    private static final String[] MALE_TITLES = {"Mr", "Dr", "Sir"};

    public static Map<String, String> generateCustomerData() {
        Map<String, String> data = new HashMap<>();
        String gender = faker.get().options().option("Male", "Female");
        String title = gender.equals("Female")
                ? faker.get().options().option(FEMALE_TITLES)
                : faker.get().options().option(MALE_TITLES);

        String firstName = faker.get().name().firstName();
        String lastName = faker.get().name().lastName();
        String fullName = firstName + " " + lastName;

        data.put("gender", gender);
        data.put("title", title);
        data.put("fullName", fullName);
        data.put("givenName", fullName);
        data.put("shortName", firstName);
        data.put("mnemonic", getMnemonic());
        data.put("sector", getSector());
        data.put("gbStreet", getAddress());
        data.put("nationality", getNationality());
        data.put("residence", getResidence());
        data.put("target", "999");
        data.put("accountOfficer", "1");
        data.put("customerStatus", "1");
        data.put("industry", "1000");
        data.put("currency", "USD");
        // Add other fields as needed

        return data;
    }

    public static String getMnemonic() {
        String initials = faker.get().letterify("???").toUpperCase();
        int number = faker.get().number().numberBetween(1, 99);
        return initials + number;
    }

    public static String getSector() {
        return faker.get().options().option("1001", "1002", "1003", "1004", "1005", "1006", "1007", "1008", "1009", "1010", "1099");
    }

    public static String getAddress() {
        return faker.get().address().fullAddress();
    }

    public static String getNationality() {
        return faker.get().options().option("AD", "AE", "AF", "CA", "EG", "US");
    }

    public static String getResidence() {
        return faker.get().options().option("AD", "AE", "AF", "CA", "EG", "US");
    }
}
