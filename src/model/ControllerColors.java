package model;

import java.time.*;

import java.time.Duration;

import java.util.concurrent.TimeUnit;

public class ControllerColors {

	private Instant start=null;

	private Instant end=null;

	private Clock clock = Clock.systemDefaultZone();
	

	private Leaderboard leaderboard;

	private GridColors grid;

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
		grid = new GridColors(8, 8, nickName);
		
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

	public ControllerColors() {
		leaderboard = new Leaderboard();
	}

	public String finalScore(Score actual){

        String out= "--|	-	FinalScore	-	|--\n--|Nickname        Puntaje        Tiempo|--\n-------------------------------------------\n";

        String space =" ";

        String spaces = "";

        int numOfSpace = Leaderboard.nickToScore-actual.getNickname().length();

        for(int i=0; i<numOfSpace; i++){

            spaces+=space;

        }

        out+="- ["+ actual.getNickname() + spaces + actual.getScore();

            spaces="";

            long time = actual.getTimer().getSeconds();

		    int hours = (int)TimeUnit.SECONDS.toHours(time);

		    int minutes = (int)(TimeUnit.SECONDS.toMinutes(time)-TimeUnit.SECONDS.toHours(time)*60);

		    double seconds = (double)(time - TimeUnit.SECONDS.toMinutes(time)*60);

            String timeFormat =hours + ":" + minutes + ":" + String.format("%.2f", seconds);

            for(int i=0; i<Leaderboard.scoreToTime-(""+actual.getScore()).length()-timeFormat.length(); i++){

                spaces+=space;

            }

            out+=spaces + timeFormat+ "] -\n";

            spaces="";

		return out;

	}
}