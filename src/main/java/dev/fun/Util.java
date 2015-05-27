package dev.fun;


/**
 *
 * Util provide methods as Utility
 * Created by Selina on 5/25/15.
 */
public class Util {

    public static final String JAVA_VERSION = getVersion ();

    public static String getVersion () {

        //version could be 1.8.0_45
        return System.getProperty("java.version");
    }

    public static boolean isJava8(String version) {
        return version.startsWith("1.8");
    }

    public static int getRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
