import java.util.*;

public class ArrayList<E> {
    private E[] list;
    private int numElements;

    public ArrayList() {  // Create array with space for one element
        numElements = 0;
        list = (E[]) new Object[1];  // not allowed "new E[1]" so cast
    }
    
    public void add(E value) {
        add(numElements, value) ;
    }

    public void add(int index, E value) {
        if (numElements == list.length) {
            list = Arrays.copyOf(list, 2*list.length);
        }
        for (int i=numElements; i>=index+1; i--) {
            list[i] = list[i-1];
        }
        list[index] = value;
        numElements += 1;
    }
    
    public void clear() {
      for(int i=0; i<numElements; i++) {
         list[i] = null;
      }
      numElements=0;
    }
    
    public E get(int index) {
      return list[index];
    }
    
    public int indexOf(Object o) {
      if(o==null) {
         for(int i=0; i<numElements; i++) {
            if(list[i]==null){
               return i;
            }
         }
      } else {
         for(int i=0; i<numElements; i++) {
            if(o.equals(list[i])) {
               return i;
            }
         }
      }
      return -1;    
    }
    
    public boolean contains(Object o) {
      return indexOf(o) >= 0;
    }
    
    public int lastIndexOf(Object o) {
      if(o==null) {
         for(int i=numElements-1; i>=0; i--) {
            if(list[i]==null) {
               return i;
            }
         }
      } else {
         for(int i=numElements-1; i>=0; i--) {
            if(o.equals(list[i])) {
               return i;
            }
         }
      }
      return -1;
   }
                 
    public void remove(int index) {
      if(index==(numElements-1)) {
         list[index]=null;
         numElements = numElements - 1;
      } else {
         for(int i=index; i<=(numElements-index-1); i++) {
            list[i] = list[i+1];
         }
         list[index]=null;
         numElements = numElements - 1;
      }
    }
    
    public void set(int index, E value) {
      list[index] = value;
    }
    
    public int size() {
      return numElements;
    }
      
    public String toString() {
        if (numElements==0) {
            return "[]";
        } else {
            String result = "[";
            for (int i=0; i<numElements-1; i++) {
                result = result + list[i].toString() + ", ";
            }
            return result + list[numElements-1].toString() + "]";
        }
    }
    
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<String>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        System.out.println(list);
        MyArrayList<Integer> list2 = new MyArrayList<Integer>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        System.out.println(list2);
        MyArrayList<int[]> list3 = new MyArrayList<int[]>();
        System.out.println(list3);
    }
}