package mainSim;

import java.util.concurrent.ThreadLocalRandom;

import events.CloseEvent;
import events.Event;
import events.EventQueue;
import events.StartEvent;
import events.StopEvent;
import state.State;

public class Optimize {

	private double closeTime;
	private int amountOfRegisters = Integer.MAX_VALUE;
	private int maxCustomerAmount;
	private double lambda;
	private double minK;
	private double maxK;
	private double minP;
	private double maxP;
	private long seed;
	
	
	private int maxCount;
	
	private int missedCustomers;

	public Optimize(String[] args) {
		this.closeTime = Double.parseDouble(args[0]);
		this.amountOfRegisters = 1;
		this.maxCustomerAmount = Integer.parseInt(args[2]);
		this.lambda = Double.parseDouble(args[3]);
		this.minK = Double.parseDouble(args[4]);
		this.maxK = Double.parseDouble(args[5]);
		this.minP = Double.parseDouble(args[6]);
		this.maxP = Double.parseDouble(args[7]);
		this.seed = Long.parseLong(args[8]);
		
		this.maxCount = 100;
	}
	
	public State metod1(){
		State state = new State(maxCustomerAmount, amountOfRegisters,
				lambda, minK, maxK, minP, maxP, this.seed, closeTime);
		EventQueue eventQueue = new EventQueue(state);
		Event start = new StartEvent(0, eventQueue, state);
		Event close = new CloseEvent(closeTime, eventQueue, state);
		Event stop = new StopEvent(99, eventQueue, state);
		
		//SupermarketView view = new SupermarketView(state);
		
		eventQueue.addEvent(close);
		eventQueue.addEvent(start);
		eventQueue.addEvent(stop);

		Simulator simulator = new Simulator(eventQueue, state);
		simulator.run();
		
		return state;
	}
	
	public int metod2(){
		int counter = 0;
		int amountOfRegisters = 1;
		boolean isReset = true;
	
		while (counter < this.maxCount) {
			State state = metod1();
				
			/* --------------------------- */
				
			if (isReset == true) {
				missedCustomers = state.getMissedCustomers();
				isReset = false;
			}
			/* --------------------------- */

			if (missedCustomers < state.getMissedCustomers()) {
				counter = 0;
				isReset = true;
				amountOfRegisters++;
			} 
		}
		
		return amountOfRegisters;
	}
	
	public int metod3(){
		int tempMinRegisters = 0;
		int counter = 0;
		while(true){
			counter++;
			this.seed = randSeed();
			tempMinRegisters = metod2();
			
			if(tempMinRegisters < this.amountOfRegisters){
				this.amountOfRegisters = tempMinRegisters;
				counter = 0;
			}
			
			if(counter == 100){
				break;
			}
		}
		return this.amountOfRegisters;
	}


	private long randSeed() {
		int seed = (int) ThreadLocalRandom.current().nextDouble(0, 1000000);
		return seed;
	}
}