package com.kem.assessment;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SecureMessagingMechanism {

	private SecretKey symmetricKey;

	public SecureMessagingMechanism() {
		try {
			this.symmetricKey = generateSymmetricKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// Generate symmetric key
	private static SecretKey generateSymmetricKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256); // Using AES with 256-bit key size
		return keyGenerator.generateKey();
	}

	// Encrypt data using symmetric key
	public byte[] encryptData(String data) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, symmetricKey);
		return cipher.doFinal(data.getBytes());
	}

	// Decrypt data using symmetric key
	public String decryptData(byte[] encryptedData) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, symmetricKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedData);
		return new String(decryptedBytes);
	}

	public static void main(String[] args) {
		try {
			// Create an instance of SecureMessaging
			SecureMessagingMechanism secureMessaging = new SecureMessagingMechanism();

			// Original message
			String originalMessage = "This is a secret message!";

			// Encrypt the message
			byte[] encryptedData = secureMessaging.encryptData(originalMessage);
			System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedData));

			// Decrypt the message
			String decryptedMessage = secureMessaging.decryptData(encryptedData);
			System.out.println("Decrypted message: " + decryptedMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
