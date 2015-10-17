public class Controll {
	private HangMan hangAMan = new HangMan();

	// during a game when there is a new guess
	public boolean newGuess(String l) {
		boolean win = false;
		hangAMan.wordcheck(l, hangAMan.getTarget());
		if (hangAMan.getTarget().equals(hangAMan.getGuessedWell().replaceAll("\\s", ""))) {
			win = true;
		}
		return win;
	}

	// when start a new game
	public void newGame() {
		hangAMan.setMissed();
		hangAMan.setTarget();
		hangAMan.setGuessedWell();
	}

	public String getTarget() {
		return hangAMan.getTarget();
	}

	public String getGuessedWell() {
		return hangAMan.getGuessedWell();
	}

	public int getMissed() {
		return hangAMan.getMissed();
	}
}
