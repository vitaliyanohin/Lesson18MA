package utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptPassword {

  private static final Logger LOGGER = Logger.getLogger(EncryptPassword.class);

  public static String encryptPassword(String passwordToHash, byte[] salt) {
    String generatedPassword = null;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
      messageDigest.update(salt);
      byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
      StringBuilder stringBuilder = new StringBuilder();
      for(int i=0; i< bytes.length ;i++) {
        stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.ERROR, "Failed to  encrypt password: ", e);
    }
    return generatedPassword;
  }

  public static byte[] getSalt() {
    SecureRandom secureRandom = null;
    try {
      secureRandom = SecureRandom.getInstance("SHA1PRNG");
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.ERROR, "Failed to  get random salt: ", e);
    }
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }
}
