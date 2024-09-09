package ma.youcode.transport.validator;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * A generic validator class for various input types.
 */
public class Validator {
    private Scanner sc;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Validator() {
        sc = new Scanner(System.in);
    }

    /**
     * Gets a valid string input from the user.
     * @param prompt The input prompt message.
     * @param errorMessage The error message for invalid input.
     * @return A valid non-empty string.
     */
    public String getValidStringInput(String prompt, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (isString(input)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * Gets a valid timestamp input from the user.
     * @param prompt The input prompt message.
     * @return A valid timestamp.
     */
    public Timestamp getValidTimestampInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                dateFormat.setLenient(false);
                return new Timestamp(dateFormat.parse(input).getTime());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format " + DATE_FORMAT + ".");
            }
        }
    }

    /**
     * Gets a valid double input from the user.
     * @param prompt The input prompt message.
     * @param errorMessage The error message for invalid input.
     * @return A valid double value.
     */
    public double getValidDoubleInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * Gets a valid boolean input from the user.
     * @param prompt The input prompt message.
     * @param errorMessage The error message for invalid input.
     * @return A valid boolean value.
     */
    public boolean getValidBooleanInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * Validates if a string is non-null and non-empty.
     * @param str The string to validate.
     * @return true if the string is valid; false otherwise.
     */
    public static boolean isString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validates if an input string matches any value in a given enum.
     * @param input The input string.
     * @param enumClass The enum class to check against.
     * @return true if the input is a valid enum value; false otherwise.
     */
    public static <E extends Enum<E>> boolean isValidEnum(String input, Class<E> enumClass) {
        try {
            Enum.valueOf(enumClass, input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Gets a valid enum value from the user.
     * @param prompt The input prompt message.
     * @param enumClass The enum class to check against.
     * @param <E> The type of the enum.
     * @return A valid enum value.
     */
    public <E extends Enum<E>> E getValidEnumInput(String prompt, Class<E> enumClass) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().toUpperCase();
            if (isValidEnum(input, enumClass)) {
                return Enum.valueOf(enumClass, input);
            } else {
                System.out.println("Invalid input. Please enter a valid value from the list.");
            }
        }
    }

    public <E extends Enum<E>> E choiceOption(Class<E> enumClass) {
        E[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            throw new IllegalArgumentException("Enum class must have at least one enum constant");
        }
        System.out.println("Choose an option:");
        for (int i = 0; i < enumConstants.length; i++) {
            System.out.println((i + 1) + " - " + enumConstants[i].name());
        }
        while (true) {
            try {
                int choice = sc.nextInt();
                if (choice < 1 || choice > enumConstants.length) {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + enumConstants.length);
                    continue;
                }
                sc.nextLine();
                return enumConstants[choice - 1];
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            sc.nextLine();
        }
    }
}
