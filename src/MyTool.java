
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MSI
 */
public class MyTool {

    public static final Scanner sc = new Scanner(System.in);

    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeFile(String filename, List<String> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
            if (!valid) {
                System.out.println("[Wrong format. Please enter again]");
            }
        } while (!valid);
        return input;
    }

    public static String readPatternWithOptionalInput(String message, String pattern) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty() || validStr(input, pattern)) {
                break;
            } else {
                System.out.println("[Wrong format. Please enter again or leave empty]");
            }
        } while (true);
        return input;
    }

    public static String readOptionalBlank(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print(" -> This file cannot be empty, try again. ");
            }
        } while (input.isEmpty());
        return input;
    }

    public static String realAlphaString(String message) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            valid = input.matches("[a-zA-Z ]+");
            if (!valid) {
                System.out.println(" -> Invalid input. Please enter again: ");
            }
        } while (!valid);
        return input;
    }

    public static String readOptionalAlphaString(String message) {
        String input;
        boolean valid;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return input;
            }
            valid = input.matches("[a-zA-Z]{5,}$");
            if (!valid) {
                System.out.println(" -> Invalid input. Please enter a string with alphabetic characters only, no spaces, and at least 5 characters long:  ");
            }
        } while (!valid);
        return input;
    }

    public static int readInt(String message) {
        int value;
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value <= 0 && value > 2024) {
                    System.out.print(" -> Please enter again: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print(" [ Invalid input ] ");
            }
        }
        return value;
    }

    public static Integer readOptionalInt(String message) {
        Integer value = null;
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                value = Integer.parseInt(input);
                if (value > 0 && value < 2025) {
                    break;
                } else {
                    System.out.print(" ->Invalid input. Please enter a valid integer between 1 and 2024, or leave it blank to skip: ");
                }
            } catch (NumberFormatException e) {
                System.out.print(" ->Invalid input. Please enter a valid integer or leave it blank to skip: ");
            }
        }
        return value;
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean isValidDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1; // Month is 0-based in Calendar
        int year = cal.get(Calendar.YEAR);

        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1) {
            return false;
        }
        switch (month) {
            case 2:
                if (isLeapYear(year)) {
                    return day <= 29;
                } else {
                    return day <= 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return day <= 30;
            default:
                return day <= 31;
        }
    }

    public static Date readValidDate(String message, String dateFormat, boolean optional) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date = null;
        boolean isValid = false;
        do {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty() && optional) {
                isValid = true;
            } else {
                try {
                    date = sdf.parse(input);
                    if (isValidDate(date)) {
                        isValid = true;
                    } else {
                        System.out.println(" [ Invalid date. Please enter a valid date. ] ");
                    }
                } catch (ParseException e) {
                    System.out.println(" [ Invalid format. Enter again or leave it empty ] ");
                }
            }
        } while (!isValid);
        return date;
    }

    public static Date readValidDateNotEmpty(String message, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        Date date = null;
        boolean isValid = false;
        do {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Date cannot be empty. ");
            } else {
                try {
                    date = sdf.parse(input);
                    if (isValidDate(date)) {
                        isValid = true;
                    } else {
                        System.out.println(" [ Invalid date. Please enter a valid date. ] ");
                    }
                } catch (ParseException e) {
                    System.out.println(" [Invalid date format] ");
                }
            }
        } while (!isValid);
        return date;
    }

    public static <T> void updateIfNotNullOrEmpty(T value, Consumer<T> updater) {
        if (value != null && !value.toString().trim().isEmpty()) {
            updater.accept(value);
        }
    }

    public static String readYesNo(String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = sc.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                break;
            } else {
                System.out.print("Invalid input. Please enter 'Y' for yes or 'N' for no: ");
            }
        }
        return input;
    }
    
    public static Boolean readOptionalBoolean(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false', or leave it empty to skip.");
            }
        }
    }

}

