import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException();
    }

    int quantity = Integer.parseInt(args[0]);
    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

    for (int i = 0; i < quantity; i++) {
      randomizedQueue.enqueue(StdIn.readString());
    }

    Iterator<String> iterator = randomizedQueue.iterator();
    while (iterator.hasNext()) {
      StdOut.println(iterator.next());
    }
  }
}
