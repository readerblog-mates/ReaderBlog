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

    /**
     * Проверка выхода значения Double за границы 0.0 - 10.0, так же округляет.
     * @param rating
     * @return
     */
    public static Double checkRatingLimits(Double rating){
        if (rating != null){
            if (rating > 10.0)
                return 10.0;
            if (rating < 0.0)
                return 0.0;
            return roundingRating(rating);
        }
        return null;
    }
}
