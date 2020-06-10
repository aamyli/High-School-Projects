import java.util.EmptyStackException;

//QUEUE ASSIGNMENT
//ICS4U
//Amy Li
//June 9 2020

//Brief investigation into queues and their relationships with stacks. 

public class Queue {
	
	private Node front;
	private Node rear;
	static Stacks s1 = new Stacks();
	static Stacks s2 = new Stacks();
	
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
	
	public static void stackEnqueue(int item) {
		// make sure all items are in s1, so the last item can be pushed in the correct order 
		while (!s2.isEmpty()) {
			s1.push(s2.pop().info);
		}
		s1.push(item);
	}
	
	public static int stackDequeue() {
		// pushes all items to s2 so that the first item ends on top and can be pushed out (following FIFO format)
		while (!s1.isEmpty()) {
			s2.push(s1.pop().info);
		}
		return s2.pop().info;
	}
	
	public static void main(String[] args) {
		Queue c = new Queue();
// 		uncomment these to test out the peek and dequeue methods for QUEUES
//		c.enqueue(5);
//		c.enqueue(4);
//		c.enqueue(3);
		
//		System.out.println(c.peek());
//		System.out.println((c.dequeue()).info);
//		System.out.println(c.peek());
		
		
		// USING TWO STACKS TO MAKE A QUEUE
		// goal: as queue is FIFO, and stack is LIFO, two stacks will allow for it to also be FIFO, imitating a queue
		
		// 3, 4, and 5 are pushed to s1
		stackEnqueue(3);
		stackEnqueue(4);
		stackEnqueue(5);
		// in dequeue process, 5, 4, and 3 are pushed to s2 in that order. 3, the last item in s2, is pushed out
		System.out.println(stackDequeue());
		// to enqueue again, the remaining 5 and 4 are returned to s1 in the order of 4, 5 (to maintain what it was before)
		// then 6 is pushed in
		stackEnqueue(6);
		// to dequeue, 6, 5, and 4 are pushed to s2 in that order. 4, the last item in s2, is pushed out.
		System.out.println(stackDequeue());
		

	}

}
