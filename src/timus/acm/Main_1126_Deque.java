package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1126_Deque {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int m = readInt(reader);
		Deque<DequeElement> deque = new ArrayDeque<>();

		int n = readInt(reader);
		int currentIndex = 0;
		while (n != -1) {
			if (!deque.isEmpty()) {
				DequeElement peek = deque.peekLast();
				while (!deque.isEmpty() && peek.value <= n) {
					deque.removeLast();
					peek = deque.peekLast();
				}
			}

			deque.add(new DequeElement(n, currentIndex));
			currentIndex++;
			if (currentIndex > m) {
				int firstElementIndex = deque.peekFirst().index;
				if (firstElementIndex < currentIndex - m) {
					deque.removeFirst();
				}
			}
			if (currentIndex >= m) {
				System.out.println(deque.peekFirst().value);
			}
			n = readInt(reader);
		}
	}

	private static int readInt(BufferedReader reader) throws IOException {
		return Integer.parseInt(reader.readLine());
	}

	private static class DequeElement {
		int value;
		int index;

		public DequeElement(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

}
