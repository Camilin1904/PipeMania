package model;
/**
 * Basic unit of the playboard of the game
 */
public class Pipe {

	private String pipeId;
	private PipeType pipeType;
	private Pipe up;
	private Pipe down;
	private Pipe left;
	private Pipe right;

	/**
	 * 
	 * @param pipeType
	 */
	public Pipe(int pipeType, String pipeId) {
		this.pipeId=pipeId;
		switch (pipeType){
			case 1:
				this.pipeType = PipeType.START;
				break;
			case 2:
				this.pipeType = PipeType.END;
				break;
			case 3:
				this.pipeType = PipeType.HORIZONTAL;
				break;
			case 4:
				this.pipeType = PipeType.VERTICAL;
				break;
			case 5:
				this.pipeType = PipeType.INTERSECTION;
				break;
			case 6:
				this.pipeType = PipeType.NULL;
				break;
			default:
				System.out.println("Non existent pipe type found");
				break;
		}
	}

	/**
	 * Returns a visual representation of the pipe 
	 */
	@Override
	public String toString() {
		String hold = "";
		switch (pipeType){
			case START:
				hold = " S ";
				break;
			case END:
				hold =  " E ";
				break;
			case HORIZONTAL:
				hold = " = ";
				break;
			case VERTICAL:
				hold = "| |";
				break;
			case INTERSECTION:
				hold = " O ";
				break;
			case NULL:
				hold = " X ";
				break;

		}
		return hold;
	}

	public String getPipeId() {
		return this.pipeId;
	}

	public Pipe getDown() {
		return down;
	}
	public Pipe getLeft() {
		return left;
	}
	public Pipe getRight() {
		return right;
	}
	public Pipe getUp() {
		return up;
	}
	public void setDown(Pipe down) {
		this.down = down;
	}
	public void setLeft(Pipe left) {
		this.left = left;
	}
	public void setPipeType(int pipeType) {
		switch (pipeType){
			case 1:
				this.pipeType = PipeType.START;
				break;
			case 2:
				this.pipeType = PipeType.END;
				break;
			case 3:
				this.pipeType = PipeType.HORIZONTAL;
				break;
			case 4:
				this.pipeType = PipeType.VERTICAL;
				break;
			case 5:
				this.pipeType = PipeType.INTERSECTION;
				break;
			case 6:
				this.pipeType = PipeType.NULL;
				break;
			default:
				System.out.println("Non existent pipe type found");
				break;
		}
	}
	public void setRight(Pipe right) {
		this.right = right;
	}
	public void setUp(Pipe up) {
		this.up = up;
	}

	public PipeType getPipeType() {
		return pipeType;
	}
}