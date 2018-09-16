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
    for (int i = 0; i < this.bag.length; i++) {
      copy[i] = this.bag[i];
    }
    this.bag = copy;
    this.size = capacity;
  }

  public boolean isEmpty() {
    return this.size() == 0;
  }

  public int size() {
    return position;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("null is not allowed");
    }
    if (this.position == this.bag.length) {
      this.resize(2 * this.bag.length);
    }

    this.bag[this.position] = item;
    position++;
  }

  public Item dequeue() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    if (this.position - 1 != 0) {
      int random = StdRandom.uniform(0, this.position - 1);

      Item deleted = this.bag[random];
      this.bag[random] = this.bag[position - 1];
      this.bag[position - 1] = null;
      position--;

      int desiredLength = this.bag.length;
      if (desiredLength / position > 3 && desiredLength > this.size) {
        desiredLength = this.bag.length / 2;
        this.resize(desiredLength);

      }

      return deleted;
    } else {
      Item deleted = this.bag[0];
      this.bag[0] = null;
      position--;

      return deleted;
    }
  }

  public Item sample() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }
    if (this.position - 1 != 0) {
      int random = StdRandom.uniform(0, this.position - 1);
      return this.bag[random];
    } else {
      return this.bag[0];
    }
  }

  public Iterator<Item> iterator() {
    StdRandom.shuffle(bag, 0, position);
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int curIndex = -1;
    private Item[] copy = Arrays.copyOf(RandomizedQueue.this.bag, RandomizedQueue.this.position);

    public boolean hasNext() {
      return curIndex < position - 1;
    }

    public Item next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      this.curIndex++;
      return copy[curIndex];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<>();
    rq.enqueue(12);
    rq.enqueue(11);
    rq.enqueue(10);
    rq.enqueue(9);
    rq.enqueue(8);
    rq.enqueue(7);
    rq.enqueue(6);
    rq.enqueue(5);
    rq.enqueue(4);
    rq.enqueue(3);
    rq.enqueue(2);
    rq.enqueue(1);
  }
}
