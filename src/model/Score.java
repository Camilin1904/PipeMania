package model;

import java.time.Duration;
import java.util.Timer;

public class Score {

	private String nickname;
	private int score;
	private Duration timer;
	private Score rigth;
	private Score left;

	/**
	 * 
	 * @param nickname
	 * @param score
	 */
	public Score(String nickname, int score, Duration timer) {
		this.nickname = nickname;
		this.score = score;
		this.timer = timer;
	}

	public String toString() {
		return nickname + "		" + score + "		";
	}

	public String getNickname(){

		return nickname;

	}

	public int getScore() {
		return this.score;
	}

	public void setRight(Score rigth){
	
		this.rigth=rigth;

	}

	public void setLeft(Score left){
	
		this.left=left;

	}

	public Score getRight(){

		return rigth;

	}

	public Score getLeft(){

		return left;

	}

	public Duration getTimer(){

		return timer;

	}

}