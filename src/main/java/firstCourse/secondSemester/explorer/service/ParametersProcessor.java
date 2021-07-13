package firstCourse.secondSemester.explorer.service;

import firstCourse.secondSemester.explorer.entities.InputString;

import java.util.Map;

public class ParametersProcessor {
    private static boolean wasWarning = false;

    public static void parametersSetting(InputString input, Map<Character, Boolean> available) {
        for (char p : input.getParameters()) {
            if (available.containsKey(p)) available.replace(p, true);
            else wasWarning = getWarning(p);
        }
        if (wasWarning) {
            System.out.println();
            wasWarning = false;
        }
    }

    public static void parametersReset(Map<Character, Boolean> available) {
        for (char el : available.keySet()) {
            available.replace(el, false);
        }
    }

    private static boolean getWarning(char p) {
        System.out.println("Attention: <" + p + "> parameter not available for this command.");
        return true;
    }
}
