package model;

import java.util.ArrayList;

public class Leaderboard {

	private Score root;

    private int boardLength=37;

	public Leaderboard() {
	}

	/**
	 * 
	 * @param score
	 * @param nickname
	 */
	public String add(int score, String nickname, Score current) {

		String out="";

        Score newScore = new Score(nickname, score);

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

                add(score, nickname, current.getRight());

            }

        }else if(newScore.getScore()<current.getScore()){

            if(current.getLeft()==null){

                current.setLeft(newScore);

                out="El nodo ha sido añadido";

            }else{

                add(score, nickname, current.getLeft());

            }

        }

		return out;

	}

	

	/**
	 * 
	 * @param node
	 */
	public ArrayList<Score> inOrder(Score node, ArrayList<Score> topTen) {
		
		ArrayList<Score> toAdd = new ArrayList<Score>();

		if(node==null){

            node=root;

        }

        if(topTen==null){

            topTen = new ArrayList<Score>();

        }

        if(node!=null&&topTen.size()<10){

            toAdd.clear();

            topTen.addAll(toAdd);

            if(node.getRight()!=null){
    
                toAdd.addAll(inOrder(node.getRight(), topTen));
    
            }

            topTen.add(node);

            if(node.getLeft()!=null){
    
                toAdd.addAll(inOrder(node.getLeft(), topTen));
    
            }

        }

        return topTen;


	}


	public String toString() {
		
		ArrayList<Score> topTen = inOrder(null, null);

		int counter=0;

        String out= "--|	-	 LeaderBoard	-	|--\n--|Nickname                      Puntaje|--\n-------------------------------------------\n";

        String space =" ";

        String spaces = "";

        Score actual;

        do{

            actual = topTen.get(counter);

            int numOfSpace = boardLength-(actual.getNickname().length()+(""+actual.getScore()).length());

            for(int i=0; i<numOfSpace; i++){

                spaces+=space;

            }

            out+="- ["+ actual.getNickname() + spaces + actual.getScore() +"] -\n";

            spaces="";

            counter++;

        }while(counter<topTen.size());

        return out;

	}

}