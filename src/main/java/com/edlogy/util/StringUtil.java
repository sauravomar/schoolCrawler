package com.edlogy.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static String capitalizeFirstLettersTokenizer(final String s) {

		final java.util.StringTokenizer st = new java.util.StringTokenizer(s,
				" ", true);
		final StringBuilder sb = new StringBuilder();

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			token = String.format("%s%s",
					Character.toUpperCase(token.charAt(0)), token.substring(1).toLowerCase());
			sb.append(token);
		}

		return sb.toString();

	}

	public static String getFirstName(final String s, final String suffix) {
		final StringBuilder sb = new StringBuilder();
		
		if (isValidString(s)) {
			final java.util.StringTokenizer st = new java.util.StringTokenizer(s.trim()," ",true);
			if (st.hasMoreTokens()) {
				String token = st.nextToken();
				token = String.format("%s%s", Character.toUpperCase(token.charAt(0)), token.substring(1).toLowerCase());
				sb.append(token);
				if (suffix != null) {
					sb.append(suffix);
				}
			}
		}

		return sb.toString();
	}

	public static long stringTolong(final String propertyId) {
		if (propertyId != null && !propertyId.equals("")) {
			return Long.valueOf(propertyId);
		} else {
			return 0;
		}
	}

	public static int stringToint(final String propertyId) {
		if (propertyId != null && !propertyId.equals("")) {
			return Integer.valueOf(propertyId);
		} else {
			return 0;
		}
	}

	public static double stringToDouble(final String value) {
		if (value != null && !value.equals("")) {
			return Double.valueOf(value);
		} else {
			return 0.0;
		}
	}

	public static String formatMoney(final double value) {
		String sValue = "";
		if (value != 0) {
			final DecimalFormat df = new DecimalFormat("#.00");
			sValue = df.format(value);
		} else {
			sValue = String.valueOf(value);
		}

		return sValue;
	}

	public static String getUniqueKey() {
		final UUID idOne = UUID.randomUUID();
		return String.valueOf(idOne);
	}

	public static String sqlArrayToInString(final Set<String> a) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final String s : a) {
			// sb.append("?");
			sb.append(s);
			if (i < a.size() - 1) {
				sb.append(",");
			}
			i++;

		}

		return sb.toString();
	}
	
	public static String sqlArrayToInStringWithQuote(final Set<String> a) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final String s : a) {
			// sb.append("?");
			sb.append("'"+s.trim()+"'");
			if (i < a.size() - 1) {
				sb.append(",");
			}
			i++;

		}

		return sb.toString();
	}
	
	public static String sqlArrayToInString(final Collection<Long> a){
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final Long s : a) {
			sb.append(s);
			if (i < a.size() - 1) {
				sb.append(",");
			}
			i++;

		}
		return sb.toString();
	}
	
	public static String sqlArrayToInString(final String[] a) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final String s : a) {
			// sb.append("?");
			sb.append(s);
			if (i < a.length - 1) {
				sb.append(",");
			}
			i++;

		}

		return sb.toString();
	}

	public static String sqlArrayToLngStrng(final List<Long> a) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final Long s : a) {
			sb.append(s);
			if (i < a.size() - 1) {
				sb.append(",");
			}
			i++;

		}

		return sb.toString();
	}

	public static String sqlArrayToIntStrng(final ArrayList<Integer> a) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final Integer s : a) {
			sb.append(s);
			if (i < a.size() - 1) {
				sb.append(",");
			}
			i++;

		}

		return sb.toString();
	}

	public static String sqlArrayToStrng(final ArrayList<Long> a) {
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for (final Long s : a) {
			sb.append("?");
			if (i < a.size() - 1) {
				sb.append(",");
			}
			i++;

		}

		return sb.toString();
	}

	public static final boolean isValidString(final String text) {
		return text != null && text.trim().length() > 0;
	}

	public static final boolean isInvalidString(final String text) {
		return text == null || text.trim().length() == 0;
	}

	// String unique Id generation
	public static final String getUUID() {
		return RandomStringUtils.randomAlphanumeric(10);

	}

	// String unique Id generation
	public static final String getUUID(int keys) {
		return RandomStringUtils.randomAlphanumeric(keys);

	}

	// String for Booking Number
	public static final String getBookingNumber() {
		String data = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		if (StringUtils.isAlpha(data)) {
			Random rn = new Random();
			data = data + rn.nextInt(5);
		}
		return data;

	}

	public static boolean isNumericForDate(String inputData) {
		Scanner sc = new Scanner(inputData).useDelimiter("/");
		Scanner sc1 = new Scanner(inputData).useDelimiter("-");
		if (sc.hasNextInt() || sc1.hasNextInt())
			return true;
		else
			return false;
	}

	public static boolean isNumericForAmount(String inputData) {
		String[] value = inputData.split(Pattern.quote("."));
		Scanner sc1 = new Scanner(value[1]);
		if (sc1.hasNextInt())
			return true;
		else
			return false;
	}

	public static boolean isNumber(final String str) {
		try {
			Double.parseDouble(str);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	public static String formatPhone(String inputData) {
		if (inputData != null) {
			inputData = inputData.trim().replaceAll("\\s+", "");
			inputData = inputData.replace("+", "");
			inputData = inputData.replace(")", "");
			inputData = inputData.replace("(", "");
			inputData = inputData.replaceAll("-", "");
		}
		return inputData;
	}

	public static String formatPhoneNumber(String phoneNumber) {
		String fNum = null;
		if (!StringUtils.isBlank(phoneNumber)) {
			phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
		} else {
			return "No Phone Number Available.";
		}
		if (13 == phoneNumber.length()) {
			fNum = "+" + phoneNumber.substring(0, 3);
			fNum += " (" + phoneNumber.substring(3, 6) + ")";
			fNum += " " + phoneNumber.substring(6, 9);
			fNum += "-" + phoneNumber.substring(9, 13);
		} else if (11 == phoneNumber.length()) {
			fNum = "+" + phoneNumber.substring(0, 1);
			fNum += " (" + phoneNumber.substring(1, 4) + ")";
			fNum += " " + phoneNumber.substring(4, 7);
			fNum += "-" + phoneNumber.substring(7, 11);
		} else if (10 == phoneNumber.length()) {
			fNum = "(" + phoneNumber.substring(0, 3) + ")";
			fNum += " " + phoneNumber.substring(3, 6);
			fNum += "-" + phoneNumber.substring(6, 10);
		} else if (7 == phoneNumber.length()) {
			fNum = phoneNumber.substring(0, 3);
			fNum += "-" + phoneNumber.substring(3, 7);
		} else {
			return phoneNumber;
		}

		return fNum;
	}

	public static String hashEmail(String content) {
		String email = null;
		String regex = "(\\w+)(\\.\\w+)*@(\\w+\\.)(\\w+)(\\.\\w+)*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			email = matcher.group();

			if (!isValidEmailAddress(email)) {
				email = null;
			}

			break;
		}
		if (email != null)
			return content.replace(email, "******");
		else
			return content;
	}

	public static boolean isValidEmailAddress(String emailAddress) {
		String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = emailAddress;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}

	public static boolean isValidEmail(String emailAddress) {
		String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(emailAddress);

		return matcher.matches();
	}

	public static double formatDoubleValue(double value) {
		BigDecimal bd = new BigDecimal(value).setScale(2,
				RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}

	public static String customFormat(String pattern, long value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		return myFormatter.format(value);

	}

	public static String formatStringKey(String key) {
		if (key == null)
			return null;
		key = key.replace("'", "");
		key = key.replace(",", "");
		return key.replace(" ", "-");
	}

	public static boolean isValidPositiveWholeNumber(String str) {
		try {
			long num = Long.parseLong(str);
			return num > 0;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static int[] convert(String tokenstring){
		String[] tokens = tokenstring.split(",");
		int[] tokenValues = new int[tokens.length];
		for( int i=0;i<tokens.length;i++){
			tokenValues[i] = Integer.parseInt(tokens[i].trim());
		}
		return tokenValues;
	}
    
	public static Double[] convertTodoubleArray(String tokenstring){
		String[] tokens = tokenstring.split(",");
		Double[] tokenValues = new Double[tokens.length];
		for( int i=0;i<tokens.length;i++){
			tokenValues[i] = Double.parseDouble(tokens[i].trim());
		}
		return tokenValues;
	}
	
	// Generate unique referral Number
	public static final String generateReferralNumber() {
		return RandomStringUtils.randomAlphanumeric(10);
	}
	
	/**
	 * Replaces special chars (with ascii value >= 128) with URL encoded string
	 * @return null if there is any unexpected failure while URL encoding special chars
	 * @throws  
	 */
	public static String urlEncodeSpecialChars(final String input) {
		StringBuilder sb = new StringBuilder();
		try {
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) >= 128) {
					sb.append(URLEncoder.encode(""+input.charAt(i), "utf-8"));
				} else {
					sb.append(input.charAt(i));
				}
			}
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
		return sb.toString();
	}

	public static String htmlEscape(final String str) {
		return StringEscapeUtils.escapeHtml(str);
	}

	/**
	 * Converts list of elements into a comma separated string
	 * @param list
	 * @param elWrapper
	 * @return
	 */
	public static String listToCSV(List<String> list, String elWrapper) {
		final StringBuilder sb = new StringBuilder(1000);
		if ( list != null ) {
			for (String element : list) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				if (elWrapper != null) {
					sb.append(elWrapper).append(element).append(elWrapper);
				} else {
					sb.append(element);
				}
			}
		}
		return sb.toString();
	}
	public static String listToCSV(String elWrapper, String... elements) {
		return listToCSV(Arrays.asList(elements), elWrapper);
	}
	
	public static String escape(String str, char escapeChars) {
		final StringBuilder sb = new StringBuilder((int)(str.length() * 1.5));
		for (int i = 0; i < str.length(); i++) {
			final char currChar = str.charAt(i);
			if (currChar == escapeChars) {
				sb.append('\\').append(currChar);
			} else {
				sb.append(currChar);
			}
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {

		String xx = "chetan@gmail.com";
		System.out.println(generateReferralNumber());

	}

    public static String resolvePlace(String rawPlace) {
    	return rawPlace.replaceAll("\\-+", ",");
    }
}
