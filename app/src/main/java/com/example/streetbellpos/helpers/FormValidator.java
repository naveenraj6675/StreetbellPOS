package com.example.streetbellpos.helpers;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
    public static boolean requiredField(String fieldStr, int length) {
        if (fieldStr == null) {
            return false;
        } else {
            return fieldStr.length() >= length;
        }
    }

    public static boolean validateEmail(String emailAddress) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(emailAddress).matches();
    }

    public static boolean validateStringByRule(String string, String rule) {
        Pattern p = Pattern.compile(rule);
        // Match the given string with the pattern
        Matcher m = p.matcher(string);
        // check whether match is found
        return m.matches();
    }
}
