public class ArrayDeque<T> {
  private T[] array;
  //总长
  private int length;
  //已使用
  private int size;
  //下一个first和下一个last
  private int nextfirst;
  private int nextlast;

  public ArrayDeque() {
    array = (T[]) new Object[8];
    nextfirst = 7;
    nextlast = 0;
    length = 8;
    size = 0;
  }

  private void resizeArray(int capacity) {
    T[] new_array = (T[]) new Object[capacity];
    for (int i = 1; i <= size; i++) {
      new_array[i] =  array[(++nextfirst)%length];
    }
    nextfirst = 0;
    nextlast = size + 1;
    length = capacity;
    array = new_array;
  }

  public void addFirst(T item) {
    if (size == length) {
      resizeArray(length * 2);
    }
    array[nextfirst] = item;
    size++;
    if (nextfirst == 0) {
      nextfirst = length - 1;
    }
    else {
      nextfirst -= 1;
    }
  }

  public void addLast(T item) {
    if (size == length) {
      resizeArray(length * 2);
    }
    array[nextlast] = item;
    size++;
    nextlast = (nextlast + 1) % length;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void printDeque() {
    int beg = (nextfirst + 1) % length;
    int end = nextlast - 1;
    for (int i = beg; i != end; i = (i + 1) % length) {
      System.out.print(array[i] + " ");
    }
    System.out.print(array[end]);
  }

  public T removeFirst() {
    if (size == 0) {
      return null;
    }
    nextfirst = (nextfirst + 1) % length;
    T ret = array[nextfirst];
    array[nextfirst] = null;
    size--;
    if (length >= 16 && length / size >= 4) {
      resizeArray(length / 2);
    }
    return ret;
  }

  public T removeLast() {
    if (size == 0) {
      return null;
    }
    nextlast = nextlast == 0 ? length - 1 : nextlast -1;
    T ret = array[nextlast];
    array[nextlast] = null;
    size--;
    if (length >= 16 && length / size >= 4) {
      resizeArray(length / 2);
    }
    return ret;
  }

  public T get(int index) {
    if (index >= size) {
      return null;
    }
    return array[(nextfirst + 1 + index) % length];
  }  
}
