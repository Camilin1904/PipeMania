package model;

public class Controller {

	private Grid grid;

	public String simulate() {
		String simulation = grid.simulate();
		int pipeNum = 0;
		int score = 0;
		String result = null;

		if (simulation!=null){
			pipeNum = grid.pipeCount();
			//TODO - calculate the score 
			result = simulation + "//" + score;
			grid = null;
		}
		return result;
	}

	public void initialize(String nickName) {
		grid = new Grid(8, 8, nickName);
		// TODO - take time instant to calculate the score
	}

	public boolean play(int row, int column, int pipeType){
		return grid.changePipeType(row, column, pipeType);
	}

	private int calculateScore() {
		// TODO - implement Controller.calculateScore
		throw new UnsupportedOperationException();
	}

	public String printGrid() {
		return grid.toString();
	}

	public String printScores() {
		// TODO - implement Controller.printScores
		throw new UnsupportedOperationException();
	}
}