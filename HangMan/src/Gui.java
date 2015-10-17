import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {
	JFrame frame = new JFrame("Hangman game");
	JPanel buttonPanel = new JPanel();
	JPanel guessedPanel = new JPanel();
	JPanel northPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel drawPanel = new JPanel();
	JPanel keyButtonPanel1 = new JPanel();
	JPanel keyButtonPanel2 = new JPanel();
	JPanel keyButtonPanel3 = new JPanel();
	JButton startButton = new JButton("Start");
	JButton keyButtons[] = new JButton[35];
	JLabel textPane = new JLabel("");
	JLabel textGuessedPanel = new JLabel("");
	JLabel missedPanel = new JLabel("");
	Controll controll = new Controll();

	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.start();
	}

	private void start() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		textPane.setSize(200, 40);
		textPane.setFont(new Font("Serif", Font.PLAIN, 16));
		textGuessedPanel.setFont(new Font("Serif", Font.BOLD, 24));
		startButton.addActionListener(new newGameListener());
		startButton.setFont(new Font("Serif", Font.BOLD, 18));
		buttonPanel.add(startButton);
		buttonPanel.add(textPane);
		guessedPanel.add(textGuessedPanel);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		northPanel.add(buttonPanel);
		northPanel.add(guessedPanel);
		for (int b = 0; b < keyButtons.length; b++) {
			String keys[] = { "q", "w", "e", "r", "t", "z", "u", "i", "o", "p", "ő", "ú", "a", "s", "d", "f", "g", "h",
					"j", "k", "l", "é", "á", "ű", "í", "y", "x", "c", "v", "b", "n", "m", "ö", "ü", "ó" };
			keyButtons[b] = new JButton(keys[b]);
			keyButtons[b].addActionListener(new keyListener(b, keys[b]));
			if (b < 12) {
				keyButtonPanel1.add(keyButtons[b]);
			} else if (b < 24) {
				keyButtonPanel2.add(keyButtons[b]);
			} else
				keyButtonPanel3.add(keyButtons[b]);
		}
		missedPanel.setPreferredSize(new Dimension(400, 100));
		missedPanel.setAlignmentX(0.5f);
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		southPanel.add(missedPanel);
		southPanel.add(keyButtonPanel1);
		southPanel.add(keyButtonPanel2);
		southPanel.add(keyButtonPanel3);
		frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
		frame.getContentPane().add(BorderLayout.NORTH, northPanel);
		frame.setVisible(true);
	}

	// if push a letter button
	class keyListener implements ActionListener {
		private String key;
		private int n;
		public keyListener(int num, String k) {
			key = k;
			n = num;
		}
		public void actionPerformed(ActionEvent event) {
			String valueTypedIn = key;
			keyButtons[n].setBackground(Color.gray);
			boolean win = controll.newGuess(valueTypedIn);
			refreshWiew();
			if (win) {
				textPane.setText("Gratulálunk! Ön nyert!");
			} else if (controll.getMissed() == 9) {
				textPane.setText("Ez most nem sikerült. Próbáljon egy új játékot! ");
				textGuessedPanel.setText("A feladvány a " + controll.getTarget() + " volt");
			}
		}
	}

	// if push button "start"
	class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			controll.newGame();
			refreshWiew();
			for (JButton b : keyButtons) {
				b.setBackground(null);
			}
		}
	}
	
	public void refreshWiew() {
		textPane.setText("A következő feladvány:");
		textGuessedPanel.setText(controll.getGuessedWell());
		missedPanel.setText("Még " + (9 - controll.getMissed()) + " hibalehetőséged van!");
		frame.getContentPane().add(BorderLayout.CENTER, new Draw(controll.getMissed()));
	}
	
}
