package com.openyich.framework.boot.id.factory;

import java.util.Random;

import com.openyich.framework.boot.id.service.RandomID;

/**
 * 随机生成字符串实现工厂
 */
public class RandomFactory implements RandomID {

  // 字符常量
  private static String NUMBERS = "1234567890";
  private static String LETTERS = "abcdefghijklmnopqrstuvwxyz";
  private static String LETTERS2 = LETTERS + LETTERS.toUpperCase();
  private static String ALL_CHARS = NUMBERS + LETTERS2;

  @Override
  public String uuid() {
    return java.util.UUID.randomUUID().toString();
  }

  @Override
  public String uuid(String name) {
    return java.util.UUID.nameUUIDFromBytes(name.getBytes()).toString();
  }

  @Override
  public String uuidUpperCase() {
    return uuid().toUpperCase();
  }

  @Override
  public String uuidUpperCase(String name) {
    return uuid(name).toUpperCase();
  }

  @Override
  public String guid() {
    return uuid().replaceAll("-", "");
  }

  @Override
  public String guid(String name) {
    return uuid(name).replaceAll("-", "");
  }

  @Override
  public String guidUpperCase() {
    return guid().toUpperCase();
  }

  @Override
  public String guidUpperCase(String name) {
    return guid(name).toUpperCase();
  }

  @Override
  public String random(int count) {
    return random(count, false);
  }

  @Override
  public String random(int count, boolean onlyNumbers) {
    if (onlyNumbers) {
      String numbers = RandomStringUtils.random(count, NUMBERS);
      if (numbers != null && numbers.startsWith("0")) {
        String startNo = RandomStringUtils.random(1, "123456789");
        numbers = numbers.replaceFirst("0", startNo);
      }
      return numbers;
    }
    return RandomStringUtils.random(count, ALL_CHARS);
  }

  @Override
  public String random(int count, char... chars) {
    return RandomStringUtils.random(count, chars);
  }

  @Override
  public String random(int count, String chars) {
    return RandomStringUtils.random(count, chars);
  }

  private static class RandomStringUtils {

    /**
     * <p>
     * Random object used by random method. This has to be not local to the random method so as to
     * not return the same value in the same millisecond.
     * </p>
     */
    private static Random RANDOM = new Random();

    /**
     * <p>
     * Creates a random string whose length is the number of characters specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of characters specified.
     * </p>
     *
     * @param count the length of random string to create
     * @param chars the character array containing the set of characters to use, may be null
     * @return the random string
     * @throws IllegalArgumentException if {@code count} &lt; 0.
     */
    public static String random(int count, char... chars) {
      if (chars == null) {
        return random(count, 0, 0, false, false, null, RANDOM);
      }
      return random(count, 0, chars.length, false, false, chars, RANDOM);
    }

    /**
     * <p>
     * Creates a random string based on a variety of options, using supplied source of randomness.
     * </p>
     *
     * <p>
     * If start and end are both {@code 0}, start and end are set to {@code ' '} and {@code 'z'},
     * the ASCII printable characters, will be used, unless letters and numbers are both
     * {@code false}, in which case, start and end are set to {@code 0} and
     * {@link Character#MAX_CODE_POINT}.
     *
     * <p>
     * If set is not {@code null}, characters between start and end are chosen.
     * </p>
     *
     * <p>
     * This method accepts a user-supplied {@link Random} instance to use as a source of randomness.
     * By seeding a single {@link Random} instance with a fixed seed and using it for each call, the
     * same random sequence of strings can be generated repeatedly and predictably.
     * </p>
     *
     * @param count the length of random string to create
     * @param start the position in set of chars to start at (inclusive)
     * @param end the position in set of chars to end before (exclusive)
     * @param letters only allow letters?
     * @param numbers only allow numbers?
     * @param chars the set of chars to choose randoms from, must not be empty. If {@code null},
     *        then it will use the set of all chars.
     * @param random a source of randomness.
     * @return the random string
     * @throws ArrayIndexOutOfBoundsException if there are not {@code (end - start) + 1} characters
     *         in the set array.
     * @throws IllegalArgumentException if {@code count} &lt; 0 or the provided chars array is
     *         empty.
     * @since 2.0
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers,
        char[] chars, Random random) {
      if (count == 0) {
        return "";
      } else if (count < 0) {
        throw new IllegalArgumentException(
            "Requested random string length " + count + " is less than 0.");
      }
      if (chars != null && chars.length == 0) {
        throw new IllegalArgumentException("The chars array must not be empty");
      }

      if (start == 0 && end == 0) {
        if (chars != null) {
          end = chars.length;
        } else {
          if (!letters && !numbers) {
            end = Character.MAX_CODE_POINT;
          } else {
            end = 'z' + 1;
            start = ' ';
          }
        }
      } else {
        if (end <= start) {
          throw new IllegalArgumentException(
              "Parameter end (" + end + ") must be greater than start (" + start + ")");
        }
      }

      int zeroDigitAscii = 48;
      int firstLetterAscii = 65;

      if (chars == null
          && (numbers && end <= zeroDigitAscii || letters && end <= firstLetterAscii)) {
        throw new IllegalArgumentException("Parameter end (" + end + ") must be greater then ("
            + zeroDigitAscii + ") for generating digits " + "or greater then ("
            + firstLetterAscii + ") for generating letters.");
      }

      StringBuilder builder = new StringBuilder(count);
      int gap = end - start;

      while (count-- != 0) {
        int codePoint;
        if (chars == null) {
          codePoint = random.nextInt(gap) + start;

          switch (Character.getType(codePoint)) {
            case Character.UNASSIGNED:
            case Character.PRIVATE_USE:
            case Character.SURROGATE:
              count++;
              continue;
          }

        } else {
          codePoint = chars[random.nextInt(gap) + start];
        }

        int numberOfChars = Character.charCount(codePoint);
        if (count == 0 && numberOfChars > 1) {
          count++;
          continue;
        }

        if (letters && Character.isLetter(codePoint) || numbers && Character.isDigit(codePoint)
            || !letters && !numbers) {
          builder.appendCodePoint(codePoint);

          if (numberOfChars == 2) {
            count--;
          }

        } else {
          count++;
        }
      }
      return builder.toString();
    }

    /**
     * <p>
     * Creates a random string whose length is the number of characters specified.
     * </p>
     *
     * <p>
     * Characters will be chosen from the set of characters specified by the string, must not be
     * empty. If null, the set of all characters is used.
     * </p>
     *
     * @param count the length of random string to create
     * @param chars the String containing the set of characters to use, may be null, but must not be
     *        empty
     * @return the random string
     * @throws IllegalArgumentException if {@code count} &lt; 0 or the string is empty.
     */
    public static String random(int count, String chars) {
      if (chars == null) {
        return random(count, 0, 0, false, false, null, RANDOM);
      }
      return random(count, chars.toCharArray());
    }
  }

}
