import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.PaintContext;
//import java.awt.Rectangle;

import javax.swing.JPanel;

public class Draw extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int missed;

	public Draw(int m) {
		missed = m;
	}

	public Dimension getPreferredSize() {
		return new Dimension(650, 500);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		switch (missed) {
		case 9:
			g.fillRect(335, 230, 4, 40);
		case 8:
			g.fillRect(361, 230, 4, 40);
		case 7:
			g.fillRect(305, 150, 30, 4);
		case 6:
			g.fillRect(365, 150, 30, 4);
		case 5:
			g.fillRect(350, 120, 4, 10);
			g.setColor(Color.green);
			g.fillRect(335, 130, 30, 100);
		case 4:
			g.setColor(Color.yellow);
			g.fillOval(325, 70, 50, 50);
			g.setColor(Color.black);
			g.fillOval(340, 85, 3, 3);
			g.fillOval(360, 85, 3, 3);
			g.fillRect(340, 105, 20, 2);
			g.fillRect(350, 95, 2, 4);
		case 3:
			g.setColor(Color.darkGray);
			g.fillRect(250, 60, 100, 5);
			g.fillRect(350, 60, 3, 10);

		case 2:
			g.fillRect(250, 60, 5, 290);
		case 1:
			g.fillRect(250, 350, 300, 50);

		}
	}

}
