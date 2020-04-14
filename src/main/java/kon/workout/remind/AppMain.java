package kon.workout.remind;

import java.time.Duration;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import kon.workout.remind.controller.TimingController;
import kon.workout.remind.model.hardcoded.HardcodedExcersiseProvider;
import kon.workout.remind.view.cli.StdOutUi;
import kon.workout.remind.view.gui.JavaFxUi;

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
		TimingController timing = new TimingController(Duration.ofHours(1));
		final HardcodedExcersiseProvider excersiseProvider = new HardcodedExcersiseProvider();
		new StdOutUi(excersiseProvider, timing);
		
		new JavaFxUi(excersiseProvider, timing).start();
	}
	
}
