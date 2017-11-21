package utilities;

public class TaeLog {

    public static void Info(String msg, Class clazz) {
        System.out.println("[" + clazz+ "] " + msg);
    }

    public static void Debug(String msg, Class clazz) {
        System.out.println("[" + clazz+ "] " + msg);
    }

    public static void Warn(String msg, Class clazz) {
        System.out.println("[" + clazz+ "] " + msg);
    }

    public static void Error(String msg, Class clazz) {
        System.out.println("[" + clazz+ "] " + msg);
    }
}