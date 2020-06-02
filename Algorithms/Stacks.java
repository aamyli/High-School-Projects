import java.util.*;

// STACKS ASSIGNMENT
// ICS4U
// Amy Li
// June 1 2020

// The questions are as follows:
// 1) Create the Pop method
// 2) Create the isEmpty method
// 3) Create a method that takes a postfix expression as a string and returns the value of the expression.
//    Assume that the operands and operators are separated by a space.
// 4) Create a method that takes a infix expression as a string and returns the postfix version as a string.
//    Use the Shunting-yard algorithm, and assume only basic operators (+, -, * , / ) are available. 
//    Assume that the operands and operators are separated by a space.

public class Stacks {
	
	private Node top;
	class Node {
		int info;
		Node link;
		
		Node (int i, Node n) {
			info = i;
			link = n;
		}
	}
	
	// push new Node to top of Stack
	public void push (int item) {
		if (top == null) {
			top = new Node (item, null);
		}
		else {
			top = new Node (item, top);
		}
	}
	
	// returns top Node value
	public int peek() {
		if (top == null) {
			throw new EmptyStackException();
		}
		return top.info;
	}
	
	// pop Node off top of Stack
	public Node pop() {
		if (isEmpty() == true) {
			throw new EmptyStackException();
		}
		else {
			Node a = top;
			top = top.link;
			return a;
		}
	}
	
	// checks if Stack is empty
	public boolean isEmpty() {
		if (top == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// calculates value of a postfix string
	public static Node postfix(String a) {

		Stacks s = new Stacks();

		for (int i = 0; i < a.length(); i++) {
			char b = a.charAt(i);
			
			if (b == ' ') {
				continue;
			}
			
		
			else if (Character.isDigit(b)) {
				int n = 0;
				while (Character.isDigit(b)) {
					n = n*10 + (int)(b-'0');
					i++;
					b = a.charAt(i);
				}
				i--;
				s.push(n);
			}
			else {
				int c = (s.pop()).info;

				int d = (s.pop()).info;

				switch(b) {
					case '+':
					s.push(d+c);
					break;
					
					case '-':
					s.push(d-c);
					break;
					
					case '*':
					s.push(d*c);
					break;
					
					case '/':
					s.push(d/c);
					break;
				}
  
			}
		}
		return s.pop();
	}
	
	// converts infix string to postfix
	public static String infixToPostfix(String a) {
		
		// TO EXPLAIN THE NUMERICAL VALUES
		// numbers are assigned to each of the operators as to put them in a stack
		// the numbers are always straight away put into the StringBuilder
		// -3 means (
		// -1 means stack empty
		// 2 means +, 4 means - (therefore if the value % 2 = 0, it takes precedence
		// 1 means /, 3 means * (therefore does not take precedence
		// this follows the Shunting Yard Algorithm!
		
		
		StringBuilder s = new StringBuilder();
		Stacks p = new Stacks();
		
		// loops through entire string
		for (int i = 0; i < a.length(); i++) {

			char b = a.charAt(i);
			
			// skips spaces
			if (b == ' ') {
				continue;
			}
			
			// detects numbers (1+ digits)
			else if (Character.isDigit(b)) {
				int n = 0;
				while (Character.isDigit(b)) {
					n = n*10 + (int)(b-'0');
					i++;
					b = a.charAt(i);
				}
				i--;
				s.append(n).append(' ');
			}
			
			else {
				// seeks out which operator it is and acts accordingly based on precedence
				// the pseudocode used is from https://brilliant.org/wiki/shunting-yard-algorithm/#:~:text=The%20shunting%20yard%20algorithm%20is,account%20the%20order%20of%20precedence.
				if (b == '(') {
					p.push(-3); 
				}
				else if (b == ')') {
					while (p.peek() != -3) {
						if ((p.peek()) == 1) {
							s.append('/').append(' ');
						}
						else if ((p.peek()) == 2) {
							s.append("+").append(' ');
						}
						else if ((p.peek()) == 3) {
							s.append('*').append(' ');
						}
						else if ((p.peek()) == 4) {
							s.append('-').append(' ');
						}
						p.pop();
					}
					p.pop();
				}
				else if (b == '+') {
					p.push(2);	
				}
				else if (b == '-') {
					p.push(4);	
				}
				else if (b == '/') {
					// looks for operators in stack at take precedence
					while ((p.peek())%2 == 0) {
						if ((p.pop().info) == 2) {
							s.append('+').append(' ');
						}
						if ((p.pop().info) == 4) {
							s.append('-').append(' ');
						}
						
					}
					p.push(1);	
				}
				else if (b == '*') {
					// looks for operators in stack at take precedence
					while ((p.peek())%2 == 0 && p.peek() != -1) {
						if ((p.pop().info) == 2) {
							s.append('+').append(' ');
						}
						else if ((p.pop().info) == 4) {
							s.append('-').append(' ');
						}
					}
					p.push(3);	
				}
				
			}
			
		}
		
		// checks to ensure there are no leftover operators left in the stack at the end
		while (!p.isEmpty()) {
			if ((p.peek()) == 1) {
				s.append('/').append(' ');
			}
			else if ((p.peek()) == 2) {
				s.append("+").append(' ');
			}
			else if ((p.peek()) == 3) {
				s.append('*').append(' ');
			}
			else if ((p.peek()) == 4) {
				s.append('-').append(' ');
			}
			p.pop();
		}
		
		
		return s.toString();
		
	}
	
	
	public static void main(String[] args) {
		Stacks a = new Stacks();
		a.push(5);
		a.push(6);
		a.push(7);
		
//		uncomment to test pop method
//		a.pop();
//		System.out.println(a.peek());

// 		uncomment to test isEmpty method
//		Stacks b = new Stacks();
//		System.out.println(a.isEmpty());
//		System.out.println(b.isEmpty());

//		uncomment to test posfix method
// 		postfix to infix = ((100 + 200)/2)*5 + 7 = 757
//		Node c = postfix("100 200 + 2 / 5 * 7 +");
//		System.out.println(c.info);

//		uncomment to test infixToPostfix method
//		System.out.println(infixToPostfix("( 74 / ( 4 + 3 ) ) - 3 "));
	}

}
