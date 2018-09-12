import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] bag;
  private int size;
  private int position = 0;

  public RandomizedQueue() {
    this.size = 10;
    this.bag = (Item[]) new Object[this.size];
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < this.size; i++) {
      copy[i] = this.bag[i];
    }
    this.bag = copy;
    this.size = capacity;
  }

  public Item[] getBag() {
    return this.bag;
  }

  public boolean isEmpty() {
    return this.size() > 0;
  }

  public int size() {
    return position;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null is not allowed");
    }
    if (this.position == this.size) {
      this.resize(2 * this.bag.length);
      this.bag[this.size] = item;
      position++;
    }
  }

  public Item dequeue() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    int random = StdRandom.uniform(0, this.position - 1);
    int desiredLength = this.bag.length;

    if (desiredLength / position > 3) {
      desiredLength = this.bag.length / 2;
    }

    //this.resize(desiredLength);

    Item[] copy = (Item[]) new Object[desiredLength];
    for (int i = 0; i < this.position - 1; i++) {
      if (i < random) {
        copy[i] = this.bag[i];
      } else {
        copy[i] = this.bag[i + 1];
      }
    }

    Item deleted = this.bag[random];
    this.bag = copy;
    return deleted;
  }

  public Item sample() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    int random = StdRandom.uniform(0, this.position - 1);
    return this.bag[random];
  }

  public Iterator<Item> iterator() {
    StdRandom.shuffle(bag);
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int curIndex = 0;

    public boolean hasNext() {
      return curIndex < position;
    }

    public Item next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      this.curIndex++;
      return RandomizedQueue.this.bag[curIndex];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    queue.enqueue("element 1");
    queue.enqueue("element 2");

    System.out.println(Arrays.toString(queue.getBag()));
  } // unit testing (optional)
}
