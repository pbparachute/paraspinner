package paraSpinner;

abstract class Condition {
	private int timeOut = 0;

	public Condition(int timeOut) {
		this.timeOut = timeOut;
	}

	public abstract boolean isValid();

	public int getTimeout() {
		return timeOut;
	}
}
