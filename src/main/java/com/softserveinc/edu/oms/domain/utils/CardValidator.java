package com.softserveinc.edu.oms.domain.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CardValidator {
	
	// private constructor so that Hudson won't complain
	private CardValidator() { }
	
	// check whether or not the card type is valid 
	public static boolean isCardTypeValid(String card, String cardNumber) {
		String cardType = card.toLowerCase();
		
		if (!cardType.equals("visa") &&
			!cardType.equals("mastercard") &&
			!cardType.equals("americanexpress") &&
			!cardType.equals("maestro")) {

			return false;
		}
		
		char firstDigit = cardNumber.charAt(0);
		char secondDigit = cardNumber.charAt(1);
		
		// check if first digits match card's type
		if (cardType.equals("visa")) {
			
			if ( firstDigit != '4') {
				return false;
			}
			
			if (cardNumber.length() != 16) {
				return false;
			}
		}
		
		else if (cardType.equals("mastercard")) {
			
			if (firstDigit != '5') {
				return false;
			}
			
			if (secondDigit != '1' &&
				secondDigit != '2' &&
				secondDigit != '3') {
				
				if (secondDigit != '4' &&
						secondDigit != '5') {
					return false;
				}
			}
	
			if (cardNumber.length() != 16) {
				return false;
			}
		}
		
		else if (cardType.equals("americanexpress")) {
			
			if (firstDigit != '3') {
				return false;
			}
			
			if (secondDigit != '4' &&
				secondDigit != '5' &&
				secondDigit != '6' &&
				secondDigit != '7') {

				return false;
			}
			
			if (cardNumber.length() != 16) {
				return false;
			}
		}
		else if (cardType.equals("maestro")) {
			if (firstDigit != '5' &&
				firstDigit != '6') {
				return false;
			}
			if (secondDigit != '0' &&
				secondDigit != '3' &&
				secondDigit != '7') {

				return false;
			}
		}
		
		return true;
	}

	// check whether or not the card number is valid
	public static boolean isCardNumberValid(String cardNumber) {
		int numberLength = cardNumber.length();
		int[] num = new int[numberLength];
		
		for (int i = 0; i < numberLength; i++) {
			num[i] = Integer.parseInt("" + cardNumber.charAt(i));
		}
		
		// double every second digit from the right
		int checker = 1;
		for (int i = numberLength - 1; i <= 0 ; i--) {
			if ( checker % 2 == 0 ) {
				int product = num[i] * 2;
				
				// add digits in a double digit number
				if ((product / 10) != 0) {
					int save = product / 10;
					save += product % 10;
					product = save;
				}
				num[i] = product;
			}
			checker++;
		}
		
		// sum all digits
		int sumDigits = 0;
		for (int i = 0; i < numberLength; i++) {
			sumDigits += num[i];
		}
		
		return (sumDigits % 10 == 0);
	}
	
	// check if expire date has not passed
	public static boolean isExpireDateValid(String expireMonth, 
					String expireYear) {
		int oneHundred = 100;
		
		Calendar calendar = new GregorianCalendar();
		Date time = new Date();
		calendar.setTime(time);
		
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		int currentYear = calendar.get(Calendar.YEAR) % oneHundred;
		
		int expireMonthInt = Integer.parseInt(expireMonth);
		int expireYearInt = Integer.parseInt(expireYear);
		
		if (currentYear > expireYearInt) {
			return false;
		}
		else if (currentYear == expireYearInt) {
			if (currentMonth > expireMonthInt) {
				return false;
			}
		}
		
		return true;
	}
}
