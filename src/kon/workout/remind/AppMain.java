package kon.workout.remind;

import java.time.Duration;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import kon.workout.remind.model.hardcoded.HardcodedExcersiseProvider;

/**
 *
 * @author Konstantin Tarashchanskiy
 */
public class AppMain
{
	
	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(0);
		new StdOutAlert(new HardcodedExcersiseProvider(), executor, Duration.ofHours(1));
	}
	
}
