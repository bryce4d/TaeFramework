package utilities;

import org.apache.log4j.Logger;

public class TaeLog {

    public static void info(String msg, Class clazz) {
        Logger logger = getLogger(clazz);
        logger.info(msg);
    }

    public static void debug(String msg, Class clazz) {
        Logger logger = getLogger(clazz);
        if(logger.isDebugEnabled()){
            logger.debug(msg);
        }
    }

    public static void warn(String msg, Class clazz) {
        Logger logger = getLogger(clazz);
        logger.warn(msg);
    }

    public static void error(String msg, Class clazz) {
        Logger logger = getLogger(clazz);
        logger.error(msg);
    }

    public static void error(Exception e, Class clazz) {
        Logger logger = getLogger(clazz);
        logger.error(e.getMessage());
        logger.error(e.getCause());
        for (StackTraceElement st : e.getStackTrace()) {
            logger.error(st.toString());
        }
    }

    public static Logger getLogger(Class clazz) {
        return Logger.getLogger(clazz);
    }
}
