package player;

import java.util.ArrayList;
import java.util.List;

public class WornItems
{
    public static final ArrayList<String> wornList = new ArrayList<String>();
    

    public void addItems(List s)
    {

        wornList.addAll(s);
        System.out.println(s);
        Sword sword = new Sword();
        sword.Sword();
    }

    public void removeItem(List s)
    {
        System.out.println("Item Removed");
        wornList.remove(s);
       
    }

}