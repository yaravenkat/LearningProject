/**
 * Convert any number to readable words
 * @author Sunheng Taing
 *
 */
public class NumberToWordsUtility {
	private String[] ones = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private String[] tys = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	private String[] hundreds = {"", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion"};
	
	/**
	 * Convert number to word form
	 * @param num The int to convert into words
	 * @return The word form of the number 
	 */
	public String getWordFormat(int num) {
		if (num == 0) return ones[0];
		String numAsStr = num + "";
		while (numAsStr.length() % 3 != 0) {
			numAsStr = "0" + numAsStr; 
		}
		String[] fragments = new String[(int)Math.ceil(numAsStr.length() / 3.0)];
		int j = fragments.length - 1;
		for (int i = numAsStr.length(); i >= 3; i-=3, j--) {
			fragments[j] = numAsStr.substring(i - 3, i);
		}
		String result = "";
		j = fragments.length - 1;
		for ( int i = 0; i < fragments.length; i++, j--) {
			String threeDigitsWord = getThreeDigitsWord(Integer.parseInt(fragments[i]));
			if (threeDigitsWord.length() > 0) {
				result += threeDigitsWord + " " + hundreds[j] + " ";
			}
		}
		return result;
	}
	
	/**
	 * Convert 3 digit number to word form
	 * @param num Three digit number
	 * @return Word form of the 3 digit number
	 */
	public String getThreeDigitsWord(int num) {
		int temp = num / 100;
		String hundred = temp > 0 ? ones[temp] + " hundred ": "";
		temp = (num % 100) / 10;
		String ten;
		String one;
		if (temp == 0) ten = "";
		else if (temp > 1) ten = tys[temp] + " ";
		else {
			temp = num % 10;
			ten = teens[temp];
			return hundred + ten;
		}
		temp = num % 10;
		one = temp > 0 ? ones[temp] : "";
		return hundred + ten + one;
	}
	
	/**
	 * Examples
	 */
	public static void main(String[] args) {
		NumberToWordsUtility util = new NumberToWordsUtility();
		System.out.println(util.getWordFormat(403));
		System.out.println(util.getWordFormat(48805));
		System.out.println(util.getWordFormat(35653805));
		System.out.println(util.getWordFormat(488024655));
	}
}