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
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(salt);
      byte[] bytes = md.digest(passwordToHash.getBytes());
      StringBuilder sb = new StringBuilder();
      for(int i=0; i< bytes.length ;i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.ERROR, "Failed to  encrypt password: ", e);
    }
    return generatedPassword;
  }

  public static byte[] getSalt() {
    SecureRandom sr = null;
    try {
      sr = SecureRandom.getInstance("SHA1PRNG");
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.ERROR, "Failed to  get random salt: ", e);
    }
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
  }
}
