package linkedlists;

// LINKED LISTS ASSIGNMENT
// ICS4U
// Amy Li
// May 29 2020

public class LinkedList // opens linked list class
{
	private Node head; // creates head

	class Node // opens class to create nodes
	{
		int info; // holds information inside node
		Node link; // holds next node in chain

		Node(int i, Node n) // constructs node
		{
			info = i; // sets value of information
			link = n; // sets value of next node
		}

	}

	public void insertFirst(int item) // inserts first node
	{
		head = new Node(item, null); // creates first node in list
	}

	public void insertSecond(int item) // inserts second node
	{
		head.link = new Node(item, null); // creates second node in list
	}

	public void insertThird(int item) // inserts third node
	{
		head.link.link = new Node(item, null); // creates third node in list
	}

	public void addAtFront(int item) // inserts new node before first node
	{
		head = new Node(item, head); // places new node at front
	}

	public void printList() // prints values of list
	{
		for (Node temp = head; temp != null; temp = temp.link) // runs through list
		{
			System.out.println(temp.info); // Prints out information of node
		}
		if (head == null) // checks if there are no values
		{
			System.out.println("There are no values in your list!"); // prints out message
		}
	}

	// find sum of all Node.info values
	public int sum() {
		int sum = 0;
		for (Node temp = head; temp != null; temp = temp.link) // runs through list
		{

			sum = temp.info + sum;
		}
		return sum;
	}

	// delete first Node
	public void deleteFirst() {
		if (head == null) {
			System.out.println("Warning: there are no links in this list.");
		} else {
			head = head.link;
		}
	}

	// delete last Node
	public void deleteLast() {
		if (head == null) {
			System.out.println("Warning: there are no links in this list.");
		} else {
			for (Node temp = head; temp != null; temp = temp.link) // runs through list
			{

				if (temp.link.link == null) {
					temp.link = null;
				}
			}
		}

	}

	// converts LinkedList to String
	public String toString() {
		String a = "";
		for (Node temp = head; temp != null; temp = temp.link) {
			if (temp.link != null) {
				a = a + temp.info + "//";
			} else {
				a = a + temp.info;
			}
		}
		return a;
	}

	// add Node at the end of the list
	public void addAtRear(int item) {
		Node temp = head;
		if (temp == null) {
			temp = new Node(item, null);
		} else {
			while (temp.link != null) {
				if (temp.link.link == null) {
					temp.link.link = new Node (item, null);
					break;

				} else {
					temp = temp.link;
				}
			}
		}
	}
	
	// sees if LinkedList contains a certain value
	public boolean contains (int item) {
		boolean a = false;
		for (Node temp = head; temp != null; temp = temp.link) {
			if (item == temp.info) {
				a = true;
				break;
			}
		}
		return a;
	}
	
	
	
	// delete all Nodes that hold a specified value
	public void deleteAll (int item) {
		Node previous = null;
		for (Node temp = head; temp != null; temp = temp.link) {
			if (temp.info == item) {
				if (temp == head) {
					head = head.link;
				}
				else {
					previous.link = temp.link;
				}
			}
			else {
				previous = temp;
			}
		}
	}
	
	// check if LinkedList order is increasing
	public boolean isOrderedIncreasing() {
		int a = head.info;
		boolean b = true;
		for (Node temp = head.link; temp != null; temp = temp.link) {
			if (temp.info >= a) {
				a = temp.info;
			}
			else {
				b = false;
				break;
			}
		}
		return b;
	}
	
	//check if two LinkedLists are identical
	public boolean isIdentical(LinkedList other) {
		boolean a = true;
		Node tempB = other.head;
		Node tempA = head;
		while ((tempB!= null) && (tempA!= null)) {
			if (tempA.info == tempB.info) {
				tempA = tempA.link;
				tempB = tempB.link;
			}
			else {
				a = false;
				break;
			}
		}
		if (tempB == null || tempA == null) {
			a = false;
		}
		return a;
	}

	public static void main(String[] args) {
		LinkedList k = new LinkedList();
		k.addAtFront(4);
		k.addAtFront(3);
		k.addAtFront(2);
		k.addAtFront(1);
		k.printList();
		
// 		UNCOMMENT various blocks to test out different methods.
		
//		System.out.println(k.sum());
//		k.deleteFirst();
//		k.printList();

//		k.deleteLast();
//		k.printList();
		
//		k.addAtRear(9);
//
//		System.out.println(k.toString());
		
//		System.out.println(k.contains(6));
		
		
//		k.deleteAll(4);
//		k.printList();
		

//		System.out.println(k.isOrderedIncreasing());
		

//		LinkedList a = new LinkedList();
//		a.addAtFront(4);
//		a.addAtFront(6);
//		a.addAtFront(5);
//		a.addAtFront(1);
//		
//		LinkedList b = new LinkedList();
//		b.addAtFront(8);
//		b.addAtFront(6);
//		b.addAtFront(5);
//		b.addAtFront(1);
//		
//		System.out.println(b.isIdentical(a));

	}

}
