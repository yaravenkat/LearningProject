**Remove Duplicates from Sorted Array**
[1, 1, 2, 2, 3]

```java
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int index = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3};
        int length = removeDuplicates(nums);

        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
``` 
**Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number**. Example 1: Input: nums = [3,2,1] **Output: 1** Explanation: The first distinct maximum is 3. The second distinct maximum is 2. The third distinct maximum is 1.

```java
class Solution {
    public int thirdMax(int[] nums) {
        Long first = null;
        Long second = null;
        Long third = null;

        for (int num : nums) {
            long n = num;

            // Skip duplicates
            if (n == first || n == second || n == third) {
                continue;
            }

            if (first == null || n > first) {
                third = second;
                second = first;
                first = n;
            } else if (second == null || n > second) {
                third = second;
                second = n;
            } else if (third == null || n > third) {
                third = n;
            }
        }

        return third == null ? first.intValue() : third.intValue();
    }
}
``` 
**Here is the Java 8 Streams solution for Third Distinct Maximum Number, written cleanly and suitable for interviews**

```java
class Solution {
    public int thirdMax(int[] nums) {
        return Arrays.stream(nums)
                .distinct()
                .boxed()
                .sorted((a, b) -> b.compareTo(a))
                .skip(2)
                .findFirst()
                .orElseGet(() -> Arrays.stream(nums).max().getAsInt());
    }
}
```
