package nz.ac.auckland.se281.a4.ds;

import java.util.NoSuchElementException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 * @throws NoSuchElementException   if the element does not exist in the
	 *                                  LinkedList
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException, NoSuchElementException {
		Node<T> data = this.head;
		int a = 0;
		if (pos < 0 || pos > this.size() - 1) {
			// if the position variable is out of range of the list.
			throw new InvalidPositionException();
		} else {
			while (a < pos) {
				data = data.getNext();
				a++;
				// this variable is to track the position of the data in the list.
			}
		}
		if (data.getValue() == null) {
			// if the actual value is null then throw exception
			throw new NoSuchElementException();
		} else {
			// if there is a data related then return the data instead
			return data;
		}
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		// first set up a node containing this element
		Node<T> n = new Node<T>(element);

		n.setNext(this.head);
		this.head = n;

	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> t = new Node<T>(element);
		Node<T> data = null;
		if (head == null) {
			// if the list is empty then append perform identically as prepend.
			prepend(element);
		} else {
			// find the last node with size - 1 index and set the next node after it
			data = locateNode(size() - 1);
			data.setNext(t);

		}

	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {
		Node<T> data = head;
		// check if the pos input parameter is within the range of the list
		if (pos < 0 || pos > size() - 1) {
			// throw exception if outside of the range
			throw new InvalidPositionException();
		} else {
			data = locateNode(pos);
		}
		return data.getValue();
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos:     an integer, which is the position
	 * @param element: the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {
		Node<T> n = new Node<T>(element);
		Node<T> data;
		Node<T> anotherData;
		// dummy variables
		if (pos < 0 || pos > size()) {
			// if the data is out of the range of the data
			throw new InvalidPositionException();
		}

		else {
			if (pos == 0) {
				// if the data to be inserted is at the start
				prepend(element);
			}
			if (pos == size()) {
				// if we want to insert the data at the end
				append(element);
			}
			if (pos > 0 && pos < size()) {
				// if the data to be inserted is in the middle of the list
				data = locateNode(pos - 1);
				anotherData = locateNode(pos);
				data.setNext(n);
				n.setNext(anotherData);
			}
		}

	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		Node<T> data;
		Node<T> anotherData;
		if (pos >= 1 && pos <= size() - 2) {
			// when the data to be removed is in the middle of the list
			data = locateNode(pos - 1);
			anotherData = locateNode(pos + 1);
			data.setNext(anotherData);
		} else if (pos < 1 && pos >= 0) {
			// if the data to be removed is at the start of the list
			if (this.size() > 1) {
				data = locateNode(pos + 1);
				this.head = data;
			} else {
				// when the list is empty
				this.head = null;
			}
		} else if (pos > size() - 2 && pos <= size() - 1) {
			// when the data to be removed is at the end of the data
			if (this.size() > 1) {
				data = locateNode(pos - 1);
				data.setNext(null);
			} else {
				// when the list is empty
				this.head = null;
			}
		} else {
			// if the data is out of the range of the list
			throw new InvalidPositionException();
		}

	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {

		int a = 0;
		Node<T> data = this.head;
		/* dummy variable for size */
		if (head == null) {
			// if the head is null return 0 to avoid null exception
			a = 0;
		} else {
			while (data.getNext() != null) {
				data = data.getNext();
				a++;
			}
			a++;
		}
		return a;

	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		// keep searching until the end is reached
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}