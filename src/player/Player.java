package player;
/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;*/
import java.io.Serializable;

/**
 * 
 * An instance of this class will be a fighter character in the game.
 *
 */
public class Player implements Fighter, Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    final private int[] stats = new int[Stat.values().length];
	private String name;
	private int level;
	
	public Player()
	{
	    System.out.println("Setting the ability scores randomly by rolling 3d6...");
		for( int i=0; i<stats.length; i++ )
			stats[i] = d10.d(3,6);
	}
	public int getStat( Stat stat )
	{
		return stats[stat.ordinal()];
	}
	public int getStatModifier( Stat stat )
	{
		int value = getStat(stat);
		/*if( value <= 3 )
			return -3;
		else if( value <= 5 )
			return -2;
		else if( value <= 8 )
			return -1;
		else if( value <= 12 )
			return 0;
		else if( value <= 15 )
			return 1;
		else if( value <= 17 )
			return 2;
		else if( value >= 18 )*/
			return value;
	}
	
	/**
	 * @return the name of the player
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name of the player
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLevel(int newLevel){
	    this.level =  newLevel;
	}
	
	public int getLevel(){
	    return this.level;
	}
	
	    
	}

		
	

	
	
	
	
	
	
	
	
	
	
	
		
		
	


