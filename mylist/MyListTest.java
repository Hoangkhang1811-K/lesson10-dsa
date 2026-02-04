package mylist;

public class MyListTest {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();

        // add(E)
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("Size after add: " + list.size()); // 3

        // add(index, E)
        list.add(1, 99);
        System.out.println("Get(1): " + list.get(1)); // 99
        System.out.println("Get(2): " + list.get(2)); // 20

        // contains/indexOf
        System.out.println("Contains 30? " + list.contains(30)); // true
        System.out.println("IndexOf 99: " + list.indexOf(99));   // 1

        // remove
        int removed = list.remove(2);
        System.out.println("Removed: " + removed); // 20
        System.out.println("Size after remove: " + list.size()); // 3

        // clone
        MyList<Integer> copied = list.clone();
        copied.add(777);
        System.out.println("Original size: " + list.size()); // 3
        System.out.println("Copied size: " + copied.size()); // 4

        // clear
        list.clear();
        System.out.println("Size after clear: " + list.size()); // 0
    }
}
