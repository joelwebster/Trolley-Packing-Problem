
public class Parcel implements Comparable<Parcel> {
	
	private int height;
	private int width;
	private int custNo;
	
	// constructor for Parcel objects
	public Parcel() {
		height = 0;
		width = 0;
		custNo = 0;
	}
	
	public Parcel(int height, int width, int custNo) {
		this.height = height;
		this.width = width;
		this.custNo = custNo;
	}
	
	@Override
	public String toString()
	{
		String msg = "Parcel Height: " + this.height + "     " + "Parcel Width: " + this.width + "     " + "Customer No: " + this.custNo;
		return msg;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getCustNo()
	{
		return this.custNo;
	}

	public int compareTo(Parcel otherParcel)
	{
		Parcel other = otherParcel;
		if(width > other.width) return -1;
		if(width == other.width) return 0;
		return 1;
	}
	
} // end class