import java.util.*;

public class MyLinkedListTest2 {
    public static void main(String[] args) {
        MyLinkedList<Integer> l = new MyLinkedList<Integer>();
        l.add(2);
        System.out.println(l + ". Expected: [2].");
        l.add(0,1);
        System.out.println(l + ". Expected: [1,2].");
        l.add(l.size(),3);
        System.out.println(l + ". Expected: [1,2,3].");
        Iterator<Integer> itr = l.iterator();
        while (itr.hasNext()) System.out.print(itr.next() + " ");
        System.out.println();
        l.removeLast();
        
        System.out.println(l + ". Expected: [1,2].");
        l.add(3);
        System.out.println(l + ". Expected: [1,2,3].");
        l.add(1,3);
        System.out.println(l + ". Expected: [1,3,2,3].");
        System.out.println("" + l.indexOf((Object)new Integer(0)) +
            l.indexOf((Object)new Integer(3)) + l.lastIndexOf(3) +
            ". Expected: -113.");
        l.remove((Object)new Integer(3));
        System.out.println(Arrays.toString(l.toArray()) + ". Expected: [1,2,3].");
        l.clear();
        if (!l.contains(1) && l.isEmpty()) {
            try {
                l.remove(0);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException");
            }
        }
        l.add(0,0);
        int i = l.set(0,42);
        if (l.get(0) == i+42) {
            System.out.println("Done!");
        }
    }
}