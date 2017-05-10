package es.uniovi.asw.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Checks that the password is correct.
	 * 
	 * @param password, passwordU
	 */
	public static boolean checkPassword(String password, String passwordU) {
		return password.equals(hashPassword(passwordU));
	}
	
	public static String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
