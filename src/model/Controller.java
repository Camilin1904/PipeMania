package model;

import java.time.*;

import java.time.Duration;

//import java.util.Scanner;

public class Controller {

	private Instant start=null;

	private Instant end=null;

	private Clock clock = Clock.systemDefaultZone();

	private Leaderboard leaderboard;
	/*
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
	}*/ 

	private Grid grid;

	public String simulate() {
		String simulation = grid.simulate();
		String result = null;

		if (simulation!=null){
			end = clock.instant();

			//TODO - calculate the score 
			result = simulation;
			printGrid();
			grid = null;
		}
		
		return result;
	}

	public void initialize(String nickName) {
		grid = new Grid(8, 8, nickName);
		// TODO - take time instant to calculate the score
		start = clock.instant();
	}

	public boolean play(int row, int column, int pipeType){
		return grid.changePipeType(row, column, pipeType);
	}

	public int calculateScore() {
		return grid.getPipeCount()*100-(60-Duration(start, end))*10;
	}

	public String printGrid() {
		return grid.toString();
	}

	public Score addToLeaderBoard(String nickName, int score){

		leaderboard.add(nickName, score, Duration(start, end));

		start = null;

		end = null;

		return new Score(nickName, score, Duration(start,end));

	}

	public String printScores() {
		return leaderboard.toString();
	}

	public Controller() {
		leaderboard = new Leaderboard();
	}

	public String finalScore(Score actual){

        String out= "--|	-	FinalScore	-	|--\n--|Nickname        Puntaje        Tiempo|--\n-------------------------------------------\n";

        String space =" ";

        String spaces = "";

        int numOfSpace = Leaderboard.nickToScore-(actual.getNickname().length()+(""+actual.getScore()).length());

        for(int i=0; i<numOfSpace; i++){

            spaces+=space;

        }

        out+="- ["+ actual.getNickname() + spaces + actual.getScore();

        spaces="";

        for(int i=0; i<Leaderboard.scoreToTime; i++){

            spaces+=space;

        }

        out+= spaces + actual.getTimer().getSeconds()+ "] -\n";

        spaces="";

		return out;

	}
}