/*
 * PermMissingElem
 * An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

Your goal is to find that missing element.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A, returns the value of the missing element.

For example, given array A such that:

  A[0] = 2
  A[1] = 3
  A[2] = 1
  A[3] = 5
the function should return 4, as it is the missing element.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
the elements of A are all distinct;
each element of array A is an integer within the range [1..(N + 1)].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited. */

class MissingArraySolution {
 public int solution(int[] A) {
     // write your code in Java SE 8
   if (A.length == 0)
     return 1;

 int sumOfAllNumbers = 0;
 for (int num : A)
     sumOfAllNumbers += num;

 long N = A.length;
 long expectedSumOfAllNumbers = ((N + 1)*(N + 2))/2;

 long missingNumber = expectedSumOfAllNumbers - sumOfAllNumbers;

 return (int) missingNumber;
}
 
 public static void main(String[] args) {
	 int[] array = { 2, 3, 1, 5 };
	 MissingArraySolution missingArraySolution = new MissingArraySolution();
	 int missingnum = missingArraySolution.solution(array);
	 System.out.println("missingnum" + missingnum);
}
}
