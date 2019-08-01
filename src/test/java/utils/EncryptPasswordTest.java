package utils;

import org.junit.Assert;
import org.junit.Test;

public class EncryptPasswordTest {

  @Test
  public void EncryptPasswordTest() {
    byte[] salt = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10};
    String testData = EncryptPassword.encryptPassword("password", salt);
    String finalResult = "82af63547b34294f7151bd2480346d64887d8860ef9c7d086655a3f5095aed205" +
            "bb2145ce53145c31718ef5dd26c7484fda6b27c9ef3efeb430ea5fc761aac13";
    Assert.assertEquals(finalResult, testData);
  }
}
