package kon.workout.remind.model.hardcoded;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kon.workout.remind.model.interfaces.IExcersise;
import kon.workout.remind.model.interfaces.IExcersiseProvider;

/**
 *
 * @author Konstantin Naryshkin
 */
public class HardcodedExcersiseProvider implements IExcersiseProvider
{
	private final List<IExcersise> excersises;
	private static final Random rng = new Random();
	
	/**
	 * 
	 */
	public HardcodedExcersiseProvider()
	{
		excersises = new ArrayList<>();
		excersises.add(new PojoExcersise("Squeeze"));
		excersises.add(new PojoExcersise("Push-up"));
		excersises.add(new PojoExcersise("Sit-up"));
		excersises.add(new PojoExcersise("Jumping jack"));
		excersises.add(new PojoExcersise("Leg lifts"));
		excersises.add(new PojoExcersise("Stomach crunch"));
		excersises.add(new PojoExcersise("Dips"));
	}
	
	@Override
	public IExcersise next()
	{
		int pick = rng.nextInt(excersises.size());
		return excersises.get(pick);
	}
	
}
