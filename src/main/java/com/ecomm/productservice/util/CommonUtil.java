package com.ecomm.productservice.util;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CommonUtil {

	public static String convertToString(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Long getTimeTaken(Long startTime) {
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	public static boolean validateMobileNumber(String number) {
		boolean valied= true;
		if(number.length()!=10) {
			valied=false;
		}else {
			String areaCode = number.substring(0,4);
			if(!areaCode.equals("9609")) {
				valied=false;
			}
		}

		return valied;
	}
	
	public static String validateEmpty(String string) {
		return (string == null || string.isEmpty()) ? null : string;
	}
	
	public static boolean validateFixedNumber(String number) {
		boolean valied= true;
		boolean valiedLength = false;
		
		if(number.length()==4 || number.length()==7 || number.length()==8) {
			valiedLength=true;
		}
		
		if(Boolean.TRUE.equals(valiedLength)) {
			String areaCode = number.substring(0,3);
			if(!areaCode.equals("400")) {
				valied=false;
			}
		}else {
			valied=false;
		}

		return valied;
	}
	
	public static boolean validateSerialNumber(String number) {
		boolean valied = true;
		String regex = "[0-9]+";
		
		Pattern p = Pattern.compile(regex);
		if (number == null) {
            return false;
        }
		Matcher m = p.matcher(number);
		
		if(!m.matches()) {
			return false;
		}

		if(number.length() != 16) {
			valied=false;
		}else {
			String areaCode = number.substring(0,3);
			boolean validAreaCode = false;
			
			if(areaCode.equals("960") ) {
				validAreaCode = true;				
			}
			
			if(!validAreaCode) {
				valied = false;
			}
		}

		return valied;
	}
	
	public static boolean validateReleaseSerial(String number) {
		boolean valied = true;
		String regex = "[0-9]+";
		
		Pattern p = Pattern.compile(regex);
		if (number == null) {
            return false;
        }
		Matcher m = p.matcher(number);
		
		if(!m.matches()) {
			return false;
		}

		if(number.length() != 19) {
			valied=false;
		}
		return valied;
	}
	

	public static boolean isLengthEqualValid(String inputString, int length) {
		return (inputString.length() == length);
	}
	
	public static boolean hasEmptySpace(String string) {
		Pattern whitespace = Pattern.compile("\\s");
		Matcher matcher = whitespace.matcher(string);
		boolean expressionMatcher = false;
		if (matcher.find()) {
			expressionMatcher = true;
		}
		return expressionMatcher;
	}
	
	public static boolean haveSpecialCharacters(String input) {

		Pattern special = Pattern.compile("[^A-Za-z0-9]+");
		Matcher hasSpecial = special.matcher(input);
		return (hasSpecial.find());
	}
	
	public static boolean isTraceIdFormat(String inputString) {
		String head = inputString.substring(0, 3);
		String tail = inputString.substring(3, 15);
		boolean expressionHeadTail = true;
		if (!isCharacters(head) || !isNumeric(tail)) {
			expressionHeadTail = false;
		}
		return expressionHeadTail;
	}
	
	public static boolean isCharacters(String input) {
		return input.matches("^[a-zA-Z]*$");
	}

	public static boolean isNumeric(String stringNumber) {
		if (stringNumber == null) {
			return false;
		}
		try {
			Double.parseDouble(stringNumber);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static String createTraceId() {
		SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMddHHmm");
		return "TRC" + dateTime.format(new Date());
	}
	
	public static String getExecutionTime(long startTime) {
		long endTime = System.currentTimeMillis();
		return String.format("%d ms", (endTime - startTime));
	}
	
	
	
	
}
