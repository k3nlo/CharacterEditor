package main;

import javax.swing.JTextField;

import junit.framework.TestCase;

import org.junit.Test;

import player.Player;

/**
 * 
 * This class contains test cases for GUI class and serializer class.
 *
 */
public class Tests extends TestCase
{

    @Test
    public void testSerializer()
    {
        Player myPlayer = new Player();
        myPlayer.setName("looloo");
        myPlayer.setLevel(1);
               
        serializer myserializer = new serializer();        
        myserializer.saveToFile("C:\\Concordia courses\\APP\\Project\\testFiles\\Looloo.ser",myPlayer);
        
        Player myPlayer2 ;
        myPlayer2 = myserializer.loadPlayerFromFile("C:\\Concordia courses\\APP\\Project\\testFiles\\Looloo.ser");
        
        assertNotNull(myPlayer2);
        assertEquals(myPlayer2.getName(),myPlayer.getName());       
        assertEquals(myPlayer2.getLevel(),myPlayer.getLevel());
    }

    @Test
    public void testSavePlayer()
    {
        Player myPlayer = new Player();
        myPlayer.setName("testChar");
        myPlayer.setLevel(1);
        
        GUI myGUI = new GUI();
        myGUI.savePlayer(myPlayer);
        
        Player myPlayer2 ;
        serializer myserializer = new serializer();
        myPlayer2 = myserializer.loadPlayerFromFile("C:\\Concordia courses\\APP\\Project\\testFiles\\testChar.ser");
        
        assertNotNull(myPlayer2);
        assertEquals(myPlayer2.getName(),myPlayer.getName());       
        assertEquals(myPlayer2.getLevel(),myPlayer.getLevel());
        
    }
    
    @Test
    public void testInputVerifierExpression()
    {        
        JTextField text = new JTextField ();   
        
        text.setText("2test");
        assertFalse(text.getText().matches("[a-zA-Z][a-zA-Z0-9]*") && text.getText().length() < 20);
        
        text.setText("t@est");
        assertFalse(text.getText().matches("[a-zA-Z][a-zA-Z0-9]*") && text.getText().length() < 20);
        
        text.setText("test5");
        assertTrue(text.getText().matches("[a-zA-Z][a-zA-Z0-9]*") && text.getText().length() < 20);
    }

}
