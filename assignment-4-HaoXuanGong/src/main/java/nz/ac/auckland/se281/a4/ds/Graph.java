package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {
		String s;
		for (int i = 0; i < set.size(); i++) {
			s = set.get(i);
			// set up a string parameter that contains the loop relation
			s = s + "," + s;
			// check for if all the element has a relation points to itself
			if (!relation.contains(s)) {
				return false;
			}
		}
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {
		String a;
		String b = "";
		// inverse of a

		// loop through all the elements and check if there is an inverse for all of it
		for (int i = 0; i < relation.size(); i++) {
			a = relation.get(i);
			b = reverseString(a);
			// check for if the relation contains the reversed relation
			if (!relation.contains(b)) {
				System.out.println("For the graph to be symmetric tuple: " + b + " MUST be present");
				return false;
			}
		}
		return true;
	}

	/**
	 * this method will reverse the order of the characters in a string
	 * 
	 * @param input in type string which you want to reverse
	 * @return the reversed string in type String
	 */
	public String reverseString(String input) {
		String output = "";
		// print the string in a reverse order
		for (int i = input.length() - 1; i >= 0; i--) {
			output = output + input.charAt(i);
		}
		return output;

	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {
		// set up some string parameters and initiate them
		String a = "";
		String b = "";
		String a1 = "";
		String b1 = "";
		String c = "";
		String d = "";
		for (int i = 0; i < relation.size(); i++) {
			// these two functions will return the first and last nodes of the relation
			a = getFirst(relation.get(i));
			b = getLast(relation.get(i));
			for (int j = 0; j < relation.size(); j++) {
				// for each element searched, search for if there is another relation end with
				// the same value as the first one of the previous
				a1 = getFirst(relation.get(j));
				b1 = getLast(relation.get(j));

				if (b.equals(a1) && !b1.equals(a)) {
					c = getLast(relation.get(j));
					d = a + "," + c;
					// if such a relation is not contained return false
					if (!relation.contains(d)) {
						return false;
					}
				}
			}

		}
		return true;
	}

	/**
	 * this method will get the first element and return that as a string
	 * 
	 * @param input is the string you want to check
	 * @return a string containing only the first element
	 */
	public String getFirst(String input) {
		String output = "";
		int i = 0;
		// while the character is not , keep adding
		while (input.charAt(i) != ',') {
			i++;
		}
		// return the searched characters as a string

		for (int a = 0; a < i; a++) {
			output = output + input.charAt(a);
		}
		return output;
	}

	/**
	 * this method will return the last element in the input string as another
	 * string
	 * 
	 * @param input string at which you find the relation
	 * @return string containing only the second element
	 */
	public String getLast(String input) {
		String output = "";
		int i = 0;
		// search the characters until , is reached
		while (input.charAt(i) != ',') {
			i++;
		}
		// print the characters in a string format

		for (int a = i + 1; a < input.length(); a++) {
			output = output + input.charAt(a);
		}
		return output;
	}

	/**
	 * This method returns a boolean based on whether the input sets are
	 * anti-symmetric TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		// if reflexive symmetric and transitive and then equivalent
		if (this.isReflexive(set, relation)) {
			if (this.isSymmetric(relation)) {
				if (this.isTransitive(relation)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {
		List<String> list = new ArrayList<String>();
		if (this.isEquivalence(set, relation)) {
			// if this is a equivalent relation
			for (int i = 0; i < relation.size(); i++) {
				if (getFirst(relation.get(i)).equals(node)) {
					// search for all the related nodes to this certain node
					list.add(getLast(relation.get(i)));
				}
			}
			return list;

		} else {
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");
		}
		// if there is no related nodes then simply return null
		return null;
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		// create two lists variable to store and update the results
		List<Node<String>> list = new ArrayList<Node<String>>();
		List<Node<String>> listTwo = new ArrayList<Node<String>>();
		list = this.breadthFirstSearch(this.root, false);
		while (this.hasNext(list)) {
			// if there is a next discontinuous root then the searching shall continue
			this.root = this.getNextRoot(list);
			// update root
			listTwo = this.breadthFirstSearch(this.root, false);
			for (int i = 0; i < listTwo.size(); i++) {
				// add all the elements into the list from the results of the other roots
				if (!list.contains(listTwo.get(i))) {
					list.add(listTwo.get(i));
				}
			}

		}
		return list;

	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch
		List<Node<String>> list = new ArrayList<Node<String>>();
		int index = 0;
		Node<String> a;
		list.add(start);
		// as long as there is more elements to be added into the list or the list
		// is not on the verge reaching invalid index (empty)
		while (this.isIncreasing(start, list) || list.size() != index + 1) {
			for (int i = 0; i < this.adjacencyMap.get(start).size(); i++) {
				a = this.adjacencyMap.get(start).get(i).getTarget();
				// get the target of all the edges
				if (!list.contains(a)) {
					list.add(a);
					// add the element into the list if it was not inside
				}
			}
			index++;
			start = list.get(index);
		}

		return list;

	}

	/**
	 * this method will indicate whether there are multiple roots for the remaining
	 * data set
	 * 
	 * @param list which you have implemented your previous root trees
	 * @return a boolean indicating whether there is another root to be accounted of
	 */
	public boolean hasNext(List<Node<String>> list) {
		Set<Node<String>> elementList = new HashSet<Node<String>>();
		elementList = this.adjacencyMap.keySet();
		for (int i = 0; i < list.size(); i++) {
			// loop through all the keys in the map
			elementList.remove(list.get(i));
		}
		// use iterator to loop through all the elements in this set
		if (elementList.iterator().hasNext()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * this method will search for the next root in the adjacency map if there is
	 * one
	 * 
	 * @param list which you have implemented your previous root trees
	 * @return a Node containing String that is your next root
	 */
	public Node<String> getNextRoot(List<Node<String>> list) {
		Set<Node<String>> elementList = new HashSet<Node<String>>();
		elementList = this.adjacencyMap.keySet();
		for (int i = 0; i < list.size(); i++) {
			elementList.remove(list.get(i));
		}
		// return the very next root if there is one in the key set
		return elementList.iterator().next();

	}

	/**
	 * this method will justify whether the list will still increase in size.
	 * 
	 * @param start the starting node
	 * @param list  list containing all the previous nodes
	 * @return boolean whether increasing or not
	 */
	public boolean isIncreasing(Node<String> start, List<Node<String>> list) {
		Node<String> a;
		for (int i = 0; i < this.adjacencyMap.get(start).size(); i++) {
			a = this.adjacencyMap.get(start).get(i).getTarget();
			// check for if the map still contains elements that are not inside list
			if (!list.contains(a)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		List<Node<String>> list = new ArrayList<Node<String>>();
		List<Node<String>> listTwo = new ArrayList<Node<String>>();
		list = this.depthFirstSearch(this.root, false);
		while (this.hasNext(list)) {
			// while there is a next root, the searching process should resume
			this.root = this.getNextRoot(list);
			listTwo = this.depthFirstSearch(this.root, false);
			for (int i = 0; i < listTwo.size(); i++) {
				// add all the elements from the new list to the original list
				// which we will be eventually returning as the output
				if (!list.contains(listTwo.get(i))) {
					list.add(listTwo.get(i));
				}
			}

		}
		return list;
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {
		List<Node<String>> list = new ArrayList<Node<String>>();
		List<Node<String>> listTwo = new ArrayList<Node<String>>();
		list.add(start);
		// while there is more elements in the list and there will be more elements
		// joining the list, the searching process should resume
		while (listTwo.size() > 0 || this.isExpanding(start, list) == true) {
			for (int i = 0; i < this.adjacencyMap.get(start).size(); i++) {
				if (!list.contains(this.adjacencyMap.get(start).get(i).getTarget())) {
					// add the target value to the list which we consider as the stack
					listTwo.add(this.adjacencyMap.get(start).get(i).getTarget());
				}
			}
			// update the value of the root so that while loop do not work infinitely
			start = listTwo.get(listTwo.size() - 1);
			if (!list.contains(start)) {
				// if the value is not contained inside the list then add it
				list.add(start);
			}
			// remove the element from the stack to update the top value
			listTwo.remove(listTwo.size() - 1);
		}
		return list;

	}

	/**
	 * this method will indicate whether the next set of nodes will expand out list
	 * 
	 * @param start is the root
	 * @param list  is the result we obtained
	 * @return a boolean saying if it is expanding
	 */
	public boolean isExpanding(Node<String> start, List<Node<String>> list) {
		for (int i = 0; i < this.adjacencyMap.get(start).size(); i++) {
			if (!list.contains(this.adjacencyMap.get(start).get(i).getTarget())) {
				// check for whether there are more elements that are not inside the list
				return true;
			}
		}
		return false;
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
