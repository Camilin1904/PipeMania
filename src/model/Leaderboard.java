package model;

import java.util.ArrayList;

import java.time.*;

import java.util.concurrent.TimeUnit;

public class Leaderboard {

	private Score root;

    public static final int nickToScore=16;

    public static final int scoreToTime=21;

	public Leaderboard() {
	}

	/**
	 * 
	 * @param score
	 * @param nickname
     * Compares the new score with each of the scores in the tree to see if it is higher or lower
     * if the corresponding side is equal to null, it will be added there, otherwise the methos will be 
     * called again but it will compare it to next node
	 */
	public void add(String nickname, Duration timer, int score, Score current) {

        Score newScore = new Score(nickname, score, timer);

        if(current==null){

            current=root;

        }

        if(root==null){

            root = newScore;

        }else if(newScore.getScore()>current.getScore()){

            if(current.getRight()==null){

                current.setRight(newScore);

            }else{

                add(nickname, timer, score, current.getRight());

            }

        }else if(newScore.getScore()<current.getScore()){

            if(current.getLeft()==null){

                current.setLeft(newScore);

            }else{

                add(nickname, timer, score, current.getLeft());

            }

        }
	}

	

	/**
	 * @param node
     * @param topTen
     * The method will go to the last node on the right, it will add it once it is at the last level of the
     * tree, after that it will add to its parent node, and finally add the node located to its left
     * until the ArrayList has 10 positions
	 */

	public ArrayList<Score> inOrder(Score node, ArrayList<Score> topTen) {
		
        if(root==null){

            return null;

        }

		if(node==null){

            node=root;

        }

        if(topTen==null){

            topTen = new ArrayList<Score>();

        }

        if(node!=null&&topTen.size()<10){

            if(node.getRight()!=null){
    
                inOrder(node.getRight(), topTen);
    
            }

            topTen.add(node);

            if(node.getLeft()!=null){
    
                inOrder(node.getLeft(), topTen);
    
            }

        }

        return topTen;


	}


	public String toString() {

		ArrayList<Score> topTen = inOrder(null, null);

		int counter=0;

        String out= "--|	-	LeaderBoard	-	|--\n--|Nickname        Puntaje        Tiempo|--\n-------------------------------------------\n";

        String space =" ";

        String spaces = "";

        Score actual;

        if(topTen!=null){

             do{

            actual = topTen.get(counter);

            int numOfSpace = nickToScore-(actual.getNickname().length());

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

            for(int i=0; i<scoreToTime-(""+actual.getScore()).length()-timeFormat.length(); i++){

                spaces+=space;

            }

            out+=spaces + timeFormat+ "] -\n";

            spaces="";

            counter++;

        }while(counter<topTen.size());

        }

        return out;

	}

}