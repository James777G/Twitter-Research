package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> n = new Node<T>(element);

		n.setNext(this.head);
		this.head = n;

	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		// if the list is empty and there is no element to be pop out
		if (this.isEmpty()) {
			// throw exceptions
			throw new EmptyStackException();
		} else {
			// update the value of head as well as removing the current head value
			Node<T> prev = this.head;
			this.head = this.head.getNext();

			return prev.getValue();
		}
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		// include the case when the stack is empty
		if (this.isEmpty()) {
			throw new EmptyStackException();
		} else {
			return this.head.getValue();
		}

	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		// set up the node containing this element
		Node<T> n = new Node<T>(element);
		Node<T> data = this.head;
		if (this.isEmpty()) {
			// if the list is empty then simply do the same thing as push
			push(element);
		} else {
			// keep searching until the end value is reached
			while (data.getNext() != null) {
				data = data.getNext();
			}
			data.setNext(n);
		}

	}
}
