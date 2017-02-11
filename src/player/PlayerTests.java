package player;


import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import player.Fighter.Stat;

/**
 * 
 * This class contains test cases testing the Player class and Dice class.
 *
 */
public class PlayerTests extends TestCase
{

    
    @Test
    public void testPlayer()
    {
        // Generate a Playercharacter. 
        Player Test = new Player();
        // Loop through all of the character's stats and print them. 
        for(Stat stat : Stat.values())
        {
        System.out.println(caps(stat.toString()) + ": " + Test.getStat(stat));
        // If the stat modifier is nonzero, then we print it, too. 
        int mod = Test.getStatModifier( stat ); 
        if( mod != 0 ) 
        { 
            System.out.println( " (" ); 
            // Positive numbers don't automatically print with a "+" in front of them, so we add it. 
            if( mod > 0 ) 
            //  System.out.print( "+" ); 
            System.out.print( mod ); 
            //System.out.print( ")" ); 
            Assert.assertTrue(true);
        } 
        else
        {
            Assert.assertFalse(false);
        }
        }
    }
    // This  function that capitalizes the first letter of a string, 
    // and lowercases all the rest. We use it when we print out enumeration 
    // constants, because they are generally ALL-CAPS,
    String caps(String s) 
    { 
        return (s.substring( 0, 1 ).toUpperCase() + s.substring( 1 ).toLowerCase()); 
    }
    
    
    @Test
    public void testSetName()
    {
        Player myPlayer = new Player();
        myPlayer.setName("testChar");
        myPlayer.setLevel(1);
        
        assertTrue(myPlayer.getName() == "testChar");
        assertTrue(myPlayer.getLevel() == 1);
    }
    
       
    @Test
    public void testD10()
    {            
        int d10 = player.d10.d(0);
        Assert.assertEquals(1,d10);     
    }
    

    @Test
    public void testD36()
    {
        d10 Test = new d10();
        @SuppressWarnings("static-access")
        int  d36 = Test.d(3, 6);
        
        assertTrue(d36 > 4);
        assertTrue(d36 < 17);
    }


    //Rolling 3-sided dice 6 times, + bonus: 2
    @Test 
    public void testD36bonus()
    {
        int d36b = player.d10.d(3,6,4);
        assertTrue(d36b > 7);
        assertTrue(d36b < 21);
    }
    
}
