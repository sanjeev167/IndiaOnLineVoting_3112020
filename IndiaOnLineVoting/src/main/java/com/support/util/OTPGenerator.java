/**
 * 
 */
package com.support.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.function.Supplier;

/**
 * @author Sanjeev
 *
 */
public class OTPGenerator {

	public static String genrateOTP1(final int lengthOfOTP) {

		StringBuilder generatedOTP = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();

		try {
			secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());

			for (int i = 0; i < lengthOfOTP; i++) {
				generatedOTP.append(secureRandom.nextInt(9));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return generatedOTP.toString();
	}

	public static Supplier<String> genrateOTP2(final int lengthOfOTP) {
		return () -> {
			StringBuilder otp = new StringBuilder();
			Random random = new Random();

			for (int i = 0; i < lengthOfOTP; i++) {

				// returns pseudo-random value between 0 and 9
				int randomNumber = random.nextInt(9);
				otp.append(randomNumber);
			}
			return otp.toString();
		};
	}

	public static String genrateOTP3(final int lengthOfOTP) {

		StringBuilder generatedOTP = new StringBuilder();
		SplittableRandom splittableRandom = new SplittableRandom();

		for (int i = 0; i < lengthOfOTP; i++) {

			int randomNumber = splittableRandom.nextInt(0, 9);
			generatedOTP.append(randomNumber);
		}
		return generatedOTP.toString();
	}

	/*public static void main(String[] args) {

		// SecureRandom class
		System.out.println("Radom number 1: " + genrateOTP1(6));

		// Supplier interface
		System.out.println("Radom number 2: " + genrateOTP2(4).get());

		// SplittableRandom class
		System.out.println("Radom number 3: " + genrateOTP3(8));

	}*/

}
