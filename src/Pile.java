import java.util.LinkedList;

public class Pile {

	private int pileWidth; // width of the bottom pile
	private int lastWidth; // width of the parcel at the top of the pile
	private int customerID; // customer ID of the pile, compare with parcel cID
	private int currentHeight; // compare with the trolley height
	
	private LinkedList<Parcel> parcels;
	
	public Pile() {
		pileWidth = 0;
		lastWidth = 0;
		customerID = 0;
		currentHeight = 0;
		
		parcels = new LinkedList<Parcel>();
	}
	
	public Pile(int pileWidth, int lastWidth, int customerID, int currentHeight) {
		this.pileWidth = pileWidth;
		this.lastWidth = lastWidth;
		this.customerID = customerID;
		this.currentHeight = currentHeight;
		
		parcels = new LinkedList<Parcel>();
	}
	
	
	// Getter and setter methods:
	public LinkedList<Parcel> getParcels()
	{
		return parcels;
	}
	
	public int getWidth()
	{
		return this.pileWidth;
	}
	
	public int getHeight()
	{
		return this.currentHeight;
	}
	
	public int getLastWidth()
	{
		return this.lastWidth;
	}
	
	public int getCustomerID()
	{
		return this.customerID;
	}
	
	public void setCurrentHeight(int height)
	{
		this.currentHeight = height;
	}
	
	public void setLastWidth(int topWidth)
	{
		this.lastWidth = topWidth;
	}
	
	
	@Override
	public String toString()
	{
		String msg = "\nPile width: " + this.pileWidth + "     " + "Last width: " + this.lastWidth + "     " + "Customer ID: " + this.customerID + "     " + "Current height: " + this.currentHeight + "     " + "Parcels in pile: " + this.parcels.size();
		return msg;
	}
	
} // end class
