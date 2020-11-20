package com.stream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestYC {

    public static boolean isHaveLetter(String s) {
        Pattern p = Pattern.compile(".*[a-zA-z].*");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static boolean isHaveNum(String str) {
        Pattern pattern = Pattern.compile(".*[0-9].");
        return pattern.matcher(str).matches();
    }

    public static boolean isAlphaNumeric(String s){
        Pattern p = Pattern.compile("[0-9a-zA-Z]{1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static void main(String[] args) {
        String s = "abcdr1231)";
        System.out.println(isHaveLetter(s));
        System.out.println(isHaveNum(s));
        System.out.println(isAlphaNumeric(s));

    }
}
