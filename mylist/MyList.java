package mylist;

import java.util.Arrays;

public class MyList<E> implements Cloneable {
    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;
    private Object[] elements;

    public MyList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public MyList(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("capacity must be >= 0");
        this.elements = new Object[Math.max(capacity, DEFAULT_CAPACITY)];
    }

    public int size() {
        return size;
    }

    public void clear() {
        // clear references to help GC
        for (int i = 0; i < size; i++) elements[i] = null;
        size = 0;
    }

    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    public boolean add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
        return true;
    }

    public void add(int index, E element) {
        // Cho phép add ở cuối: index == size
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        // dịch sang phải 1 bước từ index..size-1
        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = element;
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        E oldValue = elementData(index);

        int numMoved = size - index - 1; // số phần tử phải kéo trái
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }

        elements[--size] = null; // clear last
        return oldValue;
    }

    public int indexOf(E o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) return i;
            }
        }
        return -1;
    }

    public boolean contains(E o) {
        return indexOf(o) >= 0;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) return;

        // grow kiểu ArrayList: tăng ~1.5 lần
        int oldCap = elements.length;
        int newCap = oldCap + (oldCap >> 1); // old * 1.5

        if (newCap < minCapacity) newCap = minCapacity;

        elements = Arrays.copyOf(elements, newCap);
    }

    @Override
    public MyList<E> clone() {
        try {
            @SuppressWarnings("unchecked")
            MyList<E> copy = (MyList<E>) super.clone();
            copy.elements = Arrays.copyOf(this.elements, this.elements.length);
            // size và capacity được giữ nguyên
            return copy;
        } catch (CloneNotSupportedException e) {
            // không xảy ra vì implements Cloneable
            throw new AssertionError(e);
        }
    }

    // ===================== Helpers =====================

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elements[index];
    }
}
