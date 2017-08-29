public class DoublyLinkedList<Comparable> {
    
    private static class Node<Comparable> {
    	private Comparable data;
    	private Node<Comparable> next;
    	private Node<Comparable> prev;
    	private Node (Comparable d){
    		data = d;
    		next = null;
    		prev = null;
    	}
    	private Node (Comparable d, Node<Comparable> pref, Node<Comparable> nref){
    		data = d;
    		next = nref;
    		prev = pref;
    	}
    }

    private Node<Comparable> head;
    private Node<Comparable> current;
    private int size;
    public DoublyLinkedList(){
    	size = 0;
    	head = new Node(null);
    	current = head;
    }
    
    public DoublyLinkedList (DoublyLinkedList<Comparable> l){
    	size = 0;
    	head = new Node(null);
    	Node<Comparable> previous = new Node(null);
    	
    	current = l.head.next;
    	previous = head;
    	
    	while (current != l.head){
    		Node n = new Node(current.data);
    		n.prev = previous;
    		previous.next = n;
    		advance();
    		previous = previous.next;
    		size++;
    	}
    	previous.next = head;
    	head.prev = previous;
    }


    //insert's the new value into its proper ordered position in the list
    public void insert (Comparable d){
    	Node n = new Node(d);
    	if (empty()){
    		n.next=head;
    		n.prev=head;
    		head.next=n;
    		head.prev=n;
    	}else{
    		begin();
    		advance();
    		while (current != head && d.compareTo(current.data) > 0){
    			advance();
    		}
    		n.prev = current;
    		n.next = current.next;
    		current.next.prev = n;
    		current.next = n;
    	}
    	size++;
    }
    //removes an existing value from the list
    public void remove (Comparable d) throws ListEmptyException, NotInListException {
    	if (empty()) throw new ListEmptyException();
    	
    	begin();
    	advance();
    	while (current != head && d.compareTo(current.data) != 0){
			advance();
		}
    	if (current == head) throw new NotInListException();
    	current.next.prev = current.prev;
    	current.prev.next = current.next;
    	size--;
    }
    //positions the list at the beginning of the list
    public void begin(){
    	current = head;
    }
    //advances to the next element in the list
    public void advance(){
    	current = current.next;
    }
    //retreats to the previous item in the list
    public void retreat(){
    	current = current.prev;
    }
    //returns the value at the current point of the list
    public T current() throws ListEmptyException {
    	if (size == 0) throw new ListEmptyException();
    	return current.data;
    }
    //determines if we are at the end of the list
    public boolean end(){
    	return current == head.prev;
    }
    //determines if empty
    public boolean empty(){
    	return size==0;
    }
    //returns the number of elements in the list
    public int size(){
    	return size;
    }
}