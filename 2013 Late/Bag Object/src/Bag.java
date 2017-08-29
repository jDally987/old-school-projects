// created date: November 21, 2013 at 5:33:09 PM

public class Bag<T> {
	
	public class BagElement<T> {
		private T data;
		private int count;
		
		public BagElement(T d){
			
		}
	}
	
	private int size;
	
	public Bag(){
		
	}
	
	
	public boolean empty(){
		boolean emp = false;
		if (size==0) emp = true;
		return emp;
	}
	
	public boolean removeAll(T item){
		
	}
	
	public int size(){
		return size;
	}
}
