package model;

import java.util.ArrayList;

import java.time.*;;

public class Leaderboard {

	private Score root;

    private int nickToScore=20;

    private int scoreToTime=12;

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
	public String add(String nickname, Duration timer, int score, Score current) {

		String out="";

        Score newScore = new Score(nickname, score, timer);

        if(current==null){

            current=root;

        }

        if(root==null){

            root = newScore;

        }else if(newScore.getScore()>current.getScore()){

            if(current.getRight()==null){

                current.setRight(newScore);

                out="El nodo ha sido añadido";

            }else{

                add(nickname, timer, score, current.getRight());

            }

        }else if(newScore.getScore()<current.getScore()){

            if(current.getLeft()==null){

                current.setLeft(newScore);

                out="El nodo ha sido añadido";

            }else{

                add(nickname, timer, score, current.getLeft());

            }

        }

		return out;

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

            int numOfSpace = nickToScore-(actual.getNickname().length()+(""+actual.getScore()).length());

            for(int i=0; i<numOfSpace; i++){

                spaces+=space;

            }

            out+="- ["+ actual.getNickname() + spaces + actual.getScore();

            spaces="";

            for(int i=0; i<scoreToTime; i++){

                spaces+=space;

            }

            out+= spaces + actual.getTimer().getSeconds()+ "] -\n";

            spaces="";

            counter++;

        }while(counter<topTen.size());

        }

        return out;

	}

}