
package kon.workout.remind.controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Konstantin Naryshkin
 */
public class TimingController
{
	ScheduledThreadPoolExecutor executor;
	
	private TemporalAmount frequency;
	
	private ZonedDateTime nextRun;
	
	private ScheduledFuture<?> nextRunResult = null;
	
	/**
	 * TODO
	 * 
	 * @param duration
	 */
	public TimingController(TemporalAmount duration)
	{
		this.executor = new ScheduledThreadPoolExecutor(0);
		this.frequency = duration;
	}
	
	/**
	 * Schedule the next workout
	 */
	synchronized public void scheduleNext(Runnable callback)
	{
		if (this.nextRunResult != null) {
			this.nextRunResult.cancel(false);
		}
		this.nextRun = ZonedDateTime.now().plus(this.frequency);
		this.nextRunResult = this.executor.schedule(callback,
				this.frequency.get(ChronoUnit.SECONDS), TimeUnit.SECONDS);
	}
	
	/**
	 * Getter for nextRun
	 *
	 * @return the nextRun
	 */
	public ZonedDateTime getNextRun()
	{
		return nextRun;
	}
}
