package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	
	public Node<T> front; // head variable
	public Node<T> rear; // tail variable
	int size; 
	
	public Queue() {		
            // TODO 1
		front = null;
		rear = null;
		size = 0;
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
		
		Node<T> oldHead = other.front;
		while (oldHead != null) {
			this.enqueue((T) oldHead.data);
			oldHead = oldHead.next;
			
		}
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
		if (size == 0) {
			return true;
		}
            return false;
	}

	@Override
	public int size() {
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		Node<T> temp = new Node(element,null);

		if (isEmpty()) {
			front = temp;
			rear = temp;
		}
		
		else {
			rear.next = temp;
			rear = temp;	
		}
		
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
		T data;
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		data = front.data;
		front = front.next;
		
		if (front == null) {
			rear = null;
		}
		
		size--;
            return data;
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		if (isEmpty()) {
	throw new NoSuchElementException();
		}
            return front.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
		
		Queue<T> q = new Queue(this);
		
		if (q.isEmpty()) {
			return q;
		}
		
		T info = q.peek();
		q.dequeue();
		q = (Queue<T>) q.reversed();
		q.enqueue(info);
		
		return q;
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

}
