package model;
/**
 * Manages the playboard of the game.
 */
public class Grid {

	private Pipe head;
	private int columns;
	private int rows;
	private Pipe start;

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
	public Grid(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		head = create(1,1,0,new Pipe(6, "1,1"), null);
	}

	/**
	 * Initializes the quadruple linked list that is used to store the playboard, it creates rows in an "s" pattern
	 * @param r Must be 1 when first called
	 * @param c Must be 1 when first called
	 * @param lastC Must be 0 when first called
	 * @param current Must be a new pipe type object when first called
	 * @param last	Must be null when first called
	 * @return
	 */
	public Pipe create(int r, int c, int lastC, Pipe current, Pipe last) {
		if (r==1&&c==1){//The first object to be created, the top right corner
			current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current));// creates a new pipe object and sets it as its right, it acomplishes this by calling the method create again.
		}
		else if (r==1&&c!=columns){//The case true for all objects part of the first row that aren't the first or the last
			current.setLeft(last);//As the last is set to have this object as its right, the last is set as its left
			current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current));//sets its right as a new instance of create that will return a pipe
		}
		else if (c==columns&&c!=lastC&&r==1){//The case for the leftmost object of the first row.
			current.setLeft(last);
			current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current));//As there are not suposed to be objects to the right beyond this one, the pipe found below this pipe is set instead, efectively "going down."
		}
		else if (c==lastC&&c!=1){//The case for the leftmost pipe if the last pipe was above it, and it was a right-flowing row
			current.setUp(last);//As the last pipe was above it, it is set as its upper pipe
			current.setRight(create(r, c-1, c, new Pipe(6, r+","+(c-1)), current));//Because there are not suposed to be any more pipes to teh right of this pipe, and its last pipe was the en of its upper row, then it sets its left to continue the method
		}
		else if (c<lastC&&c!=1){//The case for any pipe if the direction of creation is left, and its not an extreme
			current.setRight(last);
			current.setUp(current.getRight().getUp().getLeft());//As it has a pipe above it, and no direct way to connect to it, it travels throgh the already conected right, its upper pipe, ans that pipe's left, with that pipe being above, that is set as its upper pipe
			current.getUp().setDown(current);
			current.setLeft(create(r, c-1, c, new Pipe(6, r+","+(c-1)), current));
		}
		else if (c==1&&c!=lastC&&r!=rows){//The case for the rightmost pipe of a row with left flowing creation
			current.setRight(last);
			current.setUp(current.getRight().getUp().getLeft());
			current.getUp().setDown(current);
			current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current));//as there are not suposed to be any pipes to the left beyond this point, it sets its downward pipe
		}
		else if (c==1&&c==lastC){//The case for the rightmost pipe that is not part of the first lane and which last pipe was above it
			current.setUp(last);
			current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current));//As there arenot suposed to be pipes to the left, the creation flow is set to the right
		}
		else if (c>lastC&&c!=columns){//The case for any pipe set in a right flowing row that is not the first one
			current.setLeft(last);
			current.setUp(current.getLeft().getUp().getRight());
			current.getUp().setDown(current);
			current.setRight(create(r, c+1, c, new Pipe(6, r+","+(c+1)), current));
		}
		else if (c==columns&&c!=lastC&&r!=rows){//the case for the leftmost pipe in a right flowing row that isnt the first one
			current.setLeft(last);
			current.setUp(current.getLeft().getUp().getRight());
			current.getUp().setDown(current);
			current.setDown(create(r+1,c,c,new Pipe(6, (r+1)+","+c),current));
		}

		//The cases for the extemes in the last row
		else if(c==columns&&c!=lastC&&r==rows){
			current.setLeft(last);
			current.setUp(current.getLeft().getUp().getRight());
			current.getUp().setDown(current);
		}
		else if (c==1&&c!=lastC&&r==rows){
			current.setRight(last);
			current.setUp(current.getRight().getUp().getLeft());
			current.getUp().setDown(current);
		}

		//Theoretically unreachable condition, used to signat that something went extremelly wrong
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
		for (int c=1; c<column; c++){//to move to teh column
			if(holder!=null) holder = holder.getRight();
		}
		if (holder!=null){//to check for its existence
			if (pipeType==1) start = holder;//If it is being changed to the start, saves it as the start
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
		System.out.println(print);
		return print;
	}

	/**
	 * Checks if the conections made by the player are compatible withs each other, used to determine victory
	 * @param current Must be the starting position (pipeType 1(PipeType.Start))
	 * @param last
	 * @return A boolean informing the program of the compatibility of the pipes put together by the player
	 */
	public boolean simulate(Pipe current, Pipe last){
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
					return true;
				case "L":
					return true;
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
					return true;
				case "U":
					return true;
			}
		}
		return false;

	}

	public String checkExistenceOf(PipeType pType, Pipe last, Pipe current){
		if(current.getRight()!=null&&current.getRight()!=last&&current.getRight().getPipeType()==pType) return "R";
		else if(current.getLeft()!=null&&current.getLeft()!=last&&current.getLeft().getPipeType()==pType) return "L";
		else if(current.getDown()!=null&&current.getDown()!=last&&current.getDown().getPipeType()==pType) return "D";
		else if(current.getUp()!=null&&current.getUp()!=last&&current.getUp().getPipeType()==pType) return "U";
		else return "no";

	}

}