package mainPackage;

import java.util.ArrayList;

public class GenericStack<T> {
	
	private int top = 0;
	ArrayList<T> stack;
		
	public GenericStack (){
		
		stack = new ArrayList<T> ();
		
	}

	public int size () {
		
		return top; 
		
	}

	public void push (T item) {
		
		stack.add (top++, item);
	}

	public T pop () {
		
		return stack.remove (--top);
	}
}
