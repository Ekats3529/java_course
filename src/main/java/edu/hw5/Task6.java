package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public static boolean isSubstring(String text, String str) {
        Pattern pattern = Pattern.compile(str);

        Matcher matcher = pattern.matcher(text);

        return matcher.find();
    }
}
