package mylinkedlist;

import java.util.Objects;

public class MyLinkedList<E> implements Cloneable {

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }

        E getData() {
            return data;
        }
    }

    private Node<E> head;
    private int numNodes = 0;

    public MyLinkedList() {
    }

    public int size() {
        return numNodes;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        numNodes++;
    }

    public void addLast(E e) {
        if (head == null) {
            addFirst(e);
            return;
        }

        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = new Node<>(e);
        numNodes++;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == 0) {
            addFirst(element);
            return;
        }

        Node<E> prev = nodeAt(index - 1);
        Node<E> newNode = new Node<>(element);
        newNode.next = prev.next;
        prev.next = newNode;
        numNodes++;
    }

    public E get(int index) {
        rangeCheck(index);
        return nodeAt(index).data;
    }

    public E getFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        return head.data;
    }

    public E getLast() {
        if (head == null) throw new IllegalStateException("List is empty");

        Node<E> tail = head;
        while (tail.next != null) tail = tail.next;
        return tail.data;
    }

    public E remove(int index) {
        rangeCheck(index);

        if (index == 0) {
            E removed = head.data;
            head = head.next;
            numNodes--;
            return removed;
        }

        Node<E> prev = nodeAt(index - 1);
        Node<E> target = prev.next;

        E removed = target.data;
        prev.next = target.next;
        numNodes--;
        return removed;
    }

    public boolean remove(Object o) {
        if (head == null) return false;

        if (Objects.equals(head.data, o)) {
            head = head.next;
            numNodes--;
            return true;
        }

        Node<E> prev = head;
        Node<E> cur = head.next;

        while (cur != null) {
            if (Objects.equals(cur.data, o)) {
                prev.next = cur.next;
                numNodes--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public boolean contains(E o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(E o) {
        int index = 0;
        Node<E> cur = head;
        while (cur != null) {
            if (Objects.equals(cur.data, o)) return index;
            cur = cur.next;
            index++;
        }
        return -1;
    }

    public void clear() {
        head = null;
        numNodes = 0;
    }

    @Override
    public MyLinkedList<E> clone() {
        try {
            @SuppressWarnings("unchecked")
            MyLinkedList<E> copy = (MyLinkedList<E>) super.clone();

            // deep copy node chain (copy nodes, same data references)
            copy.head = null;
            copy.numNodes = 0;

            Node<E> cur = this.head;
            while (cur != null) {
                copy.addLast(cur.data);
                cur = cur.next;
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    // LinkedList không có capacity, nhưng nếu đề bắt buộc có method này:
    public void ensureCapacity(int minCapacity) {
        // Do nothing - LinkedList không quản lý mảng.
    }

    // ============ Helpers ============

    private Node<E> nodeAt(int index) {
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= numNodes) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numNodes);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > numNodes) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numNodes);
        }
    }
}
