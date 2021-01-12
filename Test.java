import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;

public class Test {
	public static void getSumOfNumbers(String s) {
		/*
		 * Please implement this method to calculate the sum of all integers found in
		 * the parameter String. You can assume that integers are separated from other
		 * parts with one or more spaces (' ' symbol). For example,
		 * s="12 some text 3  7", result: 22 (12+3+7=22)
		 */
		int sum = Arrays.stream(s.split(" ")) // Convert to an array
				.filter((str) -> str.matches("\\d+")) // Only select the numbers
				.mapToInt(Integer::parseInt) // Convert to Integers
				.sum(); // Sum

		System.out.println("Sum of numbers equals to " + sum);
	}

	public static void sortIgnoringSpaces(String[] array) {
		/*
		 * Please implement this method to sort a given array of Strings in alphabetical
		 * order ignoring spaces (' ' symbols) within the strings.
		 */

		System.out.println("Answer for sort ignoring space:");
		Map<String, String> map = new HashMap<String, String>();
		String[] keys = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String noSpace = array[i].replaceAll(" ", "");
			map.put(noSpace, array[i]);
			keys[i] = noSpace;
		}
		Arrays.sort(keys);
		for (int k = 0; k < keys.length; k++) {
			array[k] = map.get(keys[k]);
			System.out.println(array[k]);
		}
	}

	public static void reverseArray(String[] array) {
		/*
		 * Please implement this method to reverse array where the order of elements has
		 * been reversed from the original array. E.g. given {"a", "b", "c", "d"},
		 * result is {"d", "c", "b", "a"}
		 */

		System.out.println("Answer for reverse array:");
		List<String> list = Arrays.asList(array);
		list.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);

	}

	public static void sumOfTwoLargestNumbers(int[] array) {
		/*
		 * Please implement this method to calculate the sum of the two largest numbers
		 * in a given array.
		 */
		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MAX_VALUE;

		for (int value : array) {
			if (value > max) {
				secondMax = max;
				max = value;
			} else if (value > secondMax && value < max) {
				secondMax = value;
			}
		}

		int sumofmaxnsecond = max + secondMax;
		System.out.println("Sum of the two largest numbers is " + sumofmaxnsecond);
	}

	// Please do not change this helper class
	public static class Node {
		int val;
		List<Node> children;

		int getValue() {
			return val;
		}

		void setValue(int val) {
			this.val = val;
		}

		List<Node> getChildren() {
			return children;
		}

		void setChildren(List<Node> children) {
			this.children = children;
		}
	}

	public static void getAverage(Node root) {
		/*
		 * Please implement this method to calculate the average of all node values
		 * (Node.getValue()) in the tree. root c1 c2 c3 c4 c5 c6
		 * 
		 * The codes must able to support any tree structures even the orphan root which
		 * doesn't have children. You can create any helper function as needed.
		 */

		int[] treeSum = getTreeSum(root);
		double average = (double) treeSum[0] / treeSum[1];

		System.out.println("Average of the node values equals to " + average);
	}

	// Return a pair of numbers
	// -first one is sum of all nodes in tree
	// -second one is number of nodes in the treeO
	public static int[] getTreeSum(Node root) {
		int[] res = new int[2];
		if (root == null) {
			res[0] = 0;
			res[1] = 0;
		} else {
			res[0] = root.getValue();
			res[1] = 1;
			if (root.getChildren() != null) {
				for (Node child : root.getChildren()) {
					int[] childRes = getTreeSum(child);
					res[0] += childRes[0];
					res[1] += childRes[1];
				}
			}
		}
		return (res);
	}

	public static void main(String args[]) {
		try {
			// sum
			getSumOfNumbers("text   mix with 112 and 222    with numbers 2 278 991");
			System.out.println("////////////////////////");
			System.out.println();

			// sort
			sortIgnoringSpaces(
					new String[] { " ", "test", "ABC", "why", "    ", "HLB", "webiste", "google", "1", "9", "-111" });
			System.out.println("////////////////////////");
			System.out.println();

			// reverse
			reverseArray(new String[] { "first", "second", "third", "fourth", "fifth", "sixth", "seventh" });
			System.out.println("////////////////////////");
			System.out.println();

			// sum two largest
			int[] array = { 43, 12, 12, 44, 47, 9, 34, 58, 3, 11, 4, 21 };
			sumOfTwoLargestNumbers(array);
			System.out.println("////////////////////////");
			System.out.println();

			// average
			Node root = new Node();
			root.setValue(RandomUtils.nextInt(100));
			Node c1 = new Node();
			c1.setValue(RandomUtils.nextInt(100));
			Node c2 = new Node();
			c2.setValue(RandomUtils.nextInt(100));
			List<Node> list = new ArrayList<>();
			list.add(c1);
			list.add(c2);
			root.setChildren(list);

			Node c3 = new Node();
			c3.setValue(RandomUtils.nextInt(100));
			list = new ArrayList<>();
			list.add(c3);

			c2.setChildren(list);

			Node c4 = new Node();
			c4.setValue(RandomUtils.nextInt(100));
			Node c5 = new Node();
			c5.setValue(RandomUtils.nextInt(100));
			Node c6 = new Node();
			c6.setValue(RandomUtils.nextInt(100));
			list = new ArrayList<>();
			list.add(c4);
			list.add(c5);
			list.add(c6);
			c3.setChildren(list);

			getAverage(root);

			int total = root.getValue() + c1.getValue() + c2.getValue() + c3.getValue() + c4.getValue() + c5.getValue()
					+ c6.getValue();
			double ans = (double) total / 7;
			System.out.println("Correct answer: " + total + " / 7 = " + ans);
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}

}

class NaryNode {
	int value;
	NaryNode parent;
	List<NaryNode> children = new ArrayList<NaryNode>();

	NaryNode(int x) {
		this.value = x;
	}
}
