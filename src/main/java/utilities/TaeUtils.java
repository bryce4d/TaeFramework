package utilities;

import java.util.function.BooleanSupplier;

public class TaeUtils {

    public static void sleep(long milliseconds) {
        try {
            TaeLog.debug("sleeping for " + milliseconds/1000.0 + " seconds", TaeUtils.class);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            TaeLog.error(e, TaeUtils.class);
            e.printStackTrace();
        }
    }

    static public boolean waitForTrue(BooleanSupplier evaluate) {
        return waitForTrue(evaluate, 5);
    }

    static public boolean waitForTrue(BooleanSupplier evaluate, int maxSeconds) {
        TaeLog.debug("waiting for statement to be true, waiting for a max of " + maxSeconds + " seconds", TaeUtils.class);
        maxSeconds *= 2;
        while (!evaluate.getAsBoolean() && --maxSeconds >= 0) {
            sleep(500);
        }
        return evaluate.getAsBoolean();
    }
}
