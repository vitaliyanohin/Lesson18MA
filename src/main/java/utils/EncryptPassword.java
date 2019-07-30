package utils;

import dao.impl.OrderDaoImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class EncryptPassword {

  private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

  public static Optional<Object> encryptPassword(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger no = new BigInteger(1, messageDigest);
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return Optional.of(hashtext);
    } catch (NoSuchAlgorithmException e) {
       LOGGER.log(Level.ERROR, "Failed to  encrypt password: ", e);
    }
    return Optional.empty();
  }
}
