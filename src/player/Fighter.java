package player;

public interface Fighter
{
	/**
	 * The six vital statistics of a Fighter character .
	 */
	
	public enum Stat
	{
		STRENGHT,INTELLIGENCE,WISDOM,DEXTERITY,CONSTITUTION,CHARISMA
		
	}
	public int getStat(Stat stat);
	public int getStatModifier(Stat stat);
	
}