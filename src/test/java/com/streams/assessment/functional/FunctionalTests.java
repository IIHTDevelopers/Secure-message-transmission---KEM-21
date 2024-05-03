package com.streams.assessment.functional;

import static com.streams.assessment.testutils.TestUtils.businessTestFile;
import static com.streams.assessment.testutils.TestUtils.currentTest;
import static com.streams.assessment.testutils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.Test;

import com.kem.assessment.SecureMessagingMechanism;

public class FunctionalTests {

	@Test
	void testEncryptDecrypt() {
		try {
			SecureMessagingMechanism secureMessaging = new SecureMessagingMechanism();
			String originalMessage = "This is a secret message!";
			byte[] encryptedData = secureMessaging.encryptData(originalMessage);
			String decryptedMessage = secureMessaging.decryptData(encryptedData);
			yakshaAssert(currentTest(), originalMessage.toString().equals(decryptedMessage) ? "true" : "false",
					businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}