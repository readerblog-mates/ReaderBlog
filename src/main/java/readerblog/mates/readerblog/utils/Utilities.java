package readerblog.mates.readerblog.utils;

public class Utilities {
    public static String getCurrentMethodName(){
        StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();
        return "method: \"" + stackTraceElements[1].getMethodName() + "\"";
    }
}
