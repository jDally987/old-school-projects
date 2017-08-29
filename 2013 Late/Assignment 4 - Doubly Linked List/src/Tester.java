// created date: December 3, 2013 at 1:56:10 AM

public class Tester {
  
  public static void main(String[] args) {
    
    // Creating an empty list and testing the methods with an empty list
    DoublyLinkedList<Integer> numbers = new DoublyLinkedList<Integer>();
    if (numbers.empty()) {
      System.out.println("OK: The list is empty");
    }
    
    try {
      numbers.remove(10);
    } catch (ListEmptyException e) {
      System.out.println("OK: The list is empty : Exception- cannot remove from an empty list");
    } catch (NotInListException e) {
      System.out.println("WRONG: This should not happen");
    }
    
    try {
      numbers.current();
    } catch (ListEmptyException e) {
      System.out.println("OK: The list is empty : current points to null");
    }
    
    if (numbers.size() != 0) {
      System.out.println("WRONG: The list is empty. Size should be zero");
    }
    
    if (!numbers.end()) {
      System.out.println("WRONG: The list is empty end should return true.");
    }
    
    // Insert some values in the list
    numbers.insert(10);
    numbers.insert(5);
    numbers.insert(7);
    numbers.insert(3);
    if (numbers.size() == 4) {
      System.out.println("OK: The size is 4");
    } else {
      System.out.println("WRONG: the size should be 4 and is "+ numbers.size());
      System.out.println("HINT: Did you forget to increment size when inserting into the list?");
    }
    
    System.out.println(displayFor(numbers));
    System.out.println(displayBack(numbers));
    
    // Set the list to the begining
    numbers.begin();
    int value = 0;
    int[] orderedNumbers = { 3, 5, 7, 10 };
    
    // Check that the elements are in order
    for (int i = 0; i < numbers.size(); i++) {
      try {
        value = numbers.current();
      } catch (ListEmptyException e) {
        System.out.println("WRONG: The list should not be empty");
      }
      
      if (value == orderedNumbers[i]) {
        System.out.println("OK: Correct");
      } else {
        System.out.println("WRONG: " + orderedNumbers[i]
                             + " should be the element and is " + value);
      }
      
      numbers.advance();
    }
    
    // Traverse the list in reverse order
    // Set the list to the beginning
    numbers.begin();
    // Move one element back. This will move to the head.
    numbers.retreat();
    // Move one more step to end to point to the last element
    numbers.retreat();
    
    // Check that we are at the last element in the list
    if (numbers.end()) {
      System.out.println("OK: the list is positioned at the last element");
    } else{
      System.out.println("WRONG: the list is not positioned at the end or your method is not correct");
    }
    
    for (int i = numbers.size() - 1; i >= 0; i--) {
      try {
        value = numbers.current();
      } catch (ListEmptyException e) {
        System.out.println("WRONG: The list should not be empty");
      }
      
      if (value == orderedNumbers[i]) {
        System.out.println("OK: Correct");
      } else {
        System.out.println("WRONG: " + orderedNumbers[i]
                             + " should be the element and is " + value);
      }
      
      numbers.retreat();
    }
    
    // Try remove an element that doesn't exist
    try {
      numbers.remove(1);
    } catch (ListEmptyException e) {
      System.out.println("WRONG: This should not happen. The list is not empty");
    } catch (NotInListException e) {
      System.out.println("OK: The element is not in the list");
    }
    
    // Try remove an element that exists
    try {
      numbers.remove(3);
    } catch (ListEmptyException e) {
      System.out.println("WRONG: This should not happen. The list is not empty");
    } catch (NotInListException e) {
      System.out.println("WRONG: This should not happen. The list contains the element");
    }
    System.out.println(displayFor(numbers));
    System.out.println(displayBack(numbers));
    
    // Try remove a second element that exists
    try {
      numbers.remove(7);
    } catch (ListEmptyException e) {
      System.out.println("WRONG: This should not happen. The list is not empty");
    } catch (NotInListException e) {
      System.out.println("WRONG: This should not happen. The list contains the element");
    }
    if (numbers.size() == 2)
      System.out.println("OK: List should be of size 2 at this point");
    else {
      System.out.println("WRONG: List should be size 2 and is size "+ numbers.size());
      System.out.println("HINT: Did you decrement size in your remove method?");
    }
    
    System.out.println(displayFor(numbers));
    System.out.println(displayBack(numbers));
    
    // Go through list again and check
    numbers.begin();
    
    int[] orderedNumbers1 = { 5, 10 };
    
    for (int i = 0; i < numbers.size(); i++) {
      try {
        value = numbers.current();
      } catch (ListEmptyException e) {
        System.out.println("WRONG: The list should not be empty");
      }
      
      if (value == orderedNumbers1[i]) {
        System.out.println("OK: Correct");
      } else {
        System.out.println("WRONG: " + orderedNumbers1[i] + " should be the element and is " + value);
      }
      
      numbers.advance();
    }
    // Creating a duplicate copy of old list.
    DoublyLinkedList<Integer> newList = new DoublyLinkedList<Integer>(numbers);
    
    // Tests duplicated list to match original
    newList.begin();
    for (int i = 0; i < newList.size(); i++) {
      try {
        value = newList.current();
      } catch (ListEmptyException e) {
        System.out.println("WRONG: New list should not be empty");
      }
      if (value == orderedNumbers1[i]) {
        System.out.println("OK: Correct");
      } else{
        System.out.println("WRONG: " + orderedNumbers1[i] + " should be the element and is " + value);
      }
      newList.advance();
    }
    System.out.println("Old list = " + displayFor(numbers));
    System.out.println("New List = " + displayFor(newList));
    
    
  }
  
  public static String displayFor(DoublyLinkedList<Integer> listIn) {
    listIn.begin();
    String toPrint = "The forward list is: ";
    while (!listIn.end()) {
      toPrint = addNumber(toPrint, listIn);
      listIn.advance();
    }
    toPrint = addNumber(toPrint, listIn);
    return toPrint;
  }
  
  public static String displayBack(DoublyLinkedList<Integer> listIn) {
    listIn.begin();
    String toPrint = "The backward list is: ";
    listIn.retreat();
    listIn.retreat();
    try {
      while (listIn.current() != null) {
        toPrint = addNumber(toPrint, listIn);
        listIn.retreat();
      }
    } catch (ListEmptyException e) {
      System.out.println("Can't display, list must be empty");
    }
    return toPrint;
  }
  
  public static String addNumber(String toPrint, DoublyLinkedList<Integer> listIn) {
    try {
      toPrint += listIn.current() + " ";
    } catch (ListEmptyException e) {
      System.out.println("Can't display, list must be empty");
    }
    return toPrint;
  }
  
  
}
