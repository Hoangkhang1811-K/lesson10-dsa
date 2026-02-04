package thuchanh;

public class MyListTest {
   public static void main(String[] args) {
        MyList<Integer> listInteger = new MyList<Integer>();
        listInteger.add(1);
        listInteger.add(2);
        listInteger.add(3);
        listInteger.add(4);
        listInteger.add(5);
       System.out.println("elements 4 in listInteger:"+listInteger.get(4));
       System.out.println("elements 1 in listInteger:"+listInteger.get(1));
       System.out.println("elements 2 in listInteger:"+listInteger.get(2));
       listInteger.get(-1);
       System.out.println("elements -1 in listInteger:"+listInteger.get(-1));
   }
}
