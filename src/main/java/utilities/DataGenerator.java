package utilities;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class DataGenerator {

    public static String generateLetters() {
        return generateLetters(8);
    }

    public static String generateLetters(int length) {
        String letters = getLetters(length);
        TaeLog.Debug("generated letters: " + letters, DataGenerator.class);
        return letters;
    }

    public static String generateString() {
        String s1 = getString();
        TaeLog.Debug("generated string: " + s1, DataGenerator.class);
        return s1;
    }

    public static String generateString(int length) {
        int amount = (length/32);
        StringBuilder generated = new StringBuilder(getString());
        for (int i = 0; i < amount; ++i) {
            generated.append(getString());
        }
        String s1 = generated.subSequence(0, length).toString();
        TaeLog.Debug("generated string: " + s1, DataGenerator.class);
        return s1;
    }

    public static int generateNumber() {
        int number = getNumber(0, 1000);
        TaeLog.Debug("generated number: " + number, DataGenerator.class);
        return number;
    }

    public static int generateNumber(int min, int max) {
        int number = getNumber(min, max);
        TaeLog.Debug("generated number: " + number, DataGenerator.class);
        return number;
    }

    public static int generateNumber(int length) {
        String min = StringUtils.rightPad("1", length, "0");
        String max = StringUtils.rightPad("9", length, "9");

        int number = getNumber(Integer.parseInt(min), Integer.parseInt(max));
        TaeLog.Debug("generated number: " + number, DataGenerator.class);
        return number;
    }

    public static long generateLongNumber(long min, long max) {
        long num = generateLongNumber(min, max);
        TaeLog.Debug("generated long number: " + num, DataGenerator.class);
        return num;
    }

    public static String generateEmail() {
        String email = "TAE-" + getString().substring(0,8) + "@automation.qa";
        TaeLog.Debug("generated email: " + email, DataGenerator.class);
        return email;
    }

    public static String generatePhone() {
        int areaCode = getNumber(100, 999);
        int prefix = getNumber(100, 999);
        int number = getNumber(1000, 9999);
        String phone = "(" + areaCode + ") " + prefix + "-" + number;
        TaeLog.Debug("generated phone number: " + phone, DataGenerator.class);
        return phone;
    }

    public static DateTime generateDateTime(DateTime after) {
        DateTime generated = after.plus(getLongNUmber(0,TimeUnit.DAYS.toMillis(60)));
        TaeLog.Debug("generated date: " + generated.toString("MM/dd/yyyy hh:mm:ss"), DataGenerator.class);
        return generated;
    }

    public static DateTime generateDateTime() {
        return generateDateTime(DateTime.now());
    }

    public static String generateStreetAddress() {
        int street = getNumber(100, 99999);
        int house = getNumber(1000, 99999);
        String address = "";

        switch (getNumber(0,5)) {
            case 0:
                address = house + " " + street + " Ave";
                break;
            case 1:
                address =  house + " " + street + " St";
                break;
            case 2:
                address =  house + " W " + street + " N";
                break;
            case 3:
                address =  house + " E " + street + " N";
                break;
            case 4:
                address =  house + " W " + street + " S";
                break;
            case 5:
                address =  house + " E " + street + " S";
                break;
        }
        TaeLog.Debug("generated address: " + address, DataGenerator.class);
        return address;
    }

    private static String getString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static int getNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static long getLongNUmber(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    private static String getLetters(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder l = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            l.append(alphabet.charAt(getNumber(0, 51)));
        }
        return l.toString();
    }
}