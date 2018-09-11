import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node head;
  private Node tail;
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
    this.head = new Node();
    this.tail = new Node();
    this.head.next = this.tail;
    this.tail.prev = this.head;
    this.size = 0;
  }                           // construct an empty deque

  public boolean isEmpty() {
    return this.head.item == this.tail;
  }

  public int size() {
    return this.size;
  }                // return the number of items on the deque

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Can not add null element");
    }
    Node oldFirst = this.head.next;
    Node newItemNode = new Node(item);

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

    Node oldLast = this.tail.prev;
    Node newItemNode = new Node(item);

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

    Node first = this.head.next;
    Node second = first.next;

    this.head.next = second;
    second.prev = this.head;
    this.size--;

    return (Item) first.item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (this.isEmpty()) {
      throw new NoSuchElementException();
    }

    Node last = this.tail.prev;
    Node previous = last.prev;

    this.tail.prev = previous;
    previous.next = this.tail;
    this.size--;

    return (Item) last.item;
  }


  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = head;

    public boolean hasNext() {
      return current.next != tail;
    }

    public Item next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      }
      Item item = (Item) current.next.item;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    Deque<String> dblist = new Deque<String>();
    dblist.addFirst(new String("item1"));
    dblist.addLast(new String("item2"));
    dblist.removeLast();
    dblist.removeFirst();
    dblist.addLast(new String("item3"));
    Iterator it = dblist.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
