package edu.hw2.Task4;

@SuppressWarnings("HideUtilityClassConstructor")
public class CallingInfoUtils {
    public static CallingInfo callingInfo() {
        var stackTrace = new Throwable().getStackTrace();

        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        return new CallingInfo(className, methodName);
    }
}
