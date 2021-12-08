package bsuir.wt.lab4.service.validation;

import java.util.regex.Pattern;

public class CredentialValidator {

	public static boolean isCorrect(String email, String password) {
		return email != null && isEmailCorrect(email) && password != null && isPasswordCorrect(password);
	}

	private static boolean isEmailCorrect(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		return pattern.matcher(email).matches();
	}

	private static boolean isPasswordCorrect(String password) {
		return password.length() > 2 && password.length() <= 48;
	}

}
