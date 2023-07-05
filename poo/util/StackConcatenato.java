package poo.util;


import java.util.Iterator;
import java.util.NoSuchElementException;


public class StackConcatenato<T> extends AbstractStack<T> {
	
	private static class Nodo<E>{
		
		E info;
		Nodo<E> next;
		
	}//Nodo
	
	private Nodo<T> testa = null;
	private int size = 0;
	
	public int size() {return size;}
	
	public void clear() {testa = null; size = 0;}
	
	public boolean contains(T e) {
		
		for(T x: this) {//gli elementi vengono analizzati dalla cima sino al fondo
			if(x.equals(e)) return true;
		}
		
		return false;
		
	}//contains
	
	public void push(T e) {//stiamo aggiungendo un elemento in cima allo stack
		
		Nodo<T> n = new Nodo<>();
		n.info = e;
		
		n.next = testa;
		testa = n;
		size++;
		
	}//push
	
	public T pop() {//rispetto all'interfaccia, non stiamo guadagnando molto, non cambia granché spostarsi con i riferimenti ai nodi dati dalla classe invece che con l'iteratore astratto dell'interfaccia
		
		if(testa == null) throw new NoSuchElementException();
		T x = testa.info;
		testa = testa.next;
		size--;
		return x;
		
		
	}//pop
	
	public T top() {
		
		if(testa == null) throw new NoSuchElementException();
		
		return testa.info;
	}

	//copiamo lo stesso identico iteratore della lista concatenata, con la relativa classe, tralasciando la questione dell'eccezione concurrent modification exception
	public Iterator<T> iterator(){
		return new IteratoreStack();
	}//iterator
	
	private class IteratoreStack implements Iterator<T>{

		
		private Nodo<T> pre = null, cor = null;
		
		@Override
		public boolean hasNext() {
			
			
			if(cor==null) return testa != null;
	 
		
			return cor.next!=null;
			
		}//hasNext

		@Override
		public T next() {

			
			if(!hasNext()) throw new NoSuchElementException();
			
			
			if(cor==null)cor = testa;
			
			else {
				pre=cor;
				cor = cor.next;
			}

			return cor.info;
		}//next
		
		@Override 
		public void remove() {

			
			if(pre==cor)throw new IllegalStateException();
			
			if(cor==testa) testa = testa.next;
			
			else {
	
				pre.next = cor.next;
			}
			
			size --;
			cor=pre;

		}//remove
		
	}//IteratoreStack

}//StackConcatenato
