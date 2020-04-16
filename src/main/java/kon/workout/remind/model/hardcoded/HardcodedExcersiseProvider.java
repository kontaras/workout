package kon.workout.remind.model.hardcoded;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

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
	@Inject
	public HardcodedExcersiseProvider()
	{
		excersises = new ArrayList<>();
		excersises.add(new PojoExcersise("Squeezes"));
		excersises.add(new PojoExcersise("Push-ups"));
		excersises.add(new PojoExcersise("Sit-ups"));
		excersises.add(new PojoExcersise("Jumping jacks"));
		excersises.add(new PojoExcersise("Leg lifts"));
		excersises.add(new PojoExcersise("Stomach crunches"));
		excersises.add(new PojoExcersise("Dips"));
		excersises.add(new PojoExcersise("Burpies"));
		excersises.add(new PojoExcersise("Ab blasters"));
		excersises.add(new PojoExcersise("Side leg lifts"));
	}
	
	@Override
	public IExcersise next()
	{
		int pick = rng.nextInt(excersises.size());
		return excersises.get(pick);
	}
	
}
