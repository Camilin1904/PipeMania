package model;
/**
 * Manages the playboard of the game.
 */
public class Grid {

	private Pipe head;
	private int columns;
	private int rows;
	private Pipe start;
	private String nickName;
	private int pipeCount;
	

	/**
	 * 
	 * @param columns
	 * @param rows
	 */
	public Grid(int columns, int rows, String nickName) {
		this.columns = columns;
		this.rows = rows;
		this.nickName = nickName;
		int sRow = (int)((Math.random()*8));
		int sCol = (int)((Math.random()*8)+1);
		int eRow = sRow;
		while ((eRow = (int)((Math.random()*8)))==sRow);
		int eCol = sCol;
		while ((eCol=(int)((Math.random()*8)))==sCol);
		head = create(0,0,-1,new Pipe(6, "0,0"), null, sRow, sCol, eRow, eCol);
	}



	/**
	 * Initializes the quadruple linked list that is used to store the playboard, it creates rows in an "s" pattern
	 * @param r Must be 0 when first called
	 * @param c Must be 0 when first called
	 * @param lastC Must be -1 when first called
	 * @param current Must be a new pipe type object when first called
	 * @param last	Must be null when first called
	 * @return The head of the list
	 */
	public Pipe create(int r, int c, int lastC, Pipe current, Pipe last, int sRow, int sCol, int eRow, int eCol) {

		if (sRow==r&&sCol==c){
			start = current;
			current.setPipeType(1);
		}
		else if (eRow==r&&eCol==c) current.setPipeType(2);
		
		if (c>lastC){//This means the pipes are getting created to the right
			current.setLeft(last);//sets its left to the last one
			if (r!=0){//Unless it is located in the first row there is the need to set its upper pipe
				current.setUp(current.getLeft().getUp().getRight());//travels trough adjacent pipes to get to the pipe that would be up
				current.getUp().setDown(current);
			}
			if (c!=columns) current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));//If it isn't in the end it continues moving right
			else if(r!=rows) current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current, sRow, sCol, eRow, eCol));//If its in the end it moves down
		}

		else if (c<lastC){//This means the pipes are getting created to the left
			current.setRight(last);
			if (r!=0){
				current.setUp(current.getRight().getUp().getLeft());
				current.getUp().setDown(current);
			}
			if (c!=0) current.setLeft(create(r, c-1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));
			else if(r!=rows) current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current, sRow, sCol, eRow, eCol));
		}

		else if (c==lastC){//This means its last pipe its abvove it
			current.setUp(last);
			if (c==0) current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));//depending on its position it sets the creation flow to the right or to the left
			else current.setLeft(create(r, c-1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));
		}

		//Theoretically unreachable condition, used to signal that something went extremelly wrong
		else {
			System.out.println("Unknown error");
		}

		return current;//Returns itself to ensure every conection is filled
	}



	/**
	 * Method to change the type of any pipe on the playboard
	 * @param row Must correspond to the pipe to be changed
	 * @param column Must correspond to the pipe to be changed
	 * @param pipeType The desired type
	 * @return A boolean meaning the succesfullness of the pipe change
	 */
	public boolean changePipeType(int row, int column, int pipeType) {
		Pipe holder = head;
		for (int r=0; r<row; r++){//to move to the row
			if(holder!=null) holder = holder.getRight();
		}
		for (int c=0; c<column; c++){//to move to the column
			if(holder!=null) holder = holder.getDown();
		}
		if (holder!=null){//to check for its existence
			//if (pipeType==1) start = holder;//If it is being changed to the start, saves it as the start
			if (holder.getPipeType()!=PipeType.END&&holder.getPipeType()!=PipeType.START){
				if(holder.getPipeType()==PipeType.NULL) pipeCount++;
				if(pipeType==6) pipeCount--;
				holder.setPipeType(pipeType);//changes the type
				return true;
			}
		}
		return false;//if the condition above was not met, then it means that the operation was unsuccesfull
	}




	/**
	 * Prints a visual representation of the playboard
	 */
	@Override
	public String toString() {
		String print = "   0    1    2    3    4    5    6    7 \n";
		Pipe holder = head, holder2 = head;
		for (int row=0; row<rows; row++){
			print += row;
			for (int column=0; column<columns; column++){
				print += " " + holder.toString() + " ";
				holder = holder.getRight();
			}
			print += "\n";
			holder2 = holder2.getDown();
			holder = holder2;
		}
		return print;
	}



	public String simulate(){
		return simulate(start, start);
	}
	/**
	 * Checks if the conections made by the player are compatible withs each other, used to determine victory
	 * @param current Must be the starting position (pipeType 1(PipeType.Start))
	 * @param last
	 * @return A boolean informing the program of the compatibility of the pipes put together by the player
	 */
	public String simulate(Pipe current, Pipe last){
		String holder = null;
		if(!current.getHasPassed()){
			current.setHasPassed(true);
			if (current.getPipeType()==PipeType.START||current.getPipeType()==PipeType.INTERSECTION){
				if (current.getPipeType()==PipeType.START||last.getPipeType()==PipeType.VERTICAL){
					switch (checkExistenceOf(PipeType.HORIZONTAL, last, current)){
						case "R":
							holder = simulate(current.getRight(), current);
							break;
						case "L":
							holder = simulate(current.getLeft(), current);
							break;
					}
				}
				if (current.getPipeType()==PipeType.START||last.getPipeType()==PipeType.HORIZONTAL){
					switch (checkExistenceOf(PipeType.VERTICAL, last, current)){
						case "D":
							holder = simulate(current.getDown(), current);
							break;
						case "U":
							holder = simulate(current.getUp(), current);
							break;
					}
				}
			}
			else if (current.getPipeType()==PipeType.HORIZONTAL){
				switch (checkExistenceOf(PipeType.HORIZONTAL, last, current)){
					case "R":
						holder = simulate(current.getRight(), current);
						break;
					case "L":
						holder = simulate(current.getLeft(), current);
						break;
				}
				switch (checkExistenceOf(PipeType.INTERSECTION, last, current)){
					case "R":
						holder = simulate(current.getRight(), current);
						break;
					case "L":
						holder = simulate(current.getLeft(), current);
						break;
				}
				if(checkExistenceOf(PipeType.END, last, current).equals("R")||checkExistenceOf(PipeType.END, last, current).equals("L")) holder = nickName;
			}
			else if (current.getPipeType()==PipeType.VERTICAL){
				switch (checkExistenceOf(PipeType.VERTICAL, last, current)){
					case "D":
						holder = simulate(current.getDown(), current);
						break;
					case "U":
						holder = simulate(current.getUp(), current);
						break;
				}
				switch (checkExistenceOf(PipeType.INTERSECTION, last, current)){
					case "D":
						holder = simulate(current.getDown(), current);
						break;
					case "U":		
						holder = simulate(current.getUp(), current);
						break;
				}
				if(checkExistenceOf(PipeType.END, last, current).equals("D")||checkExistenceOf(PipeType.END, last, current).equals("U")) holder = nickName;
			}
		}
		current.setHasPassed(false);
		return holder;

	}

	/**
	 * Used to find where a certain pipe type is in relation to a pipe
	 * @param pType The pipe type to be checked
	 * @param last The last pipe to have been checked
	 * @param current The current pipe
	 * @return where it is or if it isn't
	 */
	public String checkExistenceOf(PipeType pType, Pipe last, Pipe current){
		if(pType!=PipeType.VERTICAL&&current.getRight()!=null&&current.getRight()!=last&&current.getRight().getPipeType()==pType) return "R";
		else if(pType!=PipeType.VERTICAL&&current.getLeft()!=null&&current.getLeft()!=last&&current.getLeft().getPipeType()==pType) return "L";
		else if(pType!=PipeType.HORIZONTAL&&current.getDown()!=null&&current.getDown()!=last&&current.getDown().getPipeType()==pType) return "D";
		else if(pType!=PipeType.HORIZONTAL&&current.getUp()!=null&&current.getUp()!=last&&current.getUp().getPipeType()==pType) return "U";
		return "no";

	}

	public int getPipeCount(){
		return pipeCount;
	}

}