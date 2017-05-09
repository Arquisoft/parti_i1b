package es.uniovi.asw.password;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.uniovi.asw.model.Citizen;

public class PasswordGenerator {

	private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom random = new SecureRandom();
	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Generates a random password with alphanumeric characters.
	 * 
	 * @return String
	 */
	private static String randomPassword(int sizePassword) {
		StringBuilder sb = new StringBuilder(sizePassword);
		for (int i = 0; i < sizePassword; i++) {
			sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
		}
		return sb.toString();
	}

	/**
	 * Sets the password for the citizen object.
	 * 
	 * @param citizen
	 */
	public static void generatePasswords(Citizen citizen) {
		// Generate the random password.
		String password = randomPassword(10);
		// Makes a bcrypt hash of the password.
		String hashedPassword = passwordEncoder.encode(password);
		// Sets the password in the citizen object.
		citizen.setPassword(hashedPassword);
		// The unhashed password is needed temporarily for the letter generation
		citizen.setUnhashedPassword(password);
	}

}
