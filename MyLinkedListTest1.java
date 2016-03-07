import java.util.*;

public class MyLinkedListTest1 {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();

        for (int i=0; i<1000; i++) {
            list.add("Item " + i);
            list.add(0,"Item " + i);
        }
        System.out.println("Size = " + list.size());
        boolean good = true;
        for (int i=0; i<1000; i++) {
            if ( ! list.get(i).equals(list.get(1999-i)) ) {
                good = false;
            }
        }
        System.out.println("Get = " + good);
        good = true;
        for (int i=0; i<1000; i++) {
            if ( list.indexOf("Item " + i) != 999-i  ||  list.lastIndexOf("Item " + i) != 1000+i ) {
                good = false;
            }
        }
        System.out.println("indexOf = " + good);
        list.set(1000,"needle");
        good = (!list.contains("not in it")  &&  list.contains("needle"));
        good = good && (!list.get(1000).equals("Item 0")  &&  list.get(1000).equals("needle"));
        System.out.println("contains/set/get = " + good);
        list.remove(0);
        System.out.println("remove = " + (list.indexOf("Item 999")==1998));
        list.clear();
        System.out.println("Size = " + list.size());
        System.out.println("contains (empty) = " + !list.contains("needle"));
        System.out.println("indexOf (empty) = " + (list.indexOf("needle")==-1));
        System.out.println(list);
        list.add("abc");
        System.out.println(list);
        list.add("def");
        System.out.println(list);
        list.add("ghi");
        System.out.println(list);
    }
}