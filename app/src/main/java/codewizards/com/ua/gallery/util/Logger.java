package codewizards.com.ua.gallery.util;

import android.util.Log;

/**
 * Created by dmikhov on 29.07.2016.
 */
public class Logger {
    private Class<?> cls;

    private Logger() {}

    private Logger(Class<?> cls) {
        this.cls = cls;
    }

    public static Logger getLogger(Class<?> cls) {
        return new Logger(cls);
    }

    public static void v(Class cls, String message) {
        Log.v(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void d(Class cls, String message) {
        Log.d(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void i(Class cls, String message) {
        Log.i(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void w(Class cls, String message) {
        Log.w(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void e(Class cls, String message) {
        Log.e(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void verbose(Class cls, String message) {
        Log.v(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void debug(Class cls, String message) {
        Log.d(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void info(Class cls, String message) {
        Log.i(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void warn(Class cls, String message) {
        Log.w(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public static void error(Class cls, String message) {
        Log.e(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public void v(String message) {
        Log.v(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public void d(String message) {
        Log.d(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public void i(String message) {
        Log.i(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public void w(String message) {
        Log.w(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

    public void e(String message) {
        Log.e(Const.TAG, cls.getSimpleName() + " >> " + message);
    }

}
