package kon.workout.remind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kon.workout.remind.model.interfaces.IExcersiseProvider;

/**
 *
 * @author Konstantin Naryshkin
 */
public class StdOutAlert implements Runnable
{
	private ScheduledThreadPoolExecutor executor;
	private TemporalAmount period;
	private BufferedReader in;
	private IExcersiseProvider exersiseProvider;
	/**
	 * TODO
	 * @param excersiseProvider 
	 */
	public StdOutAlert(IExcersiseProvider excersiseProvider, ScheduledThreadPoolExecutor executor, TemporalAmount period)
	{
		this.executor = executor;
		this.period = period;
		
		this.exersiseProvider = excersiseProvider;
		
		this.in = new BufferedReader(new InputStreamReader(System.in));
		scheduleNext();
	}
	
	@Override
	public void run()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("WORKOUT");
		System.out.println(this.exersiseProvider.next().getName());
		System.out.println("--------------------------------------------------");
		System.out.flush();
		try
		{
			this.in.readLine();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scheduleNext();
	}

	/**
	 * Schedule the next workout
	 */
	private void scheduleNext()
	{
		System.out.println("Next workout at " + ZonedDateTime.now().plus(period));
		System.out.flush();
		this.executor.schedule(this, this.period.get(ChronoUnit.SECONDS), TimeUnit.SECONDS);
	}
	
}
