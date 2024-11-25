package com.example.cli.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputValidator {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Validate if the input is a valid integer.
     */
    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validate if the input is a valid double.
     */
    public static boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validate if the input is a valid date in yyyy-MM-dd format.
     */
    public static boolean isValidDate(String input) {
        try {
            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Parse the input string into a Date object.
     */
    public static Date parseDate(String input) throws ParseException {
        return DATE_FORMAT.parse(input);
    }

    /**
     * Validate if the input is non-empty.
     */
    public static boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Validate if the input matches one of the allowed values.
     */
    public static boolean isValidChoice(String input, String... allowedValues) {
        for (String value : allowedValues) {
            if (input.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
