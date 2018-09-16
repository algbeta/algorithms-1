import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private final Node<Item> head;
  private final Node<Item> tail;
  private int size;

  private class Node<Item> {
    Item item;
    Node<Item> next;
    Node<Item> prev;

    Node(Item item) {
      this.item = item;
      this.next = null;
      this.prev = null;
    }

    Node() {
      this.item = null;
      this.next = null;
      this.prev = null;
    }
  }

  public Deque() {
    this.head = new Node<>();
    this.tail = new Node<>();
    this.head.next = this.tail;
    this.tail.prev = this.head;
    this.size = 0;
  }                           // construct an empty deque

  public boolean isEmpty() {
    return this.head.next == this.tail;
  }

  public int size() {
    return this.size;
  }                // return the number of items on the deque

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Can not add null element");
    }
    Node<Item> oldFirst = this.head.next;
    Node<Item> newItemNode = new Node<>(item);

    newItemNode.next = oldFirst;
    newItemNode.prev = this.head;
    oldFirst.prev = newItemNode;
    this.head.next = newItemNode;

    this.size++;
  }

  // add the item to the end
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Can not add null element");
    }

    Node<Item> oldLast = this.tail.prev;
    Node<Item> newItemNode = new Node<>(item);

    oldLast.next = newItemNode;
    newItemNode.prev = oldLast;
    newItemNode.next = this.tail;
    this.tail.prev = newItemNode;
    this.size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }

    Node<Item> first = this.head.next;
    Node<Item> second = first.next;

    this.head.next = second;
    second.prev = this.head;
    this.size--;

    return first.item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }

    Node<Item> last = this.tail.prev;
    Node<Item> previous = last.prev;

    this.tail.prev = previous;
    previous.next = this.tail;
    this.size--;

    return last.item;
  }


  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node<Item> current = head;

    public boolean hasNext() {
      return current.next != tail;
    }

    public Item next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = current.next.item;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    Deque<String> dblist = new Deque<>();
    dblist.addFirst("item1");
    dblist.addFirst("item2");
    dblist.removeFirst();
    dblist.removeFirst();
    System.out.println(dblist.isEmpty());
  }
}
