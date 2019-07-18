package utils;

import java.util.Random;

public class ConfirmCode {

  private static final int CHAR_START = 65;
  private static final int CHAR_END = 90;

  public static String code() {
    StringBuilder stringBuilder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 4; i++) {
      int randomChar = random.ints(CHAR_START,(CHAR_END + 1)).findFirst().getAsInt();
      stringBuilder.append((char) randomChar);
    }
    return stringBuilder.toString();
  }
}
