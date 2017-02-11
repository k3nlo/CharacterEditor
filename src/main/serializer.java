package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


import player.Player;

/**
 * 
 * An instance of this class can serialize and deserialize a player.
 *
 */
public class serializer
{
    
    /**
     * This method is capable of saving a player to a file.
     * 
     * This method always returns immediately. 
     *
     * @param  path  the path to the file we want to serialized the player
     * @param  playerToSave   an instance of Player class
     * @return <b>true</b> if the save was successful, <b>false</b> otherwise
     * 
     */
    public boolean saveToFile(String path, Player playerToSave) 
    {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(playerToSave);
            out.close();
            return true;
            } catch (IOException e)
            {
            return false;
            }
    }
    
    
    /**
     * This method loads a player from a file.
     *         
     * @param  path  the path to the file we want to serialized the player
     * @return Player
     * 
     */
    public  Player loadPlayerFromFile(String path) {
        try {
            
            File file = new File(path);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            
            Player player = (Player) in.readObject();
            in.close();
            return player;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
