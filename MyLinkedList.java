import java.util.*;
import java.util.Iterator;

/**
* <h1> MyLinkedList Class </h1>
* This is the source code for linked lists written from scratch that
* mimics the basic functionality of Java's built-in linked list class.
* This was implemented using doubly-linked lists.
* All of the basic methods are listed first, followed by the ListNode
* and MyLinkedListIterator classes. At the end is a main program used to
* test the class's functionality.
* <p>
* 
* @author Tan Dao, Class ID: 1913
* @version 1.0
* @since 11/02/14
*/

public class MyLinkedList<E> implements Iterable {

    private ListNode<E> first;
    private ListNode<E> last;
    private int size;
    
    /**
    *Constructs an empty list
    */
    public MyLinkedList() {
        first = new ListNode<E> (null, null, null);
        last = new ListNode<E> (null, null, first);
        first.next = last;
        size = 0;
    }
//---------------------------------------------------------------------------
//
//  boolean addCheck(E d)
//      
//---------------------------------------------------------------------------    
    /**
    *Adds the given element to the end of this list. Returns
    *<code>true</code> if this list changed as a result of the call.
    *@param d Element to be appended to this list.
    *@return Always returns <code>true</code> for a linked list.
    */
    public boolean addCheck(E d) {
        add(d);
        return true;
    }

//---------------------------------------------------------------------------
//
//  void add(E d)
//      
//---------------------------------------------------------------------------     
    /**
    *Adds the given element to the end of this list.
    *@param d Element to be appended to this list.
    */
    public void add(E d) {    
        if (size==0) {
            last = first = new ListNode<E>(d, null, null);
        } else {
            ListNode<E> curr = last;
            curr.next = new ListNode<E> (d, null, curr);
            last = last.next;
        }
        size += 1;
    }

//---------------------------------------------------------------------------
//
//  void add(int idx, E d)
//      
//---------------------------------------------------------------------------    
    /**
    *Inserts the given element at the specified index into a new node.
    *Reassigns references to and from this node and its neighbors.
    *@param idx Index at which the given element is to be inserted.
    *@param d Element to be inserted.
    *@throws IndexOutOfBoundsException if the index is beyond the given
    *indices for this list.
    */         
    public void add(int idx, E d) { 
        if(idx < 0 || idx > size){
            throw new IndexOutOfBoundsException();
        } else if (idx == 0){
            ListNode<E> newFirst = new ListNode<E>(d, first, null);
            first = newFirst; 
            newFirst.next.prev = newFirst;
            size++;
        } else if (idx == size()){
            add(d);
        } else {
            ListNode<E> curr = first;
            for(int i = 0; i < idx - 1; i++){
                curr = curr.next;
            }
            ListNode<E> newPrev = curr;
            ListNode<E> newNode = new ListNode<E> (d, curr.next, newPrev);
            newPrev.next = newNode;
            newNode.next.prev = newNode;
            size++;
        }
    }

//---------------------------------------------------------------------------
//
//  void clear()
//      
//---------------------------------------------------------------------------    
    /**
    *Removes all elements from this list.
    */
    public void clear() {
        first.next = last;
        last.prev = first;
        size = 0;
    }


//---------------------------------------------------------------------------
//
//  boolean contains(Object o)
//      
//---------------------------------------------------------------------------    
    /**
    *Returns <code>true</code> if and only if this list contains at least
    *one of the given element.
    *@param o Element that this method tests the presence of.
    *@return Returns <code>true</code> if this list contains the given element.
    */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    } 

//---------------------------------------------------------------------------
//
//  E get (int idx)
//      
//---------------------------------------------------------------------------    
    /**
    *Returns the element contained within the given index in this list.
    *@param idx Index of the element to return.
    *@return Returns the element contained within the given index. 
    *@throws NoSuchElementException if this list is empty.
    *indices provided by the list.
    */
    public E get(int idx) {
        if(idx < 0 || idx >= size){
            throw new IndexOutOfBoundsException();
        } else if(size == 0) {
            throw new NoSuchElementException();
        } else {
            ListNode<E> curr = first;
            for(int i = 0; i <= idx - 1; i++){
                curr = curr.next;
            }
            return curr.data;
        }
    }

//---------------------------------------------------------------------------
//
//  int indexOf(Object o)
//      
//---------------------------------------------------------------------------    
    /**
    *Returns the index of the first occurrence of the given element in
    *this list. Returns -1 if the element cannot be found.
    *@param o Element to search for
    *@return Returns the index of the first occurrence of the given element.
    *Returns -1 if it cannot be found.
    */
    public int indexOf(Object o) {
      int idx = 0;
      ListNode<E> curr = first;
      if (first.data.equals(o)) {
         return idx;
      } else {
         while (curr.next != null) {
            curr = curr.next;
            idx++;
            if (curr.data.equals(o)) {
               return idx;
            }
         }
      }
      return -1; 
    } 

//---------------------------------------------------------------------------
//
//  boolean isEmpty()
//      
//---------------------------------------------------------------------------    
    /**
    *Returns <code>true</code> if this list is empty.
    *@return Returns <code>true</code> if this list is empty.
    */
    public boolean isEmpty() {
       return (size == 0);
    }

//---------------------------------------------------------------------------
//
//  Iterator<E> iterator()
//      
//---------------------------------------------------------------------------    
    /**
    *Creates an iterator object that can traverse across this list,
    *both forward and backward.
    *@return Returns an iterator object that can perform all of the methods
    *defined within the MyLinkedListIterator class.
    */
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

//---------------------------------------------------------------------------
//
//  int lastIndexOf(Object o)
//      
//---------------------------------------------------------------------------    
    /**
    *Returns the index of the last occurrence of the given element in this
    *list, or -1 if the element is not found.
    *@param o Element to search for
    */
    public int lastIndexOf(Object o) {
      int idx = -1;
      if (first == null) {
         return idx;
      }
      int counter = -1;
      if (first.data.equals(o)) {
         return 0;
      }
      ListNode<E> curr = first;
      counter++;
      while (curr.next != null) {
         curr = curr.next;
         counter++;
         if (curr.data.equals(o)) {
            idx = counter;
         }
      }
      return idx;
    }

//---------------------------------------------------------------------------
//
//  E remove(int idx)
//      
//---------------------------------------------------------------------------    
    /**
    *Removes the element contained within the given index in this list. 
    *Reassigns the references to and from the node that contained the element.
    *Returns the element removed.
    *@param idx Index of the element to be removed.
    *@return Returns the element removed from this list.
    *@throws IndexOutOfBoundsException if the given index is beyond
    *the range of indices within this list.
    */
    public E remove(int idx) {
        if (idx < 0 || idx > size - 1) {
           throw new IndexOutOfBoundsException();
        } else if (idx == 0) {
           E temp = first.data;
           first = first.next;
           first.prev = null;
           size--;
           return temp;
        } else if (idx == size - 1) {
           E temp = last.data;
           last = last.prev;
           last.next = null;
           size--;
           return temp;
        }
        ListNode<E> temp = first;
        for (int i = 0; i < idx - 1; i++) {
           temp = temp.next;
        }
        ListNode<E> connector = temp.next;
        temp.next = connector.next;
        E elem = connector.data;
        connector = null;
        size--;
        return elem; 
    }

//---------------------------------------------------------------------------
//
//  boolean remove(Object o)
//      
//---------------------------------------------------------------------------    
    /**
    *Removes the first occurrence of the given element. If this list does
    *not contain the element, then it remains unchanged. Returns
    *<code>true</code> if this list contained the element, or if this list 
    *was changed as a result of the call.
    *@param o Element to be removed, if it is present
    *@return <code>true</code> if this list contained the given element.
    */
    public boolean remove(Object o) {
        if (first.data.equals(o)) {
           remove(0);
           return true;
        } else {
           int counter = 1;
           ListNode<E> curr = first;
           while (curr.next != null) {
              curr = curr.next;
              if (curr.data.equals(o)) {
                 remove(counter);
                 return true;
              }
              counter++;
           }
        }
        return false;       
    }
   
//---------------------------------------------------------------------------
//
//  E removeLast()
//      
//---------------------------------------------------------------------------    
    /**
    *Removes and returns the last element from this list.
    *@return the last element from this list
    *@throws NoSuchElementException if this list is empty
    */
    public E removeLast() {
        if(size == 0) {
           throw new NoSuchElementException();
        } else {
           return remove(size - 1);
        }
    }

//---------------------------------------------------------------------------
//
//  E set(int idx, E d)
//      
//---------------------------------------------------------------------------    
    /**
    *Replaces the element contained within the given index in this list
    *with the given element.
    *@param idx Index of the element to replace
    *@param d Element to be stored at the given index 
    *@return the element previously occupying the given index    
    *@throws IndexOutOfBoundsException if the given index is beyond
    *the range of indices within this list.
    */           
    public E set(int idx, E d) {
      if (idx < 0 || idx >= size) {
         throw new IndexOutOfBoundsException();
      } else {
         ListNode<E> curr = first;
         for (int i = 0; i < idx; i++) {
            curr = curr.next;
         }
         E old = curr.data;
         curr.data = d;
         return old;
      }
    }

//---------------------------------------------------------------------------
//
//  int size()
//      
//---------------------------------------------------------------------------    
    /**
    *Returns the number of elements in this list.
    *@return the number of elements in this list
    */    
    public int size() {     
        return size;
    }

//---------------------------------------------------------------------------
//
//  Object[] toArray()
//      
//---------------------------------------------------------------------------    
    /**
    *Returns an array containing all of the elements in this linked list
    *in order (from index 0 to size - 1).
    *@return an array containing all of the elements in this list in proper
    *sequence
    */
    public Object[] toArray() {
      Object[] res = new Object[size];
      int i = 0;
      for (ListNode<E> curr = first; curr != null; curr = curr.next) {
         res[i] = curr.data;
         i++;
      }
      return res;
    }

//---------------------------------------------------------------------------
//
//  String toString()
//      
//---------------------------------------------------------------------------    
    /**
    *Returns a string representation of this list. Elements are separated by
    *commas and the entire string is enclosed in square brackets.
    *@return a string representation of this list
    */              
    public String toString() {
        if (size==0) {
            return "[]";
        } else {
            String res = "[" + first.data;
            ListNode<E> cur = first.next;
            while (cur != null) {
                res = res + "," + cur.data;
                cur = cur.next;
            }
            return res + "]";
        }
    }

//*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*   
   /**
   *Contains the necessary fields for a doubly-linked list: the data that the
   *node contains, and references to both the previous node, and next node in
   *the linked list.
   */
   class ListNode<E> {
       public E data;
       public ListNode<E> next;
       public ListNode<E> prev;
       public ListNode(E data, ListNode<E> next, ListNode<E> prev) {
          this.data = data;
          this.next = next;
          this.prev = prev;
       }
   }

//*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*    
   /**
   *Contains the methods necessary for an iterator object to traverse
   *over a linked list, both forward and backward.
   */
   class MyLinkedListIterator implements Iterator<E> {
       private ListNode<E> forward;
       private ListNode<E> backward;
       private boolean removeOK;
    
       public MyLinkedListIterator() {
           forward = first;
           backward = last;
           removeOK = false;
       }
    
       public boolean hasNext() {
           return (forward != last.next);
       }
    
       public E next() {
           if (!hasNext()) {
              throw new NoSuchElementException();
           }
           E res = forward.data;
           forward = forward.next;
           removeOK = true;
           return res;
       }
       
       public boolean hasPrev() {
           return (backward != first.prev);
       }
       
       public E prev() {
           if (!hasPrev()) {
              throw new NoSuchElementException();
           }
           E res = backward.data;
           backward = backward.prev;
           removeOK = true;
           return res;
       }
       
       public void remove() {
           throw new UnsupportedOperationException();
       }
   }

//*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*~~*
   /**
   *Unit Test
   */    
   public static void main(String[] args) {
        MyLinkedList<Integer> l = new MyLinkedList<Integer>();
        System.out.println(l.size());
        System.out.println(l);
        l.add(10);
        System.out.println(l.size());
        System.out.println(l);
        l.add(20);
        System.out.println(l.size());
        System.out.println(l);
        l.add(30);
        l.add(40);
        if(l.contains(40)) {
           System.out.println("YAY");
        } else {
           System.out.println("BOO");
        }
        Object[] arr = new Object[4];
        arr = l.toArray();
        System.out.println(arr[3]);
        l.add(0, 100);
        l.add(1, 200);
        System.out.println(l.size());
        System.out.println(l);
        l.remove(0);
        System.out.println(l.size());
        System.out.println(l);
        l.remove(4);
        System.out.println(l.size());
        System.out.println(l);
        l.removeLast();
        System.out.println(l.size());
        System.out.println(l);
        System.out.println(l.lastIndexOf(200));
        l.add(300);
        l.add(400);
        l.add(500);
        System.out.println(l.size());
        System.out.println(l);
        Object o = 200;
        if(l.remove(o)) {
           System.out.println("YAY");
        } else {
           System.out.println("BOO");
        }
        System.out.println(l.size());
        System.out.println(l);
        Iterator it = l.iterator();
        while(it.hasNext()) {
           System.out.println(it.next());
        }
        l.add(1, 150);
        l.add(3, 250);
        l.add(5, 350);
        l.set(3, 750);
        System.out.println(l.indexOf(750));
        System.out.println(l);
        l.clear();
        if(l.isEmpty()) {
           System.out.println("Whee");
        }
        if(l.addCheck(50)) {
           System.out.println("Works!");
        }
        System.out.println(l);
   }
}