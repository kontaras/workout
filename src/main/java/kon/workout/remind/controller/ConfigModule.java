
package kon.workout.remind.controller;

import java.time.Duration;

import com.google.inject.AbstractModule;

import kon.workout.remind.model.hardcoded.HardcodedExcersiseProvider;
import kon.workout.remind.model.interfaces.IExcersiseProvider;

/**
 * @author Konstantin Naryshkin
 */
public class ConfigModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IExcersiseProvider.class).toInstance(new HardcodedExcersiseProvider());
		bind(TimingController.class)
				.toInstance(new TimingController(Duration.ofHours(1)));
	}
}
