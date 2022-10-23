public class LinkedListDeque<T> implements Deque<T> {
  public class node {
    private T item;
    private node pre;
    private node next;

    public node(T i, node p, node n) {
      item = i;
      pre = p;
      next = n;
    }
  } 

  private node sentinal;
  private int size;

  public LinkedListDeque() {
    sentinal = new node(null, null, null);
    sentinal.pre = sentinal;
    sentinal.next = sentinal;
    size = 0;
  }

  public void addFirst(T item) {
    node to_add = new node(item, sentinal,sentinal.next);
    sentinal.next.pre = to_add;
    sentinal.next = to_add;
    size += 1;
  }

  public void addLast(T item) {
    node to_add = new node(item,sentinal.pre,sentinal);
    sentinal.pre.next = to_add;
    sentinal.pre = to_add;
    size += 1;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  } 

  public void printDeque() {
    node p = sentinal.next;
    while(p != sentinal) {
      System.out.print(p.item + " ");
      p = p.next;
    }
  }

  public T removeFirst() {
    if (size == 0 ) {
      return null;
    }
    node to_remove = sentinal.next;
    T it = to_remove.item;
    to_remove.next.pre = sentinal;
    sentinal.next = to_remove.next;
    size -= 1;
    return it;
  }

  public T removeLast() {
    if (size == 0) {
      return null;
    }
    node to_remove = sentinal.pre;
    T it = to_remove.item;
    to_remove.pre.next = sentinal;
    sentinal.pre = to_remove.pre;
    size -= 1;
    return it;
  }

  public T get(int index) {
    if (index >= size) {
      return null;
    }
    node p = sentinal.next;
    int cnt = 0;
    while(cnt<index) {
      p = p.next;
      cnt++;
    }
    return p.item;
  }

  private T getRecursive_help(node cur, int index) {
    if (index == 0) {
      return cur.item;
    } else {
      return getRecursive_help(cur.next, index - 1);
    }
  }

  public T getRecursive(int index) {
    if(index >= size) {
      return null;
    }
    return getRecursive_help(sentinal.next, index);
  }

}
