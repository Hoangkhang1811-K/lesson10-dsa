package mylinkedlist;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.addFirst(10);               // 10
        list.addLast(20);                // 10 -> 20
        list.add(30);                    // 10 -> 20 -> 30
        list.add(1, 99);                 // 10 -> 99 -> 20 -> 30

        System.out.println("Size: " + list.size());       // 4
        System.out.println("First: " + list.getFirst());  // 10
        System.out.println("Last: " + list.getLast());    // 30
        System.out.println("Get(1): " + list.get(1));     // 99

        System.out.println("IndexOf 20: " + list.indexOf(20)); // 2
        System.out.println("Contains 30? " + list.contains(30)); // true

        System.out.println("Remove index 2: " + list.remove(2)); // 20
        System.out.println("Size after remove: " + list.size()); // 3

        System.out.println("Remove object 99: " + list.remove(Integer.valueOf(99))); // true
        System.out.println("Size after remove obj: " + list.size()); // 2

        MyLinkedList<Integer> cloned = list.clone();
        cloned.addLast(777);

        System.out.println("Original size: " + list.size()); // 2
        System.out.println("Cloned size: " + cloned.size()); // 3

        list.clear();
        System.out.println("Size after clear: " + list.size()); // 0
    }
}
