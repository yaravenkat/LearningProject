# My Learnings


1. You are given an input array and a target sum. Your task is to find two indices in the array whose corresponding values sum up to the target.

Input:

int[] array = {2, 7, 11, 15};
int target = 9;
Expected output:
[0, 1]


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



2. What are the challenges you faced while developing the application
3.UseMemo and useCallback difference in reactjs
4.Write a Java program that separates 0s on the left hand side and 1s on the right hand side from a random array of 0s and 1.

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



We have a input array as

String arr[] = { “abcd”, “java”, “dcba”, “ajav”, “xyz”, “epam”, “pame”, “aepm” };

Kindly write a java program for following output

Output:

[epam, pame, aepm] contains same character

[java, ajav] contains same character

[abcd, dcba] contains same character


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


5.Shift all even numbers to left side of array and odd number to right side java

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

