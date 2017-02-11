package player;
//package player.Inventory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Inventory
{
    public ArrayList<String> ls = new ArrayList<String>();

    public Inventory()
    {
        String leather = "Leather-Armor";
        String padded ="Padded-Armor";
        String studded="Studdedleather-Armor";
        String chainShirt="ChainShirt-Armor";
        String breastplate = "BreastPlate-Armor";
        String bandedmail ="BandedMail-Armor";
        String halfplate = "Halfplate-Armor";
        String fullplate="FullPLate-Armor";
        String buckler = "Buckler-Shield";
        String heavy="Heavy-Shield";
        String tower="tower-Shield";
        String gloves = "Gloves";
        String bracers = "Bracers";
        String rings = "Rings";
        String helmet = "Helmet";
        String boots = "Boots";
        String belt = "Belt";
        String backpack = "BackPack";
        String sword ="Sword";
        String bowArrow="BowArrow";
        ls.add(tower);
        ls.add(heavy);
        ls.add(buckler);
        ls.add(fullplate);
        ls.add(halfplate);
        ls.add(bandedmail);
        ls.add(breastplate);
        ls.add(studded);
        ls.add(padded);
        ls.add(leather);
        ls.add(backpack);
        ls.add(gloves);
        ls.add(bracers);
        ls.add(rings);
        ls.add(helmet);
        ls.add(boots);
        ls.add(belt);
        ls.add(sword);
        ls.add(bowArrow);
    }
/*
    public void SelectItem() throws IOException
    {
        for (int i = 0; i < ls.size(); i++)
        {
            System.out.println(ls.get(i));
        }
        System.out.println("Select Item");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String item = br.readLine();
        for (int j = 0; j < ls.size(); j++)
        {

            if (ls.get(j).equalsIgnoreCase(item))
            {
                //to be used in future builds
                //WornItems wI = new WornItems(item);
                break;
            }
        }
        

    }*/

   
}
