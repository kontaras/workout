package kon.workout.remind.model.hardcoded;

import kon.workout.remind.model.interfaces.IExcersise;

/**
 *
 * @author Konstantin Naryshkin
 */
public class PojoExcersise implements IExcersise
{
	private final String name;
	
	/**
	 * 
	 */
	public PojoExcersise(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
}
