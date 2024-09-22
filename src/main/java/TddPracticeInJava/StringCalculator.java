package TddPracticeInJava;

public class StringCalculator {

  private static int sum;


  public static int add(String consoleInput) throws Exception {
    sum = 0;


    if (consoleInput == null || consoleInput.equals("")) {
      return sum;
    }
    else {
      return handleAddforValidInput(consoleInput);
    }
  }


  /**
   * @param consoleInput
   * @throws Exception 
   * @throws NumberFormatException
   */
  private static int handleAddforValidInput(String consoleInput) throws  Exception {
    return checkLengthEqualsOne(consoleInput) ? Integer.parseInt(consoleInput) : handleAddProcess(consoleInput);
  }


  /**
   * @param consoleInput
   * @return
   * @throws Exception 
   */
  private static int handleAddProcess(String consoleInput) throws Exception {
    StringBuilder negativeNumber = new StringBuilder();
    StringBuilder onlyPositiveNumber = new StringBuilder();
    extractAndAddOnlyNumber(negativeNumber, onlyPositiveNumber, consoleInput);
    return sum;
  }


  /**
   * @param negativeNumber
   * @param onlyNumber
   * @param consoleInput
   * @throws Exception 
   */
  private static void extractAndAddOnlyNumber(StringBuilder negativeNumber, StringBuilder onlyPositiveNumber,
      String consoleInput) throws Exception {
    Character prevChar = ' ';
    onlyPositiveNumber.append("0");

    for (Character inputChar : consoleInput.toCharArray()) {
      if (Character.isDigit(inputChar) && !isNegativeSign(prevChar)) {
        appendPositiveNumbers(inputChar, prevChar, onlyPositiveNumber);
      }
      else if (isNegativeSign(prevChar)) {
        negativeNumber.append(prevChar + inputChar + ',');
      }
      prevChar = inputChar;
    }
    calculateSum(onlyPositiveNumber);
    throwExceptionforNegativeNumbers(negativeNumber);
  }

  /**
   * @param onlyPositiveNumber
   */
  private static void calculateSum(StringBuilder onlyPositiveNumber) {
    String[] positiveNumbers = onlyPositiveNumber.toString().split(",");
    for (String positiveNumber : positiveNumbers) {
      sum = sum + processIfGreaterThanThousand(Integer.parseInt(positiveNumber));
    }

  }


  /**
   * @param inputChar
   * @param prevChar
   * @param onlyPositiveNumber
   */
  private static void appendPositiveNumbers(Character inputChar, Character prevChar, StringBuilder onlyPositiveNumber) {

    if (Character.isDigit(prevChar)) {
      onlyPositiveNumber.append(inputChar);
    }
    else {
      onlyPositiveNumber.append("," + inputChar);
    }

  }


  /**
   * @param negativeNumber
   * @throws Exception 
   */
  private static void throwExceptionforNegativeNumbers(StringBuilder negativeNumber) throws Exception {
    if (negativeNumber.length() > 0) {
      throw new Exception("Given input has negative numbers " + negativeNumber.toString());
    }

  }


  public static boolean isNegativeSign(Character inputCharacter) {
    return inputCharacter == '-';
  }

  /**
   * @param i
   * @return
   */
  private static int processIfGreaterThanThousand(int inputNumber) {
    return inputNumber >= 1000 ? 0 : inputNumber;
  }


  /**
   * @param consoleInput
   * @return
   */
  private static boolean checkLengthEqualsOne(String consoleInput) {
    return consoleInput.length() == 1;
  }

}

