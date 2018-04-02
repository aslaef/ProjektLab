/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.HashMap;
import java.util.Map;

/**
 * Logger osztálya
 * @author Rottenhoffer
 */
public abstract class LOGGER {

    private static final Map<Object, String> objects = new HashMap<>();
    private static final Map<Object, String> levels = new HashMap<>();

    private static boolean loggingTime = true;

    private static String tabs = "";

    public static boolean isLoggingTime() {
        return loggingTime;
    }

    public static void setLoggingTime(boolean loggingTime) {
        LOGGER.loggingTime = loggingTime;
    }

    public static void log(Object object) {
        if (!loggingTime) {
            return;
        }

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement: stackTraceElements) {
            if (object.getClass().getName().equals(stackTraceElement.getClassName())
                    || object.getClass().getSuperclass().getName().equals(stackTraceElement.getClassName())) {
                System.out.print(" --> ");
                System.out.print(stackTraceElement.getMethodName());
                System.out.print("(" + objects.get(object) + ")");
                return;
            }
        }
    }

    public static void newLevel(Object object) {
        tabs += "\t\t\t\t";
        levels.put(object, tabs);
    }

    public static void sameLevel(Object object) {
        System.out.print("\n" + levels.get(object));
    }

    public static void endLevel(Object object) {
        tabs = tabs.substring(4);
        levels.remove(object);
    }

    public static void addObject(Object object, String name) {
        objects.put(object, name);
    }

    public static void newUseCase(String useCaseName) {
        objects.clear();
        levels.clear();
        System.out.println("\nUse case: " + useCaseName);
    }

}
