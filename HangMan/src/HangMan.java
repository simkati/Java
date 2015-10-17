//import static java.lang.System.in;
//import org.omg.CORBA.PUBLIC_MEMBER;
import java.io.*;
import java.nio.charset.StandardCharsets;

//HangMan is a guessing game, computer "think" a word and player tries to guess it by suggesting letters 
public class HangMan {
	private String fileName = "words.txt"; // keep possibly words for target
	private String target; // player win if find out this word
	private String guessedWell; // contain only letters what player already hit
	private int missed; // number of missed guess

	public String getTarget() {
		return target;
	}

	public void setTarget() {
		target = randomWord();
	}

	public String getGuessedWell() {
		return guessedWell;
	}

	public void setGuessedWell() {
		guessedWell = replaceLetters(target);
	}

	public int getMissed() {
		return missed;
	}

	public void setMissed() {
		missed = 0;
	}

	// take a random word from txt this word will be the target
	private String randomWord() {
		int random = (int) (Math.random() * numberOfLines() + 1);
		String target = "véletlen";
		try {
			InputStream inp = getClass().getClassLoader().getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inp, StandardCharsets.UTF_8));
			for (int i = 0; i < random; i++) {
				target = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("The file is no readable.");
		}

		return target;
	}

	// Helper for randomWord() return the number of lines in the txt
	private int numberOfLines() {
		int lines = 0;
		try {
			InputStream inp = getClass().getClassLoader().getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
			while (reader.readLine() != null)
				lines++;
			reader.close();
		} catch (IOException e) {
			System.out.println("The file is no readable.");
		}

		return lines;
	}

	// make guessWell from target by replace letter with underscore and space to
	// show to player
	private String replaceLetters(String target) {
		guessedWell = "";
		for (int i = 0; i < target.length(); i++) {
			guessedWell = guessedWell + "_ ";
		}
		return guessedWell;
	}

	// input a letter, check target contain it, if yes-> show this letter in
	// guessWell, if it is not hit -> missed is grow
	public void wordcheck(String letter, String target) {
		boolean wrong = true;
		for (int i = 0; i < target.length(); i++) {

			String l = target.substring(i, i + 1);

			if (l.equals(letter)) {
				String before = guessedWell.substring(0, i * 2);
				String after = guessedWell.substring((i * 2) + 2);
				guessedWell = before + l + " " + after;
				wrong = false;
			}
		}
		if (wrong == true) {
			missed = missed + 1;
		}
	}

}
