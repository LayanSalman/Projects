
public class Node<T> {
	private  Node<T> next;
	private T data;

	public Node() {
		next = null;
		data = null;
	}

	public Node(T data) {
		next = null;
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
