package dates;

import java.util.*;

public class Dates {

    public static Scanner reader = new Scanner(System.in);
    private static final ArrayList<String> rawDates = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            String line = reader.nextLine();
            if (line.equals(".")) {
                break;
            }
            rawDates.add(line);
        }

        ArrayList<String> dates = processDates();

        for (String s : dates) {
            System.out.println(processDate(s));
        }

    }

    public static ArrayList<String> processDates() {
        ArrayList<String> processedDates = new ArrayList<>();

        for (String rawDate : rawDates) {
            processedDates.add(processDate(rawDate));
        }

        return processedDates;
    }

    public static String processDate(String date) {
        String year, month, day;

        String[] splitted = splitDate(date);
        if (Integer.parseInt(splitted[0]) > 31) {
            year = splitted[0];
            month = splitted[1];
            day = splitted[2];
        } else {
            year = splitted[2];
            month = splitted[0];
            day = splitted[1];
        }

        if (year.length() < 4) {
            year = "20" + year;
        }

        if (month.length() < 2) {
            month = "0" + month;
        }

        if (day.length() < 2) {
            day = "0" + day;
        }

        return year + "-" + month + "-" + day;
    }

    public static String[] splitDate(String date) {
        String[] splitted;
        char currChar = ' ';

        for (int i = 0; i < date.length(); i++) {
            currChar = date.charAt(i);
            if (currChar < 48 || currChar > 57) {
                break;
            }
        }

        splitted = date.split(Character.toString(currChar));
        return splitted;
    }

}
