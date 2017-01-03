package other;

import java.util.Stack;

public class NextGreaters {

	public static int[] nextGreaters(int[] array) {
		int[] result = new int[array.length];
		Stack<StackElement> stack = new Stack<>();

		for (int i = 0; i < array.length; i++) {
			while (!stack.isEmpty() && array[i] > stack.peek().value) {
				StackElement pop = stack.pop();
				result[pop.position] = array[i];
			}

			stack.push(new StackElement(i, array[i]));
		}

		while (!stack.isEmpty()) {
			StackElement pop = stack.pop();
			result[pop.position] = -1;
		}

		return result;
	}

	private static class StackElement {
		int position;
		int value;

		public StackElement(int position, int value) {
			this.position = position;
			this.value = value;
		}
	}
}
