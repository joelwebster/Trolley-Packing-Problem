import java.util.LinkedList;

public class Trolley {
	
	private int height; // trolley height
	private int width; // trolley width
	private int widthLeft; // width left in trolley
	
	private int[] custIDs = new int[2]; // max of 2 customerIDs in one trolley
	
	private LinkedList<Pile> piles;
		
	public Trolley() {
		height = 2000;
		width = 2000;
		widthLeft = width;
		custIDs = new int[]{0,0};
		
		piles = new LinkedList<Pile>();
	}
		
	@Override
	public String toString()
	{
		String msg = "Trolley Height: " + this.height + "     " + "Trolley Width: " + this.width + "     " + "Width Left: " + this.widthLeft + "     " + "Piles in trolley: " + this.piles.size();
		return msg;
	}
	
	public LinkedList<Pile> getPiles()
	{
		return piles;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getWidthLeft()
	{
		return this.widthLeft;
	}
	
	public void setWidthLeft(int newPileWidth)
	{
		this.widthLeft = widthLeft - newPileWidth;
	}
	
	public int[] getCustArray() {
		return custIDs;
	}
	
	public void setCustArray(int id) {
		if (custIDs[0] == 0) {			
			custIDs[0] = id;
		}
		else if (custIDs[1] == 0){
			custIDs[1] = id;
		}
		else {
			return;
		}
	}
	
	public boolean idValid(int id) {
		if (custIDs[0] == id || (custIDs[1] == id || custIDs[1] == 0)) {
			return true;
		}
		else {
			return false;
		}
	}
	
} // end class
