
public class State extends SimState{
	
	private int maximumCostumerAmount;
	private int registerAmount;
	private int openRegisters;
	private int currentCostumerCount;

	//Keeps track of total costumer count;
	private int queueAmount; //Total number of people who have been in a queue
	private int missedCostumers; //Total number of missed costumers
	
	//Time tracking
	private long timeRegistrers;
	private long timeCostumerQueue;
	
	/**
	 * 
	 * @param maxCostumerAmount - set max costumers in store
	 * @param registerAmount - set amount of registers in total
	 */
	public State(int maxCostumerAmount, int registerAmount) {
		super();
		this.maximumCostumerAmount = maxCostumerAmount;
		this.registerAmount = registerAmount;
	}
	/**
	 * Call when payEvent is done, notifies that one register is now open.
	 */
	public void notifyFreeRegister() {
		if(openRegisters == registerAmount) {
			openRegisters = registerAmount;
		} else {
			openRegisters++;
		}
	}
	
	/**
	 * Checks if a Register is open.
	 * @return true if a Register is open, false otherwise.
	 */
	public boolean isRegisterOpen() {
		return openRegisters > 0 ? true : false;
	}
	/**
	 * Checks if the store is currently full or not
	 * @return true if store is full, false otherwise.
	 */
	public boolean isStoreFull() {
		return currentCostumerCount == maximumCostumerAmount ? true : false;
	}
	/**
	 * Call when a costumer enters the store, updates current number of costumers in store.
	 */
	public void notifyCostumerEnteredStore() {
		if(currentCostumerCount == maximumCostumerAmount) {
			currentCostumerCount = maximumCostumerAmount;
		} else {
			currentCostumerCount++;
		}
	}

	/**
	 * Adds a costumer to queueStatistics.
	 * (Should be called when a costumers needs to queue)
	 */
	public void addCostumerQueue() {
		queueAmount +=1;
	}
	/**
	 * Adds a missed costumer to the missedCostumer statistics.
	 * (Should be called when a person leaves the store without paying)
	 */
	public void addMissedCostumer() {
		missedCostumers += 1;
	}
	/**
	 * 
	 * @return total number of costumers that have queued.
	 */
	public int getQueueAmount() {
		return queueAmount;
	}
	/**
	 * 
	 * @return total number of costumers leaving without paying.
	 */
	public int getMissedCostumers() {
		return missedCostumers;
	}
	/**
	 * 
	 * @return total time registers are used in milliseconds since store opened
	 */
	//TODO used with Time object, in TIme update this variable
	public long getTimeRegistrers() {
		return timeRegistrers;
	}
	/**
	 * 
	 * @return total time costumers have been in queue since store opened.
	 */
	//TODO used with TIme object, in time update this variable
	public long getTimeCostumerQueue() {
		return timeCostumerQueue;
	}
	
}
