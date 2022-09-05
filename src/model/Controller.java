package model;

public class Controller {

	private Leaderboard leaderboard;

	public boolean simulate() {
		// TODO - implement Controller.simulate
		throw new UnsupportedOperationException();
	}

	public void initialize() {
		// TODO - implement Controller.initialize
		throw new UnsupportedOperationException();
	}

	public int calculateScore() {
		// TODO - implement Controller.calculateScore
		throw new UnsupportedOperationException();
	}

	public String printGrid() {
		// TODO - implement Controller.printGrid
		throw new UnsupportedOperationException();
	}

	public String printScores() {
		return leaderboard.toString();
	}

	public Controller() {
		leaderboard = new Leaderboard();
	}

}