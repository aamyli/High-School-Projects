import java.util.EmptyStackException;

//QUEUE ASSIGNMENT
//ICS4U
//Amy Li
//June 9 2020

//Brief investigation into queues and their relationships with stacks. 

public class Queue {
	
	private Node front;
	private Node rear;
	
	class Node {
		int info;
		Node link;
		
		Node (int i, Node n) {
			info = i;
			link = n;
		}
	}
	
	public void enqueue (int item) {
		Node temp = new Node (item, null);
		// if queue was empty
		if (rear == null) {
			front = rear = temp;
		}
		// otherwise add node at rear of queue
		else {
			rear = rear.link = temp;
		}
	}
	
	// checks for the front-most value
	public int peek() {
		if (rear == null) {
			throw new EmptyStackException();
		}
		else {
			return front.info;
		}
	}
	
	// takes away the front-most value
	public Node dequeue() {
		if (rear == null) {
			throw new EmptyStackException();
		}
		else { 
			Node temp = front;
			front = front.link;
			return temp;
		}
	}
	
	
	public static void main(String[] args) {
		Queue c = new Queue();
// 		uncomment these to test out the peek and dequeue methods
//		c.enqueue(5);
//		c.enqueue(4);
//		c.enqueue(3);
		
//		System.out.println(c.peek());
//		System.out.println((c.dequeue()).info);
//		System.out.println(c.peek());
		
		// USING TWO STACKS TO MAKE A QUEUE
		// goal: as queue is FIFO, and stack is LIFO, two stacks will allow for it to also be FIFO, imitating a queue
		Stacks s1 = new Stacks();
		Stacks s2 = new Stacks();
		
		// pushing values to stack 1: the order becomes 3, 4, 5, with 5 being the first to come out
		s1.push(3);
		s1.push(4);
		s1.push(5);
		
		// pop s1 to push all of the values to s2, which means that the values in s2 become 5, 4, 3
		while (!s1.isEmpty()) {
			s2.push(s1.pop().info);
		}
		
		// thus, the first value that comes out of s2 is 3.
		// this matches what it'd be like in a queue, in a FIFO format
		System.out.println(s2.pop().info);

	}

}
