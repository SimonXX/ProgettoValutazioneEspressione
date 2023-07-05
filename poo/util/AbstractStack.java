package poo.util;

import java.util.Iterator;

public abstract class AbstractStack<T> implements Stack<T> {
	
	public String toString() {
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("[");
		Iterator<T> it = iterator();
		
		while(it.hasNext()) {
			T x = it.next();
			sb.append(x);
			if(it.hasNext()) sb.append(", ");
		}
		
		sb.append("]");

		
		return sb.toString();
		
	}//toString
	

}//AbstractStack
