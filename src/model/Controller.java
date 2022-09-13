package model;

import java.time.*;

import java.time.Duration;

public class Controller {

	private Instant start=null;

	private Instant end=null;

	private Clock clock = Clock.systemDefaultZone();
	

	private Leaderboard leaderboard;

	private Grid grid;

	public String simulate() {
		String simulation = grid.simulate(), result = null;
		int pipeCount, score;

		if (simulation!=null){
			end = clock.instant();
			pipeCount = grid.getPipeCount();
			result = simulation;
			score = calculateScore(pipeCount);
			result = finalScore(addToLeaderBoard(result, score)); 
			printGrid();
			grid = null;
		}
		
		return result;
	}

	public void initialize(String nickName) {
		start = clock.instant();
		grid = new Grid(8, 8, nickName);
		
	}

	public boolean play(int row, int column, int pipeType){
		return grid.changePipeType(row, column, pipeType);
	}

	public int calculateScore(int pipeCount) {
		return pipeCount*100-(60-(int)Duration.between(start, end).getSeconds())*10;
	}

	public String printGrid() {
		return grid.toString();
	}

	public Score addToLeaderBoard(String nickName, int score){

		leaderboard.add(nickName,Duration.between(start, end), score, null);
		Score s = new Score(nickName, score, Duration.between(start,end));
		start = null;
		end = null;
		return s;

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