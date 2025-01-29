

public class LinkedList<T extends Comparable<T>>{

	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = current = null;
	}
	public boolean empty () {
		return head == null;
	}
	public boolean last () {
		return current.getNext()== null; 
	}
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.getNext();
	}
	public T retrieve () {
		return current.getData();


	}
	public void update (T val) {
		current.setData(val);; 
	}




	public T search(T data) {
		Node<T> tmp = head;
		while (tmp != null&& tmp.getData().compareTo(data) != 0) {
			tmp = tmp.getNext(); 
		}

		if (tmp!=null) {
			current= tmp;
			return tmp.getData();
			
		}
		return null;}


	public void add(T data) { 
	    Node<T> newNode = new Node<>(data);
	    
	    if (head == null) {
	        head = newNode;
	    } else if (head.getData().compareTo(data) > 0) {
	        newNode.setNext(head);
	        head = newNode;
	    } else {
	        Node<T> current = head;
	        Node<T> prev = null;
	        
	        while (current != null && current.getData().compareTo(data) <= 0) {
	            prev = current;
	            current = current.getNext();
	        }
	        
	        newNode.setNext(current);
	        prev.setNext(newNode);
	    }
	}


	public T remove(T data) {

		T findnode= search(data);
		T removedData;
		if (findnode==null) 
			return null;

		if(head.getData().compareTo(data)==0) {
			removedData = head.getData();
			head=head.getNext();
			return removedData; }


		Node<T> tmp = head;
		while (tmp.getNext()!=null) {
			if(tmp.getNext().getData().compareTo(data)==0){
				removedData = tmp.getNext().getData();
				tmp.setNext(tmp.getNext().getNext()); 
				return removedData; }
			tmp=tmp.getNext(); 
		}
		return null;

	} }



























