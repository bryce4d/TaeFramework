package utilities;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaeProperties {

    private static Properties propertyReader = readInPropertyFile();

    private TaeProperties() {
        readInPropertyFile();
    }

    public static String getProperty(String key) {
        String value = System.getenv(key) == null ? propertyReader.getProperty(key) : System.getenv(key);
        if (value == null) {
            TaeLog.warn("property not found " + key, TaeProperties.class);
            return null;
        } else {
            value = buildPartialData(value);
            TaeLog.debug("getting property key: " + key + " value: " + value, TaeProperties.class);
        }
        return value;
    }

    public static void setProperty(String key, String value) {
        propertyReader.setProperty(key, value);
        TaeLog.debug("set property key: " + key + " value: " + value, TaeProperties.class);
    }

    public static boolean hasProperty(String key) {
        return System.getProperty(key) != null || propertyReader.containsKey(key);
    }

    private static Properties readInPropertyFile() {
        Properties propertyReader = new Properties();
        try {
            //load properties from the default file
            InputStreamReader inputStreamReader = new InputStreamReader(TaeProperties.class.getClassLoader().getResourceAsStream("default.properties"), "UTF-8");
            propertyReader.load(inputStreamReader);
            //load propertyReader from all the other files
            for (String fileName : propertyReader.getProperty("additionalPropertyFiles").split(",")) {
                inputStreamReader = new InputStreamReader(TaeProperties.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
                propertyReader.load(inputStreamReader);
            }
        } catch (IOException e) {
            TaeLog.error(e, TaeProperties.class);
            e.printStackTrace();
        }
        return propertyReader;
    }

    private static String buildPartialData(String template) {
        while (template.contains("{{") && template.contains("}}")) {
            //use regex matching
            Pattern compile = Pattern.compile("(\\{\\{.*?}})");
            Matcher matcher = compile.matcher(template).usePattern(compile);

            if (matcher.find()) {
                //find all the variables in the string and replace them
                String group = matcher.group(0);
                //need to remove the {{ }} to find the value
                String replaceProperty = getProperty(group.substring(2, group.length() - 2));
                template = template.replace(group, replaceProperty);
            }
        }
        return template;
    }
}
