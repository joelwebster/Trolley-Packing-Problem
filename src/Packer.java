import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Packer {
	
	Random rand = new Random();
	private int custIDMin = 1;
	private int custIDMax = 3;
	private int randomNum;
	private int howManyParcels = 10;
	private boolean parcelAdded = false; // boolean to indicate when to move onto the next parcel
	
	private int minWidth = 1;
	private int minHeight = 1;
	private int maxWidth = 2000;
	private int maxHeight = 2000;
	private int randomWidth;
	private int randomHeight;
	
	private int trolleysUsed = 0; // count trolleys used (not empty)
	
	private static LinkedList<Parcel> initialParcels = new LinkedList<Parcel>(); // store initially created parcels
	private static LinkedList<Trolley> trolleys = new LinkedList<Trolley>(); // store trolleys
	
	// Methods:
	
	// Count trolleysUsed
	public int countTrolleysUsed()
	{
		trolleysUsed = 0;
		ListIterator<Trolley> tItr = trolleys.listIterator();
		while(tItr.hasNext())
		{
			Trolley currentTrolley = tItr.next();
			if(currentTrolley.getWidthLeft()!=currentTrolley.getWidth())
			{
				trolleysUsed++;
			}
		}
		return trolleysUsed;
	}
	
	public int getHowManyParcels()
	{
		return howManyParcels;
	}
	
	// Create parcels
	public void createRandomParcels()
	{
		for (int i=0; i<howManyParcels; i++)
		{
			// Generate a customerID between 1 and 3
			randomNum = rand.nextInt(custIDMax - custIDMin + 1) + custIDMin;
			randomWidth = rand.nextInt(maxWidth - minWidth + 1) + minWidth;
			randomHeight = rand.nextInt(maxHeight - minHeight + 1) + minHeight;
			
			// Populate list with parcels
			initialParcels.add(new Parcel(randomHeight,randomWidth,randomNum));
		}
	}
	
	// Create trolleys
	public void createTrolleys()
	{
		for (int i=0; i<howManyParcels; i++)
		{
			// Populate list with trolleys
			trolleys.add(new Trolley());
		}
	}
	

	
	// clear initialParcels and trolleys
	public void clearPT()
	{
		initialParcels.clear();
		trolleys.clear();
	}
	
	public void clearTrolleys()
	{
		trolleys.clear();
	}
	
	
	// Offline packing algorithm
	public void offlineAlgorithm()
	{
		// sort initialParcels by width, largest first
		Collections.sort(initialParcels);
		
		// call the online algorithm
		onlineAlgorithm();
	}
	
	
	// >>> Online packing algorithm
	public void onlineAlgorithm()
	{
		ListIterator<Parcel> parcelsItr = initialParcels.listIterator();
		
		while(parcelsItr.hasNext()) {
			parcelAdded = false; // reset parcelAdded boolean for the new parcel
			
			Parcel currentParcel = parcelsItr.next(); // get parcel
			ListIterator<Trolley> trolleysItr = trolleys.listIterator();
			
			// Check where to place the parcel
			while(parcelAdded==false)
			{
				
			if(trolleysItr.hasNext())
			{
				Trolley currentTrolley = trolleysItr.next(); // get trolley/iterate to next trolley
				LinkedList<Pile> piles = currentTrolley.getPiles(); // get piles in currentTrolley
				ListIterator<Pile> pilesItr = piles.listIterator(); // create iterator for piles
				
				if(!pilesItr.hasNext()) // if there are no piles in the trolley
				{
					// create a new pile 
					Pile newPile = new Pile(currentParcel.getWidth(),currentParcel.getWidth(),currentParcel.getCustNo(),currentParcel.getHeight());
					// add the parcel to the pile
					newPile.getParcels().add(currentParcel); // add parcel to pile's parcel list
					// add pile to trolley's list
					piles.add(newPile);
					// update trolley widthLeft
					currentTrolley.setWidthLeft(newPile.getWidth()); // update remaining space in trolley
					currentTrolley.setCustArray(currentParcel.getCustNo()); // set trolley array id
					
					parcelAdded=true;
					// break;
					
				} else if (pilesItr.hasNext()) // if there are piles in the trolley
				{
					Pile currentPile = pilesItr.next(); // get first pile
					// if the parcel fits current pile
					if(currentParcel.getWidth()<=currentPile.getLastWidth() && currentPile.getHeight()+currentParcel.getHeight()<=currentTrolley.getHeight() && currentParcel.getCustNo()==currentPile.getCustomerID())
					{
						// add parcel to current pile
						currentPile.getParcels().add(currentParcel); // add parcel to pile's parcel list
						// update pile values
						currentPile.setCurrentHeight(currentPile.getHeight() + currentParcel.getHeight());
						currentPile.setLastWidth(currentParcel.getWidth());
						
						parcelAdded=true;
						
					}
					else {
					// while the parcel doesn't fit current pile
					while(currentParcel.getWidth()>currentPile.getLastWidth() || currentPile.getHeight()+currentParcel.getHeight()>currentTrolley.getHeight() || currentParcel.getCustNo()!=currentPile.getCustomerID())
					{
						
						if(pilesItr.hasNext())
						{
							currentPile = pilesItr.next(); // iterate to next pile
							// check if parcel fits
							if(currentParcel.getWidth()<=currentPile.getLastWidth() && currentPile.getHeight()+currentParcel.getHeight()<=currentTrolley.getHeight() && currentParcel.getCustNo()==currentPile.getCustomerID())
							{
								// add parcel to current pile
								currentPile.getParcels().add(currentParcel); // add parcel to pile's parcel list
								// update pile values
								currentPile.setCurrentHeight(currentPile.getHeight() + currentParcel.getHeight());
								currentPile.setLastWidth(currentParcel.getWidth());
								
								parcelAdded=true;
								break;
							}
							
						} else if (!pilesItr.hasNext()){ // if all piles have been checked
							
							// is there room for another valid pile in this trolley?
							if(currentTrolley.getWidthLeft()>=currentParcel.getWidth() && currentTrolley.idValid(currentParcel.getCustNo())) // there is room for pile in this trolley
							{
								// create a new pile 
								Pile newPile = new Pile(currentParcel.getWidth(),currentParcel.getWidth(),currentParcel.getCustNo(),currentParcel.getHeight());
								// add the parcel to the pile
								newPile.getParcels().add(currentParcel); // add parcel to pile's parcel list
								// add pile to trolley's list
								piles.add(newPile);
								// update trolley widthLeft
								currentTrolley.setWidthLeft(newPile.getWidth()); // update remaining space in trolley
								currentTrolley.setCustArray(currentParcel.getCustNo());
								
								parcelAdded=true;
								break;
								
							} else
								{
									// iterate to next trolley by starting algorithm again
									break;
								}
						}
					}
					}
				}
			}
		}
	}
	}
	
	// Print trolleys
	public void printTrolleys()
	{
		ListIterator<Trolley> tItr = trolleys.listIterator();
		int trolleyCounter = 0;
		
		while(tItr.hasNext()) // for each trolley
		{
			Trolley printTrolley = tItr.next();
			trolleyCounter++;
			System.out.println("\nTrolley Number: " + trolleyCounter);
			System.out.println(printTrolley); // print trolley
			
			// print piles in trolley
			LinkedList<Pile> printPiles = printTrolley.getPiles();
			ListIterator<Pile> pItr = printPiles.listIterator();
			
			while(pItr.hasNext())
			{
				// System.out.println("Printing Piles"); // test
				
				Pile printPile = pItr.next();
				System.out.println(printPile); // print pile
				
				LinkedList<Parcel> printParcels = printPile.getParcels();
				ListIterator<Parcel> paItr = printParcels.listIterator();
				
				while(paItr.hasNext())
				{
					Parcel printParcel = paItr.next();
					System.out.println(printParcel); // print parcels
				}
			}
			System.out.println("\n------------------------------------------------------------------------------");
		}
	}
	
	// Print initial parcels (for correctness testing)
	public void printInitialParcels()
	{
		ListIterator<Parcel> pItr = initialParcels.listIterator();
		
		while(pItr.hasNext())
		{
			Parcel printParcel = pItr.next();
			System.out.println(printParcel);
		}
	}
	
} // end class