
package kon.workout.remind.view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import kon.workout.remind.controller.TimingController;
import kon.workout.remind.model.interfaces.IExcersiseProvider;

/**
 * @author Konstantin Naryshkin
 */
public class StdOutUi implements Runnable
{
	private BufferedReader in;
	
	private IExcersiseProvider exersiseProvider;
	
	private TimingController timing;
	
	/**
	 * TODO
	 * 
	 * @param excersiseProvider
	 * @param timing
	 */
	public StdOutUi(IExcersiseProvider excersiseProvider, TimingController timing)
	{
		this.exersiseProvider = excersiseProvider;
		
		this.in = new BufferedReader(new InputStreamReader(System.in));
		
		this.timing = timing;
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
		this.timing.scheduleNext(this);
		System.out.println("Next workout at " + this.timing.getNextRun());
		System.out.flush();
	}
	
}
