import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class fuzzbuzz {

	public static void main(String[] args) {

	//	public void getSumOfNumbers(String s) {
	        /*
	          Please implement this method to
	          calculate the sum of all integers found in the parameter String. You can assume that
	          integers are separated from other parts with one or more spaces (' ' symbol).
	          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
	         */
		
		//int[] ourElements = { 4, 8, 44, 7, 10, 34 };
		int [] ourElements = {43, 12, 12, 44, 47, 9, 34, 58, 3, 11, 4, 21};
		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MAX_VALUE;
 
		for (int value : ourElements) {
			if (value > max) {
				secondMax = max;
				max = value;
			} else if (value > secondMax && value < max) {
				secondMax = value;
			}
		}
		
		String[] array = new String[] {"test", "ABC", "why", "    ", "HLB", "webiste", "google", "1", "9", "-111"};
		Map<String, String> map = new HashMap<String,String>();
    	String[] keys = new String[array.length];
    	for (int i=0; i<array.length; i++){
    		String noSpace = array[i].replaceAll(" ", "");
    		map.put(noSpace, array[i]);
    		keys[i] = noSpace;
    	}
    	Arrays.sort(keys);
    	for(int k=0; k<keys.length; k++){
    		array[k] = map.get(keys[k]);
    		System.out.println("The sort array is : " + array[k]);
    	}
		
		
		System.out.println("The Large Element is : " + max);
		System.out.println("The Second Large Element is : " + secondMax);
 
		int sumofmaxnsecond = max + secondMax;
		System.out.println("The Sum Of Two Largest Element is : " + sumofmaxnsecond);
		
		
		
		List<String> list = Arrays.asList(array);
		list.stream().sorted(Collections.reverseOrder()) 
		    .forEach(System.out::println);
		
        String line = "Test 15 lqlq 35 bad 99999 guess 34";
        String s ="12 some text 3  7";
        int sum = Arrays.stream(s.split(" ")) // Convert to an array
            .filter((str) -> str.matches("\\d+"))  // Only select the numbers
            .mapToInt(Integer::parseInt)  // Convert to Integers
            .sum(); // Sum

        System.out.println(sum);



String[] phones = s.split("\\s+");

int sumofnum =0 ;
for (String string : phones) {
	
boolean numeric = true;

numeric = string.matches("-?\\d+(\\.\\d+)?");

if(numeric) {
			//sumofnum += i;
}
 

System.out.println("Sum of numbers equals to ");
		}         
	    	
	 //   }
	
	}
}
