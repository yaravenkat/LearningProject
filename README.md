# My Learnings


1. You are given an input array and a target sum. Your task is to find two indices in the array whose corresponding values sum up to the target.

Input:

int[] array = {2, 7, 11, 15};
int target = 9;
Expected output:
[0, 1]
```java
public class TwoSum {

  public static void main(String[] args) {
    int[] array = {2, 7, 11, 15};
    int target = 9;

    // Call the method to find the indices
    int[] result = twoSum(array, target);

    // Output the result
    System.out.println(Arrays.toString(result)); // Expected output: [0, 1]
  }

  public static int[] twoSum(int[] array, int target) {
    // Create a HashMap to store the number and its index
    Map<Integer, Integer> map = new HashMap<>();

    // Iterate through the array
    for (int i = 0; i < array.length; i++) {
      int complement = target - array[i];

      // Check if the complement exists in the map
      if (map.containsKey(complement)) {
        return new int[] { map.get(complement), i }; // Return the indices
      }

      // Store the current number and its index in the map
      map.put(array[i], i);
    }

    // If no solution is found, return an empty array (or throw an exception)
    return new int[] {};
  }
``` 

2. What are the challenges you faced while developing the application
3.UseMemo and useCallback difference in reactjs
4.Write a Java program that separates 0s on the left hand side and 1s on the right hand side from a random array of 0s and 1.
```java
// Import the necessary Java utility class for working with arrays.
import java.util.Arrays;

// Define the Main class.
public class Main {
    public static void main(String[] args)
    {
        // Create an array of integers containing 0s and 1s.
        int arr[] = new int[]{ 0, 0, 1, 1, 0, 1, 1, 1, 0 };
        int result[];
        
        // Print the original array.
        System.out.println("Original Array ");
        System.out.println(Arrays.toString(arr));

        // Get the length of the array.
        int n = arr.length;
 
        // Call the separate_0_1 method to separate 0s and 1s.
        result = separate_0_1(arr, n);
        
        // Print the rearranged array.
        System.out.println("New Array ");
        System.out.println(Arrays.toString(result));
    }
    
    // A method to separate 0s and 1s in an array.
    static int[] separate_0_1(int arr[], int n)
     {
        // Initialize a count to track the number of 0s.
        int count = 0;   
     
        // Count the number of 0s in the array.
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                count++;
        }
 
        // Set the first 'count' elements to 0.
        for (int i = 0; i < count; i++)
            arr[i] = 0;
 
        // Set the remaining elements to 1.
        for (int i = count; i < n; i++)
            arr[i] = 1;
    
        // Return the modified array.
        return arr;
     }       
}
``` 


We have a input array as

String arr[] = { “abcd”, “java”, “dcba”, “ajav”, “xyz”, “epam”, “pame”, “aepm” };

Kindly write a java program for following output

Output:

[epam, pame, aepm] contains same character

[java, ajav] contains same character

[abcd, dcba] contains same character
```java
import java.util.*;

public class GroupStringsBySameCharacters {
    public static void main(String[] args) {
        String[] arr = { "abcd", "java", "dcba", "ajav", "xyz", "epam", "pame", "aepm" };
        
        // Map to store the grouped anagrams
        Map<String, List<String>> map = new HashMap<>();

        // Loop through each string in the input array
        for (String str : arr) {
            // Convert the string to a character array, sort it, and then back to a string
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            // If the sorted string is already in the map, add the original string to the list
            // Otherwise, create a new list and add the string
            map.putIfAbsent(sortedStr, new ArrayList<>());
            map.get(sortedStr).add(str);
        }

        // Iterate over the map and print out the grouped strings
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> group = entry.getValue();
            if (group.size() > 1) { // Only print groups that contain more than one string
                System.out.println(group + " contains same character");
            }
        }
    }
}
``` 

5.Shift all even numbers to left side of array and odd number to right side java
```java
public class EvenOddArray {
    public static void main(String[] args) {
        int[] arr = {12, 34, 45, 9, 8, 90, 3};
        rearrangeEvenOdd(arr);

        // Print the modified array
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void rearrangeEvenOdd(int[] arr) {
        int left = 0;            // Pointer to the left side of the array
        int right = arr.length - 1;  // Pointer to the right side of the array

        while (left < right) {
            // If the number at left is even, move left pointer to the right
            if (arr[left] % 2 == 0) {
                left++;
            } 
            // If the number at right is odd, move right pointer to the left
            else if (arr[right] % 2 != 0) {
                right--;
            } 
            // If left is odd and right is even, swap them
            else {
                // Swap the numbers at left and right
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }
}
``` 

5 .Here's the Java program that checks if a given string has balanced brackets:
```java
import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        System.out.println(isBalanced("{[()]}")); // true
        System.out.println(isBalanced("{[(])}")); // false
        System.out.println(isBalanced("{{[[(())]]}}")); // true
        System.out.println(isBalanced("{[}")); // false
    }

    public static boolean isBalanced(String str) {
        // Stack to hold opening brackets
        Stack<Character> stack = new Stack<>();

        // Traverse through the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // If it's an opening bracket, push it onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // If it's a closing bracket
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If the stack is empty or the top of the stack doesn't match the corresponding opening bracket
                if (stack.isEmpty()) {
                    return false;
                }

                // Pop from stack and check if it matches the corresponding opening bracket
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If the stack is empty, all brackets matched; otherwise, they didn't
        return stack.isEmpty();
    }
}
``` 

6. what is the output for the below
```java
public void test(){
    try{
        System.out.println("1");
        System.out.println("2");
        return;
    }catch(Exception ex){
        System.out.println("3");
    }
    finally{
        System.out.println("4");
    }
}
``` 
output : 1,2,4

7. what is the output for the below
```java
public class Employee {
    private String firstName;

    public Employee(String firstName){
        super();
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private static void method1(Employee e){
        e.setFirstName("ghi");
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("abc");
        Employee e2 = e1; // e2 now references the same object as e1
        e2.setFirstName("def"); // e1's firstName is now "def" because e2 is pointing to the same object as e1
        System.out.println(e1.getFirstName()); // prints "def"

        method1(e2); // This changes e1's firstName to "ghi" via e2, because e2 references e1
        System.out.println(e1.getFirstName()); // prints "ghi"

        Employee e3 = new Employee("ghi");  // e3 is a new object with the firstName "ghi"
        Set<Employee> empSet = new HashSet<>();
        empSet.add(e1);  // Adds e1 to the set
        empSet.add(e2);  // e2 is the same as e1, so e1 and e2 are treated as the same object
        empSet.add(e3);  // Adds e3 to the set

        System.out.println(empSet.size());  // Size of the set
    }
}
``` 
Output is like below
def
ghi
2


8.Given an integer array "nums", find the largest sum, from a arrya/subarray and return its sum.
```java
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        int result = maxSubArray(nums);
        System.out.println("Largest sum: " + result);  // Output: 6
    }

    public static int maxSubArray(int[] nums) {
        // Initialize currentSum and maxSum with the first element
        int currentSum = nums[0];
        int maxSum = nums[0];
        
        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Update currentSum to be either the current element itself
            // or currentSum + current element (whichever is greater)
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Update maxSum to be the maximum of itself or the new currentSum
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}
``` 


9. to get the employes count who joined last 6 months
   ```java
   long count = Stream.concat(list1.stream(), list2.stream()) // Concatenate the streams
            .filter(e -> e.getJoiningDate().isAfter(LocalDate.now().minusMonths(6))) // Filter based on date
            .count(); // Count the results
        
        System.out.println("Count of employees joined in the last 6 months: " + count);
    }	
```
Determine the positions of the digit '2' in the number provided:
int num = 627248642;
Expected output: 1, 3, 8
```java
import java.util.stream.IntStream;
public class Indexfind {
    public static void main(String[] args) {
        int num = 627248642;
     //   Expected output: 1, 3, 8
        int target=2;
       int index=0;
       int temp = num;
       while (temp > 0){
           int currentDigit= temp % 10;
          // System.out.println(currentDigit);
           if(currentDigit == target){
               System.out.println("Digit 2 index" +index);
           }
           temp /= 10;
           index++;
       }
    }
}
```

Find 2nd non-repeated char from input string, using Java8 stream
String input = "Java is my programming language"
Output = v
```java
import java.util.*;
import java.util.stream.Collectors;

public class SecondNonRepeat {
    public static void main(String[] args) {
        String input = "Java is my programming language";

        // Single statement to get the second non-repeated character while preserving the order
        input.toLowerCase().chars()
                .mapToObj(c -> (char) c)  // Convert int to Character
                .filter(Character::isLetter)  // Filter only alphabetic characters
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))  // Preserve order with LinkedHashMap
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)  // Filter for non-repeated characters
                .map(Map.Entry::getKey)  // Get the characters
                .skip(1)  // Skip the first non-repeated character
                .findFirst()  // Get the second non-repeated character (if any)
                .ifPresent(System.out::println);  // Prints 'v' if found
    }
}
```
