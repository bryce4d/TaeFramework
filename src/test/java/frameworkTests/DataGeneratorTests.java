package frameworkTests;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataGenerator;

import java.util.regex.Pattern;

public class DataGeneratorTests {

    @Test
    public void generateString() {
        String s1 = DataGenerator.generateString();
        String s2 = DataGenerator.generateString();

        Assert.assertNotEquals(s1, s2, "Generated the same String twice in a row");
        Assert.assertEquals(s1.length(), s2.length(), "the generated strings are of different lengths");
        Assert.assertEquals(s1.length(), 32, "the generate string was a different length");

        Pattern p1 = Pattern.compile("[\\w|\\d]+");
        Assert.assertTrue(p1.matcher(s1).matches(), "generated string has more than numbers and letters");
        Assert.assertTrue(p1.matcher(s2).matches(), "generated string has more than numbers and letters");
    }

    @Test
    public void generateStringOfLength() {
        String s0 = DataGenerator.generateString(0);
        String s31 = DataGenerator.generateString(31);
        String s32 = DataGenerator.generateString(32);
        String s33 = DataGenerator.generateString(33);
        String s112 = DataGenerator.generateString(112);

        Assert.assertEquals(s0.length(), 0, "the string should be of length 0");
        Assert.assertEquals(s31.length(), 31, "the string should be of length 31");
        Assert.assertEquals(s32.length(), 32, "the string should be of length 32");
        Assert.assertEquals(s33.length(), 33, "the string should be of length 33");
        Assert.assertEquals(s112.length(), 112, "the string should be of length 112");
    }

    @Test
    public void generateNumber() {
        int n1 = DataGenerator.generateNumber();
        int n2 = DataGenerator.generateNumber();

        Assert.assertNotEquals(n1, n2, "did not get different numbers");
    }

    @Test
    public void generateNumberLength() {
        int n1 = DataGenerator.generateNumber(5);
        int n2 = DataGenerator.generateNumber(1);

        Assert.assertTrue(n2 < 10, "Number should be less than 10 to have a length of 1");
        Assert.assertTrue(n1 < 100000, "Number should be less than 100000 to have a length of 5");
        Assert.assertTrue(n1 > 10000, "Number should be greater than 10000 to have a length of 5");
    }

    @Test
    public void generateEmail() {
        String email1 = DataGenerator.generateEmail();
        String email2 = DataGenerator.generateEmail();

        Assert.assertNotEquals(email1, email2, "emails should be different");
    }

    @Test
    public void generatePhoneNumber() {
        String phone1 = DataGenerator.generatePhone();
        String phone2 = DataGenerator.generatePhone();

        Assert.assertNotEquals(phone1, phone2, "phone numbers should not match");
        Pattern p1 = Pattern.compile("\\(\\d\\d\\d\\) \\d\\d\\d-\\d\\d\\d\\d");

        Assert.assertTrue(p1.matcher(phone1).matches(), "did not match a phone number format (xxx) xxx-xxxx");
        Assert.assertTrue(p1.matcher(phone2).matches(), "did not match a phone number format (xxx) xxx-xxxx");
    }

    @Test
    public void generateLetters() {
        String s0 = DataGenerator.generateLetters(0);
        String s1 = DataGenerator.generateLetters(1);
        String s2 = DataGenerator.generateLetters(64);
        String s3 = DataGenerator.generateLetters(64);

        Assert.assertNotEquals(s2, s3, "strings should be different");
        Assert.assertEquals(s0.length(), 0, "should be of length 0");
        Assert.assertEquals(s1.length(), 1, "should be of length 1");
        Assert.assertEquals(s2.length(), 64, "should be of length 64");
        Assert.assertEquals(s3.length(), 64, "should be of length 64");

        Pattern p1 = Pattern.compile("\\w+");
        Assert.assertTrue(p1.matcher(s1).matches(), "generated more than just letters "+ s1);
        Assert.assertTrue(p1.matcher(s2).matches(), "generated more than just letters "+ s2);
        Assert.assertTrue(p1.matcher(s3).matches(), "generated more than just letters "+ s3);
    }

    @Test
    public void generateDate() {
        DateTime d = DataGenerator.generateDateTime();

        Assert.assertTrue(d.isAfter(DateTime.now()), "the generated date is not after now");
        Assert.assertTrue(d.isBefore(DateTime.now().plusDays(61)), "the generated date is too far out");
    }

    @Test
    public void generateStreetAddress() {
        String address = DataGenerator.generateStreetAddress();

        Assert.assertTrue(address.contains("Ave")||address.contains("St")||address.contains("N")||address.contains("S"), "this address is not valid");
    }
}
