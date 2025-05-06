package com.javaweb.utils;

public class IntegerUtil {
    public static boolean isNumber(String data) {
    	
    	try {
    		Integer.parseInt(data);
    		return true;
    	}
    	catch(NumberFormatException e) {
    		return false;
    	}
    }
}
