package com.ecommerce.util;

import java.security.GeneralSecurityException;

import com.ecommerce.model.UserPassword;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.KeyTemplate;

public class PasswordUtil {
	public static UserPassword encryptUserPassword(String password, String emailId) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		AeadConfig.register();
		byte[] ciphertext = null;
		try {

			// Get the KeyTemplate [AES256_EAX]
			KeyTemplate keyTemplate = AeadKeyTemplates.AES256_EAX;

			// Generate KeySetHandle
			KeysetHandle keysetHandle = KeysetHandle.generateNew(keyTemplate);

			// Obtain the primitive Aead
			Aead aead = AeadFactory.getPrimitive(keysetHandle);
			
			ciphertext = aead.encrypt(password.getBytes(), emailId.getBytes());
            System.out.println("Cipher:" + ciphertext.toString());

		} catch (GeneralSecurityException e) {
			System.out.println(e);
			System.exit(1);
		}
		UserPassword userPassword = new UserPassword();
		//userPassword.setPassword(ciphertext);
		return userPassword;
	}

	public static String decryptUserPassword(byte[] password , String emailId) throws GeneralSecurityException {
		AeadConfig.register();
		String decryptedText = null;
		try
        {
			// Get the KeyTemplate [AES256_EAX]
	        KeyTemplate keyTemplate = AeadKeyTemplates.AES256_EAX;

	        // Generate KeySetHandle
	        KeysetHandle keysetHandle = KeysetHandle.generateNew(keyTemplate);

	        // Obtain the primitive Aead
	        Aead aead = AeadFactory.getPrimitive(keysetHandle);
			byte[] decrypted = aead.decrypt(password, emailId.getBytes());
	        decryptedText = new String(decrypted);
	        System.out.println("After Decryption: " + decryptedText);
        } catch (GeneralSecurityException e)
        {
            System.out.println(e);
        }
		return decryptedText;
	}
}
