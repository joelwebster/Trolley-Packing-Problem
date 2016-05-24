
public class Test {
	
	private static int totalTests = 1;
	private static long totalTrolleysUsedOnline;
	private static long totalTrolleysUsedOffline;
	private static long totalTimeTakenOnline;
	private static long totalTimeTakenOffline;
	
	public static void main (String [] args)
	{
		// Instance of Packer class
		Packer packer = new Packer();
		
		for(int i=0;i<totalTests;i++) // do a number of tests for both online and offline algorithms
		{
			
		// Clear parcels and trolleys before test runs
		packer.clearPT();
		
		// Create parcels and trolleys
		packer.createRandomParcels();
		//packer.printInitialParcels();
		packer.createTrolleys();
		
		// Test online algorithm
		long onlineStartTime = System.currentTimeMillis();
		packer.onlineAlgorithm();
		//packer.printTrolleys();
		long onlineEndTime = System.currentTimeMillis();
		
		long timeTakenOnline = onlineEndTime - onlineStartTime;
		int trolleysUsedOnline = packer.countTrolleysUsed();
				
		totalTimeTakenOnline = totalTimeTakenOnline + timeTakenOnline; // count up time taken by online method calls
		totalTrolleysUsedOnline = totalTrolleysUsedOnline + trolleysUsedOnline; // count up trolleys used by online method calls
		
		// print trolleys
		//packer.printTrolleys();
		
		// Clear the parcels placed by the online algorithm
		packer.clearTrolleys();
		// Create fresh trolleys for the offline algorithm
		packer.createTrolleys();
		
		// Test offline (sorted) algorithm
		long offlineStartTime = System.currentTimeMillis();
		packer.offlineAlgorithm();
		//packer.printTrolleys();
		long offlineEndTime = System.currentTimeMillis();
		
		long timeTakenOffline = offlineEndTime - offlineStartTime;
		int trolleysUsedOffline = packer.countTrolleysUsed();
		
		totalTimeTakenOffline = totalTimeTakenOffline + timeTakenOffline; // count time taken by offline method calls
		totalTrolleysUsedOffline = totalTrolleysUsedOffline + trolleysUsedOffline; // count up trolleys used by offline method calls
		
		// Print trolleys
		//packer.printTrolleys();
		
		}
		
		// Calculate test result averages
		double averageTimeOnline = totalTimeTakenOnline/totalTests;
		double averageTrolleysOnline = totalTrolleysUsedOnline/totalTests;
		double averageTimeOffline = totalTimeTakenOffline/totalTests;
		double averageTrolleysOffline = totalTrolleysUsedOffline/totalTests;
		
		// Print test result averages
		System.out.println("When running " + totalTests + " tests of both algorithms, placing " + packer.getHowManyParcels() + " parcels:\n");
		
		System.out.println("Average time taken online: " + (int)averageTimeOnline + " milliseconds.");
		System.out.println("Average number of trolleys used online: " + (int)averageTrolleysOnline);
		System.out.println("Average time taken offline: " + (int)averageTimeOffline + " milliseconds.");
		System.out.println("Average number of trolleys used offline: " + (int)averageTrolleysOffline);
		
		// Print efficiency differences between algorithms
		System.out.println("\nThe offline algorithm, on average, took " + (int)(((averageTimeOnline-averageTimeOffline)/averageTimeOffline)*100) + "% less time than the online algorithm.");
		System.out.println("The offline algorithm, on average, used " + (int)(((averageTrolleysOnline-averageTrolleysOffline)/averageTrolleysOffline)*100) + "% less trolleys than the online algorithm.");
		
	}
} // end class
