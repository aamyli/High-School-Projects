import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HangMan extends Canvas {
	int TLX = 80; // top left x coordinate
	int TLY = 140; // top left y coordinate
	int width = 10;
	private boolean bDrawArm1 = false;
	private boolean bDrawArm2 = false;
	private boolean bDrawHead = false;
	private boolean bDrawNeck = false;
	private boolean bDrawBody = false;
	private boolean bDrawLeg1 = false;
	private boolean bDrawLeg2 = false;
	private boolean bDrawHanger = false;
	private boolean bDrawEyesBefore = false;
	private boolean bDrawEyesAfter = false;
	private boolean bDrawMouthBefore = false;
	private boolean bDrawMouthAfter = false;
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
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
	public void HangManArm1(boolean bShowIt) {
		this.bDrawArm1 = bShowIt;
	}
	public void HangManArm2(boolean bShowIt) {
		this.bDrawArm2 = bShowIt;
	}
	public void HangManHead(boolean bShowIt) {
		this.bDrawHead = bShowIt;
	}
	public void HangManNeck(boolean bShowIt) {
		this.bDrawNeck = bShowIt;
	}
	public void HangManBody(boolean bShowIt) {
		this.bDrawBody = bShowIt;
	}
	public void HangManLeg1(boolean bShowIt) {
		this.bDrawLeg1 = bShowIt;
	}
	public void HangManLeg2(boolean bShowIt) {
		this.bDrawLeg2 = bShowIt;
	}
	public void HangManHanger(boolean bShowIt) {
		this.bDrawHanger = bShowIt;
	}
	public void HangManEyesBefore(boolean bShowIt) {
		this.bDrawEyesBefore = bShowIt;
	}
	public void HangManEyesAfter(boolean bShowIt) {
		this.bDrawEyesAfter = bShowIt;
	}
	public void HangManMouthBefore (boolean bShowIt) {
		this.bDrawMouthBefore = bShowIt;
	}
	public void HangManMouthAfter(boolean bShowIt) {
		this.bDrawMouthAfter = bShowIt;
	}
	public void hanger (Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(TLX, TLY, TLX+50, width); // top horizontal bar
		g.fillRect(TLX, TLY, width, TLX+250); // left side vertical bar
		g.fillRect(TLX+TLX+10, TLY, 2, 50); // right side vertical bar
	}
	public void head(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval(TLX+TLX-20, TLY+width+39, 65, 70);
		g.setColor(Color.BLACK);
		g.drawOval(TLX+TLX-20, TLY+width+39, 65, 70);
	}
	public void neck(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(TLX+TLX+5, TLY+width+100, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect(TLX+TLX+5, TLY+width+100, 20, 20);
	}
	public void body(Graphics2D g) {
		g.setColor(Color.WHITE);
		int height = TLY+width+100+20;
		int[] xPoints = {TLX+65, TLX+125, TLX+120, TLX+70};
		int[] yPoints = {height, height, height+70, height+70};
		g.fillPolygon(xPoints, yPoints, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, 4);
	}
	public void leg1(Graphics2D g) {
		g.setColor(Color.WHITE);
		int height = TLY+width+190;
		int[] xPoints = {TLX+70, TLX+80, TLX+65, TLX+80, TLX+100, TLX+95};
		int[] yPoints = {height, height+60, height+120, height+120, height+60, height};
		g.fillPolygon(xPoints, yPoints, 6);
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, 6);
	}

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
	public void eyesBefore(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawLine(TLX+TLX+5, TLY+width+65, TLX+TLX+5, TLY+width+72);
		g.drawLine(TLX+TLX+25, TLY+width+65, TLX+TLX+25, TLY+width+72);
	}
	public void eyesAfter(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawLine(TLX+TLX+2, TLY+width+65, TLX+TLX+8, TLY+width+74);
		g.drawLine(TLX+TLX+8, TLY+width+65, TLX+TLX+2, TLY+width+74);
		g.drawLine(TLX+TLX+23, TLY+width+65, TLX+TLX+29, TLY+width+74);
		g.drawLine(TLX+TLX+29, TLY+width+65, TLX+TLX+23, TLY+width+74);
	}
	public void mouthBefore(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawArc(TLX+TLX+2, TLY+width+75, 25, 20, -10, -160);
	}
	public void mouthAfter(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.drawArc(TLX+TLX+2, TLY+width+88, 25, 17, 10, 160);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(800,600);
		window.setTitle("test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		HangMan hangMan = new HangMan();
		hangMan.HangManArm1(true);
		hangMan.HangManArm2(true);
		hangMan.HangManLeg1(true);
		hangMan.HangManLeg2(true);
		hangMan.HangManHead(true);
		hangMan.HangManNeck(true);
		hangMan.HangManBody(true);
		hangMan.HangManHanger(true);
		hangMan.HangManEyesBefore(true);
		hangMan.HangManEyesAfter(false);
		hangMan.HangManMouthBefore(true);
		hangMan.HangManMouthAfter(false);
		//hangMan.bDrawArm1 = true;
		window.add(hangMan);
		JLabel hello = new JLabel("testing");
		JPanel label = new JPanel();
		label.add(hello);                   // Add L to JPanel P
		window.getContentPane().add(label);  // Add P to JFrame f

	}

}


