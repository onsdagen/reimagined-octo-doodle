import java.util.Observable;

public abstract class SimState extends Observable{
	private boolean isStopped = false;
	protected long time;
	
	public SimState() {
		time = System.currentTimeMillis();
	}
	
	/**
	 * Stops the current simulation
	 */
	public void stop() {
		if(!isStopped) {
			time = System.currentTimeMillis() - this.time;
			isStopped = true;
		}
	}
	/**
	 * Get current time in simulation, or total runtime for the whole simulation
	 * @return current time or final runtime if stopped
	 */
	public long getTime() {
		return getStopStatus() ? time : System.currentTimeMillis() - this.time;
	}
	/**
	 * Returns the current status, if the simulation should be interrupted or not.
	 * @return if the process is stopped or not
	 */
	public boolean getStopStatus() {
		return isStopped;
	}
	/**
	 * Notify all observers
	 */
	public void notifyObs() {
		setChanged();
		notifyObservers();
	}
	
	
}
