package com.example.umc4_delivery_people.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 이메일 형식 확인
 */
public class ValidationRegex {
    public static boolean isRegexEmail(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static boolean isRegexPhoneNumber(String target) {
        String regex = "^(010|011|016|017|018|019)-[0-9]{3,4}-[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }
}
