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
**Employee count in each department**

SELECT
    d.department_id,
    d.dept_name,
    COUNT(e.emp_id) AS employee_count
FROM departments d
LEFT JOIN employees e
    ON d.department_id = e.dept_id
GROUP BY
    d.department_id,
    d.dept_name
ORDER BY
    d.department_id;
    
```
**Find the length of the longest substring without repeating characters**
```java
import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
    }
}
```
**Employees earning more than their manager**

SELECT 
    e.name AS employee_name,
    e.salary AS employee_salary,
    m.name AS manager_name,
    m.salary AS manager_salary
FROM Employee e
JOIN Employee m
    ON e.manager_id = m.id
WHERE e.salary > m.salary;
```
## Employees earning more than their manager

```sql
SELECT
    e.name   AS employee_name,
    e.salary AS employee_salary,
    m.name   AS manager_name,
    m.salary AS manager_salary
FROM Employee e
JOIN Employee m
    ON e.manager_id = m.id
WHERE e.salary > m.salary;
```
**Merge all overlapping intervals and return non-overlapping intervals**
```java
import java.util.*;

public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        int start = intervals[0][0];
        int end   = intervals[0][1];

        // Step 2: Merge overlapping intervals
        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end   = intervals[i][1];
            }
        }

        // Add last interval
        result.add(new int[]{start, end});

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] merged = merge(intervals);

        for (int[] i : merged) {
            System.out.println(Arrays.toString(i));
        }
    }
}
```
**Given a list of integers, return all unique pairs whose sum equals a target**
Input:  nums = [1, 2, 3, 4, 5, 6, 7]
Target: 8
Output: [[1,7], [2,6], [3,5]]

```java
import java.util.*;

public class PairSumEasy {

    public static List<List<Integer>> findPairs(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    result.add(Arrays.asList(nums[i], nums[j]));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int target = 8;

        System.out.println(findPairs(nums, target));
    }
}
```
