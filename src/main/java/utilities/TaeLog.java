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

    public static void Error(Exception e, Class clazz) {
        System.out.println("Exception from " + clazz);
        System.out.println(e.getMessage());
        System.out.println(e.getCause().getMessage());
        for (StackTraceElement st : e.getStackTrace()) {
            System.out.println(st.toString());
        }
    }
}
