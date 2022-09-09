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

	/*
	public static void main(String[] args) {
		Grid grid = new Grid(8,8);
		grid.changePipeType(1, 1, 1);
		grid.changePipeType(1, 2, 3);
		grid.changePipeType(1, 3, 5);
		grid.changePipeType(2, 3, 4);
		grid.changePipeType(3, 3, 5);
		grid.changePipeType(3, 4, 3);
		grid.changePipeType(3, 5, 3);
		grid.changePipeType(3, 6, 5);
		grid.changePipeType(2, 6, 4);
		grid.changePipeType(1, 6, 5);
		grid.changePipeType(1, 5, 3);
		grid.changePipeType(1, 4, 2);
		grid.toString();
		System.out.println(grid.simulate(grid.start, grid.start));
	}
	*/
	

	/**
	 * 
	 * @param columns
	 * @param rows
	 */
	public Grid(int columns, int rows, String nickName) {
		this.columns = columns;
		this.rows = rows;
		this.nickName = nickName;
		int sRow = (int)((Math.random()*8)+1);
		int sCol = (int)((Math.random()*8)+1);
		int eRow = (int)((Math.random()*8)+1);
		int eCol = (int)((Math.random()*8)+1);
		head = create(1,1,0,new Pipe(6, "1,1"), null, sRow, sCol, eRow, eCol);
	}



	/**
	 * Initializes the quadruple linked list that is used to store the playboard, it creates rows in an "s" pattern
	 * @param r Must be 1 when first called
	 * @param c Must be 1 when first called
	 * @param lastC Must be 0 when first called
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
			if (r!=1){//Unless it is located in the first row there is the need to set its upper pipe
				current.setUp(current.getLeft().getUp().getRight());//travels trough adjacent pipes to get to the pipe that would be up
				current.getUp().setDown(current);
			}
			if (c!=columns) current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));//If it isn't in the end it continues moving right
			else if(r!=rows) current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current, sRow, sCol, eRow, eCol));//If its in the end it moves down
		}

		else if (c<lastC){//This means the pipes are getting created to the left
			current.setRight(last);
			if (r!=1){
			current.setUp(current.getRight().getUp().getLeft());
			current.getUp().setDown(current);
			}
			if (c!=1) current.setLeft(create(r, c-1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));
			else if(r!=rows) current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current, sRow, sCol, eRow, eCol));
		}

		else if (c==lastC){//This means its last pipe its abvove it
			current.setUp(last);
			if (c==1) current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current, sRow, sCol, eRow, eCol));//depending on its position it sets the creation flow to the right or to the left
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
		for (int r=1; r<row; r++){//to move to the row
			if(holder!=null) holder = holder.getDown();
		}
		for (int c=1; c<column; c++){//to move to the column
			if(holder!=null) holder = holder.getRight();
		}
		if (holder!=null){//to check for its existence
			//if (pipeType==1) start = holder;//If it is being changed to the start, saves it as the start
			holder.setPipeType(pipeType);//changes the type
			return true;
		}
		else return false;//if the condition above was not met, then it means that the operation was unsuccesfull
	}




	/**
	 * Prints a visual representation of the playboard
	 */
	@Override
	public String toString() {
		String print = "";
		Pipe holder = head, holder2 = head;
		for (int row=1; row<=rows; row++){
			for (int column=1; column<=columns; column++){
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
		if (current.getPipeType()==PipeType.START||current.getPipeType()==PipeType.INTERSECTION){
			switch (checkExistenceOf(PipeType.HORIZONTAL, last, current)){
				case "R":
					return simulate(current.getRight(), current);
				case "L":
					return simulate(current.getLeft(), current);
			}
			switch (checkExistenceOf(PipeType.VERTICAL, last, current)){
				case "D":
					return simulate(current.getDown(), current);
				case "U":
					return simulate(current.getUp(), current);
			}
		}
		else if (current.getPipeType()==PipeType.HORIZONTAL){
			switch (checkExistenceOf(PipeType.HORIZONTAL, last, current)){
				case "R":
					return simulate(current.getRight(), current);
				case "L":
					return simulate(current.getLeft(), current);
			}
			switch (checkExistenceOf(PipeType.INTERSECTION, last, current)){
				case "R":
					return simulate(current.getRight(), current);
				case "L":
					return simulate(current.getLeft(), current);
			}
			switch (checkExistenceOf(PipeType.END, last, current)){
				case "R":
					return nickName;
				case "L":
					return nickName;
			}
		}
		else if (current.getPipeType()==PipeType.VERTICAL){
			switch (checkExistenceOf(PipeType.VERTICAL, last, current)){
				case "D":
					return simulate(current.getDown(), current);
				case "U":
					return simulate(current.getUp(), current);
			}
			switch (checkExistenceOf(PipeType.INTERSECTION, last, current)){
				case "D":
					return simulate(current.getDown(), current);
				case "U":
					return simulate(current.getUp(), current);
			}
			switch (checkExistenceOf(PipeType.END, last, current)){
				case "D":
					return nickName;
				case "U":
					return nickName;
			}
		}
		return null;

	}


	/**
	 * Used to find where a certain pipe type is in relation to a pipe
	 * @param pType
	 * @param last
	 * @param current
	 * @return where it is or if it isn't
	 */
	public String checkExistenceOf(PipeType pType, Pipe last, Pipe current){
		if(current.getRight()!=null&&current.getRight()!=last&&current.getRight().getPipeType()==pType) return "R";
		else if(current.getLeft()!=null&&current.getLeft()!=last&&current.getLeft().getPipeType()==pType) return "L";
		else if(current.getDown()!=null&&current.getDown()!=last&&current.getDown().getPipeType()==pType) return "D";
		else if(current.getUp()!=null&&current.getUp()!=last&&current.getUp().getPipeType()==pType) return "U";
		else return "no";

	}

	public int pipeCount(){
		return pipeCount(1, 1, 0, head, head, 0);
	}

	public int pipeCount(int r, int c, int lastC, Pipe current, Pipe last, int sumVal){
		if(current.getPipeType()!=PipeType.END||current.getPipeType()!=PipeType.START||current.getPipeType()!=PipeType.NULL) sumVal++;
		else if (c>lastC){//This means the pipes are getting created to the right last one
			if (c!=columns) return pipeCount(r, c+1, c, current.getRight(), current, sumVal);
			else if(r!=rows) return pipeCount(r+1, c, lastC, current, last, sumVal);
		}
		else if (c<lastC){//This means the pipes are getting created to the left
			if (c!=columns) return pipeCount(r, c-1, c, current.getRight(), current, sumVal);
			else if(r!=rows) return pipeCount(r+1, c, lastC, current, last, sumVal);
		}

		else if (c==lastC){//This means its last pipe its abvove it
			if (c!=columns) return pipeCount(r, c+1, c, current.getRight(), current, sumVal);
			else if(r!=rows) return pipeCount(r, c-1, lastC, current, last, sumVal);
		}
		return 0;
	}

}