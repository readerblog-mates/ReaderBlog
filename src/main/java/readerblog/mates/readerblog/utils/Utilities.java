package readerblog.mates.readerblog.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utilities {
    public static String getCurrentMethodName(){
        StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();
        return "method: \"" + stackTraceElements[1].getMethodName() + "\"";
    }

    /**
     * Округление значения Double до 1 знака после запятой (в большую сторону).
     * @param rating
     * @return
     */
    public static Double roundingRating(Double rating){
        if (rating != null)
            return BigDecimal.valueOf(rating).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return null;
    }
}
