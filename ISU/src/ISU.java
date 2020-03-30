/* ISU - Hangman 
 * Amy Li
 * 01/14/2019
 */ 

import java.awt.*;   
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class ISU extends JFrame {
	
	// this is the first thing the program reads. this sets the JFrame so the layout is established
	public ISU() {
		// calls the DrawingPanel class into the program's main class
		DrawingPanel demo = new DrawingPanel();
		// add it to the JFrame
		getContentPane().add(demo);
		// JFrame info
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
	}

	public static void main(String[] args) {
		// creates instance of Scanner class
		Scanner sc = new Scanner(System.in);
		// sets the title, its font, and its location
		JLabel titlehangman = new JLabel("HANGMAN");
		titlehangman.setLocation(230, 20);
		titlehangman.setSize(420, 100);
		titlehangman.setFont(new Font("Sans Serif", Font.BOLD, 60));
		// creates JPanel for the title section
		JPanel textPanel = new JPanel();
		textPanel.setLayout(null);
		textPanel.setLocation(0, 0);
		textPanel.setSize(800, 100);
		textPanel.setBackground(Color.WHITE);
		// adds the JLabel to the JLabel
		textPanel.add(titlehangman);
		// creates JPanel for 
		JPanel textPanel2 = new JPanel();
		textPanel2.setLayout(null);
		textPanel2.setLocation(300, 100);
		textPanel2.setSize(500, 500);
		textPanel2.setBackground(Color.WHITE);
		// calls the goodCopyISU into the main method, and adds the component called in there (demo) to the main method as well
		ISU retrieveDrawing = new ISU();
		Component drawing1 = retrieveDrawing.getContentPane().getComponent(0);
		// creates an instance of DrawingPanel(a JPanel)
		DrawingPanel drawing = (DrawingPanel)drawing1;
		drawing.setLayout(null);
		drawing.setSize(300,500);
		drawing.setBackground(Color.WHITE);
		drawing.setLocation(0,100);
		// adds the panels to the overall JFrame
		retrieveDrawing.add(textPanel);
		retrieveDrawing.add(textPanel2);
		// calls the method which executes the game
		AskAndAnswer(drawing, textPanel2);

	}
	private static void AskAndAnswer(DrawingPanel drawing, JPanel textPanel2) {
		// creates a String to store all of the wrong letters
		String wrongLettersBox = ""; 
		// creates a JLabel for the wrong letters string
		JLabel wrongLettersLabel = new JLabel(wrongLettersBox);
		wrongLettersLabel.setLocation(300, 400);
		wrongLettersLabel.setSize(450, 100);
		wrongLettersLabel.setFont(new Font("Sans Serif", Font.PLAIN, (int) 20));
		// creates a JLabel for the wrong letters heading
		JLabel wrongLettersHeading = new JLabel("Wrong Letters:");
		wrongLettersHeading.setLocation(300, 350);
		wrongLettersHeading.setSize(200, 100);
		wrongLettersHeading.setFont(new Font("Sans Serif", Font.ITALIC, (int) 20));
		// add and repaint for it to appear onto the JFrame
		textPanel2.add(wrongLettersHeading);
		wrongLettersHeading.repaint();
		// creates a String to store the inputed word
		String word = "";
		
		JOptionPane.showMessageDialog(null, "Welcome to Hangman! Here are some rules:\n" 
		+ "1. Player 1 MUST enter a word - no special characters or spaces!\n"
		+ "2. To be fair, you can ONLY guess letters - not words!\n"
		+ "3. Player 2 gets 10 guesses - once your guesses run out, you lose!");
		
		// creates boolean. while it is false, the program will continue to retrieve input
		boolean usable = false;
		while (usable == false) {
			usable = true;
			// creates a pop up that asks for a word
			word = JOptionPane.showInputDialog("Player 1: Please Enter A Word");
			// sets to uppercase for output and usability
			word = word.toUpperCase();
			// if word is not only letters, then asks for user to re-enter
			if (!word.matches("[a-zA-Z]+")) {
				JOptionPane.showMessageDialog(null, "Please enter letters only!");
				usable = false;
			}
		}
		// creates a char array to store the letters of the word
		char[] wordarray = word.toCharArray();
		// sets the x coordinate locations for the word
		int letterLocation = 400/wordarray.length;
		// creates an array of JLabels for the word
		JLabel[] letters =new JLabel[word.length()];
		// saves each letter of the char array to a JLabel
		for (int i = 0; i < word.length(); i++) {
			letters[i] = new JLabel(Character.toString(wordarray[i]));
		}
		// creates an array of JLabels for the lines underneath
		JLabel[] lettersBlank =new JLabel[word.length()];
		// saves a "___" to each JLabel
		for (int i = 0; i < word.length(); i++) {
			lettersBlank[i] = new JLabel("__");
		}
		// sets fontsize as 0, and changes it depending on the length of the word
		int fontsize = 0;
		if (letters.length <= 7) {
			fontsize = 50;
		}
		else if (letters.length > 7 && letters.length < 10) {
			fontsize = 30;
		}
		else if (letters.length >= 10 && letters.length < 13) {
			fontsize = 20;
		}
		else if (letters.length >= 13){
			fontsize = 20;
		}
		// sets the location of each JLabel 
		for (int i = 0; i < word.length(); i++) {
			lettersBlank[i].setLocation((int) 315+(i*letterLocation), 210);
			lettersBlank[i].setSize(400, 100);
			lettersBlank[i].setFont(new Font("Sans Serif", Font.BOLD, (int) fontsize-10));
			// adds the blank lines to the screen
			textPanel2.add(lettersBlank[i]);
			lettersBlank[i].repaint();
		}
		for (int i = 0; i < word.length(); i++) {
			letters[i].setLocation((int) 320+(i*letterLocation), 200);
			letters[i].setSize(400, 100);
			letters[i].setFont(new Font("Sans Serif", Font.BOLD, (int) fontsize));
		}
		// sets length for the while loop to run
		int correctWord = word.length();
		// sets for the correctguesses BEFORE calculating any repeats
		int correctguesses = 0;
		// sets number of wrongguesses (to know which part of the man to output)
		int wrongguesses = 0;
		// actual number of correctguesses AFTER calculating any repeats
		int totalcorrectguesses = 0;
		// to calculate how many repeats for correct 
		int correctC = 0;
		// to calculate how many repeats for incorrect
		int counter = 0;
		// array lists (which can add values on)
		ArrayList<Integer> correctLetterPosition = new ArrayList<Integer>();
		ArrayList<String> wrongletters = new ArrayList<String>();
		ArrayList<String> correctletters = new ArrayList<String>();

		// loop continues while the word has not been fully guesses yet
		while (totalcorrectguesses != correctWord) {
			// sets guess as empty
			String guess = "";
			// same concept as the word; checks to ensure that it is only a letter
			boolean usable1 = false;
			while (usable1 == false) {
				usable1 = true;
				guess = JOptionPane.showInputDialog("Player 2: Enter Your Guess");
				guess = guess.toUpperCase();
				if (!guess.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Please enter ONLY a letter!");
					usable1 = false;
				}
			}
			// as long as guess length is 1 character, the program will continue
			if (guess.length() == 1) {
				// converts to char so it can compare with the characters array
				char charGuess = guess.charAt(0);
				// if any match, add it to the correctguesses variable & add the letter to appear
				for (int i = 0; i < wordarray.length; i++) {
					if (charGuess == wordarray[i]) {
						correctguesses = correctguesses + 1;
						correctLetterPosition.add(i);
					}
				}
				// once correctguesses go over 0, then must check guess for any repeats 
				if (correctguesses > 0) {
					// go through correctletters array loop to see if the guess is the same as any previous guesses
					for (int a = correctletters.size() - 1; a >= 0; a--) {
						if (guess.equals(correctletters.get(a))) {
							// if it is, add to counter
							correctC = correctC + 1;
						}
					}
					// if the counter is at 0, it is a new guess and it can be added to the correctletters array
					if (correctC == 0) {
						correctletters.add(Character.toString(charGuess));
						totalcorrectguesses = totalcorrectguesses + correctguesses;
						// this goes through the actual word to know where to put the letter
						for (int i = 0; i < correctLetterPosition.size(); i++) {
							int index = correctLetterPosition.get(i);
							textPanel2.add(letters[index]);
							letters[index].repaint();
						}
						correctletters.clear();
					}
					// tell user they have already guessed the letter
					else {
						System.out.println(correctC);
						JOptionPane.showMessageDialog(null, "You've already tried this letter!");
					}
					// reset
					correctC = 0;
					correctguesses = 0;
				}
				// means the guess is wrong
				else {
					// if wrongguesses at 0, add the wrong guess straight to the array 
					if (wrongguesses == 0) {
						wrongletters.add(Character.toString(charGuess));
						wrongLettersBox = charGuess + "  ";
						wrongLettersLabel.setText(wrongLettersBox);
						textPanel2.add(wrongLettersLabel);
						wrongLettersLabel.repaint();
						wrongguesses = wrongguesses + 1;
					}
					// if not, must check if it is a repeat
					else {
						// check if the guess is the same as any of the past ones
						for (int i = wrongletters.size() - 1; i >= 0; i--) {
							if (guess.equals(wrongletters.get(i))) {
								// add to counter if so
								counter = counter + 1;
							}
						}
						// if counter is at 0, the wrong guess is a new one and it can be added to teh array and added to the string
						if (counter == 0) {
							wrongletters.add(Character.toString(charGuess));
							wrongLettersBox = wrongLettersBox + charGuess + "  ";
							wrongLettersLabel.setText(wrongLettersBox);
							textPanel2.add(wrongLettersLabel);
							wrongLettersLabel.repaint();
							wrongguesses = wrongguesses + 1;
						}
					}
					// matches the wrong guesses to the respective body components that should show up. if counter at 0, it is wrong, but if over, then
					// it was a repeated letter
					if (wrongguesses == 1) {
						drawing.repaint();
						drawing.bDrawHead = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 2) {
						drawing.repaint();
						drawing.bDrawEyesBefore = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 3) {
						drawing.repaint();
						drawing.bDrawMouthBefore = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 4) {
						drawing.repaint();
						drawing.bDrawNeck = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 5) {
						drawing.repaint();
						drawing.bDrawBody = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 6) {
						drawing.repaint();
						drawing.bDrawArm1 = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 7) {
						drawing.repaint();
						drawing.bDrawArm2 = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 8) {
						drawing.repaint();
						drawing.bDrawLeg1 = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 9) {
						drawing.repaint();
						drawing.bDrawLeg2 = true;
						if (counter == 0) {
							JOptionPane.showMessageDialog(null, "WRONG!");
						}
						else {
							JOptionPane.showMessageDialog(null, "You've already tried this letter!");
						}
						counter = 0;
					}
					if (wrongguesses == 10) {
						drawing.repaint();
						drawing.bDrawEyesBefore = false;
						drawing.bDrawMouthBefore = false;
						drawing.bDrawEyesAfter = true;
						drawing.bDrawMouthAfter = true;
					}
				}
				// over 10 = out of guesses + lose
				if (wrongguesses >= 10) {
					JOptionPane.showMessageDialog(null, "Player 2 - you lose!");
					for (int i = 0; i < wrongletters.size(); i++) {
						System.out.println(wrongletters.get(i));
					}
					// game over
					System.exit(0);
				}
			}
			else {
				// if the guess is over one letter
				JOptionPane.showMessageDialog(null, "Please enter one character!");
			}
		}
		// guessed word correctly
		JOptionPane.showMessageDialog(null, "Congrats! You win.");
	}
	// new class for jpanels, allows for drawings
	public class DrawingPanel extends JPanel {

		int TLX = 80; // top left x coordinate
		int TLY = 50; // top left y coordinate
		int width = 10;
		
		// currently sets as false so they are nto visible
		boolean bDrawArm1 = false;
		boolean bDrawArm2 = false;
		boolean bDrawHead = false;
		boolean bDrawNeck = false;
		boolean bDrawBody = false;
		boolean bDrawLeg1 = false;
		boolean bDrawLeg2 = false;
		boolean bDrawHanger = false;
		boolean bDrawEyesBefore = false;
		boolean bDrawEyesAfter = false;
		boolean bDrawMouthBefore = false;
		boolean bDrawMouthAfter = false;
		// paint component to draw
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			hanger(g2);
			// if the booleans become true, the components become visible 
			if(bDrawArm2) {
				arm2(g2);
			}
			if(bDrawLeg1) {
				leg1(g2);
			}
			if(bDrawLeg2) {
				leg2(g2);
			}
			if(bDrawArm1) {
				arm1(g2);
			}
			if(bDrawNeck) {
				neck(g2);
			}
			if(bDrawHead) {
				head(g2);
			}
			if(bDrawBody) {
				body(g2);
			}
			if(bDrawHanger) {
				hanger(g2);
			}
			if(bDrawEyesBefore) {
				eyesBefore(g2);
			}
			if(bDrawEyesAfter) {
				eyesAfter(g2);
			}
			if(bDrawMouthBefore) {
				mouthBefore(g2);
			}
			if(bDrawMouthAfter) {
				mouthAfter(g2);
			}
		}
		// draws the hanger; stays visible entire time
		public void hanger (Graphics2D g) {
			g.setColor(Color.BLACK);
			g.fillRect(TLX, TLY, TLX+50, width); // top horizontal bar
			g.fillRect(TLX, TLY, width, TLX+250); // left side vertical bar
			g.fillRect(TLX+TLX+10, TLY, 2, 50); // right side vertical bar
		}
		// draws head
		public void head(Graphics2D g) {
			g.setColor(Color.WHITE);
			g.fillOval(TLX+TLX-20, TLY+width+39, 65, 70);
			g.setColor(Color.BLACK);
			g.drawOval(TLX+TLX-20, TLY+width+39, 65, 70);
		}
		// draws neck
		public void neck(Graphics2D g) {
			g.setColor(Color.WHITE);
			g.fillRect(TLX+TLX+5, TLY+width+100, 20, 20);
			g.setColor(Color.BLACK);
			g.drawRect(TLX+TLX+5, TLY+width+100, 20, 20);
		}
		// draws body
		public void body(Graphics2D g) {
			g.setColor(Color.WHITE);
			int height = TLY+width+100+20;
			int[] xPoints = {TLX+65, TLX+125, TLX+120, TLX+70};
			int[] yPoints = {height, height, height+70, height+70};
			g.fillPolygon(xPoints, yPoints, 4);
			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints, yPoints, 4);
		}
		// draws leg 1
		public void leg1(Graphics2D g) {
			g.setColor(Color.WHITE);
			int height = TLY+width+190;
			int[] xPoints = {TLX+70, TLX+80, TLX+65, TLX+80, TLX+100, TLX+95};
			int[] yPoints = {height, height+60, height+120, height+120, height+60, height};
			g.fillPolygon(xPoints, yPoints, 6);
			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints, yPoints, 6);
		}
		// draws leg 2
		public void leg2(Graphics2D g) {
			g.setColor(Color.WHITE);
			int height = TLY+width+190;
			int moveover = 25;
			int[] xPoints = {TLX+70+moveover, TLX+80+moveover, TLX+65+moveover, TLX+80+moveover, TLX+100+moveover, TLX+95+moveover};
			int[] yPoints = {height, height+60, height+120, height+120, height+60, height};
			g.fillPolygon(xPoints, yPoints, 6);
			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints, yPoints, 6);
		}
		// draws arm 1
		public void arm1(Graphics2D g) {
			g.setColor(Color.WHITE);
			int height = TLY+width+120;
			int moveover = 50;
			int[] xPoints = {TLX+75+moveover, TLX+90+moveover, TLX+85+moveover, TLX+70+moveover, TLX+72+moveover, TLX+65+moveover};
			int[] yPoints = {height, height+60, height+120, height+120, height+70, height};
			g.fillPolygon(xPoints, yPoints, 6);
			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints, yPoints, 6);
		}
		// draws arm 2
		public void arm2(Graphics2D g) {
			g.setColor(Color.WHITE);
			int height = TLY+width+120;
			int moveover = -10;
			int[] xPoints = {TLX+75+moveover, TLX+55+moveover, TLX+70+moveover, TLX+85+moveover, TLX+75+moveover, TLX+85+moveover};
			int[] yPoints = {height, height+50, height+120, height+120, height+60, height};
			g.fillPolygon(xPoints, yPoints, 6);
			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints, yPoints, 6);
		}
		// draw eyes before dying
		public void eyesBefore(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.drawLine(TLX+TLX+5, TLY+width+65, TLX+TLX+5, TLY+width+72);
			g.drawLine(TLX+TLX+25, TLY+width+65, TLX+TLX+25, TLY+width+72);
		}
		// draws eyes after dying
		public void eyesAfter(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.drawLine(TLX+TLX+2, TLY+width+65, TLX+TLX+8, TLY+width+74);
			g.drawLine(TLX+TLX+8, TLY+width+65, TLX+TLX+2, TLY+width+74);
			g.drawLine(TLX+TLX+23, TLY+width+65, TLX+TLX+29, TLY+width+74);
			g.drawLine(TLX+TLX+29, TLY+width+65, TLX+TLX+23, TLY+width+74);
		}
		// draws mouth before dying
		public void mouthBefore(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.drawArc(TLX+TLX+2, TLY+width+75, 25, 20, -10, -160);
		}
		// draws mouth after dying
		public void mouthAfter(Graphics2D g) {
			g.setColor(Color.BLACK);
			g.drawArc(TLX+TLX+2, TLY+width+88, 25, 17, 10, 160);
		}
	}
}