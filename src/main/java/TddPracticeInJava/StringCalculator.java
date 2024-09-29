package TddPracticeInJava;

public class StringCalculator {

  public static List<Integer> negativeNumList ;
  public static int sum ;


  /**
   * @param inputString - input string to calculate sum
   * @return sum
   * @throws Exception 
   */
  public static int add(String inputString) throws Exception {
    sum = 0; 
    negativeNumList = new ArrayList();
    
    if (inputString.equals("")) {
      return sum;
    }
    handleStringwithDelimiter(inputString);
    if(!negativeNumList.isEmpty()) {
      throw new Exception("The negative numbers are " + negativeNumList.toString());
    }
    return sum;
  }

  /**
   * @param inputString
   */
  private static void handleStringwithDelimiter(String inputString) {
    Matcher matchString = Pattern.compile("//(.*)\n(.*)").matcher(inputString);
    
    if (matchString.find()) {
      String stringWithNumber = inputString.substring(inputString.indexOf("\n") + 1);
      String[] numberArray = getNumberArray("\\"+ matchString.group(1), stringWithNumber);
      calculateSum(numberArray);
    }
    else {
      handleStringwithoutDelimeterSpecified(inputString);
    }

  }

  /**
   * @param inputString
   */
  private static void handleStringwithoutDelimeterSpecified(String inputString) {
    String defaultDelimiter = ",|\n";
    String[] numberArray = getNumberArray(defaultDelimiter, inputString);
    calculateSum(numberArray);
  }

  /**
   * @param numberArray
   */
  private static void calculateSum(String[] numberArray) {
    for (String number : numberArray) {     
      int num = Integer.parseInt(number);
      checkForValidNumbers(num);
    }
  }

  /**
   * @param num
   */
  private static void checkForValidNumbers(int num) {
    if (!checkandStoreNegativenumbers(num) && (num < 1000)) {
      sum = sum + num;
    }
  }

  /**
   * @param num
   * @return
   */
  private static boolean checkandStoreNegativenumbers(int num) {
    if (num < 0) {
      negativeNumList.add(num);
      return true;
    }
    return false;
  }

  /**
   * @param defaultDelimiter
   * @param inputString
   * @return
   */
  private static String[] getNumberArray(String defaultDelimiter, String inputString) {
    return inputString.split( defaultDelimiter + "+" );   
  }



}
