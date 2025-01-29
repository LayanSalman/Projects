
public class LinkedList<T extends Comparable<T>> {
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
			current = tmp;
			return tmp.getData();}

		return null;}


	public void add(T data){ 

		if(head==null){
			current = head = new Node<T> (data);

		}
		else {
			current = head;
			Node <T> temp = new Node<T> (data);
			Node <T> prv = null;

			if(head.getData().compareTo(data) >0 ){
				temp.setNext(head);
				head = temp; }


			else {
				while (current!=null && current.getData().compareTo(data)<=0){
					prv=current;
					current=current.getNext();}

				if(current==null){
					prv.setNext(temp);
					temp.setNext(null);
				}

				else{
					temp.setNext(current);
					prv.setNext(temp);
				}

			}

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
		//check for last node
		if(tmp.getNext().getData().compareTo(data)==0){
			removedData = tmp.getNext().getData();
			tmp.setNext(tmp.getNext().getNext()); 
			return removedData; }
		return null;

	}

	public String toString() {

		Node<T>  p = head;
		String str = "" ;

		while ( p != null)
		{
			str += p.getData().toString()+"\n";
			p = p.getNext();
		}
		return str;
	}
}
