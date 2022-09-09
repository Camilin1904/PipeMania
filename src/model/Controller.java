package model;

import java.util.Scanner;

public class Controller {

	public static Scanner scan = new Scanner(System.in);

	
	public static void main(String[] args) {
		int r, cl, p =0;
		Controller c = new Controller();
		c.initialize("Camilin");
		while (true){
			System.out.println(c.printGrid());
			r = scan.nextInt();
			cl = scan.nextInt();
			p = scan.nextInt();
			c.play(r, cl, p);
			System.out.println(c.simulate());
		}
	}

	private Grid grid;

	public String simulate() {
		String simulation = grid.simulate();
		int pipeNum = 0;
		int score = 0;
		String result = null;

		if (simulation!=null){
			pipeNum = grid.getPipeCount();
			System.out.println(pipeNum);
			//TODO - calculate the score 
			result = simulation + "//" + score;
			printGrid();
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