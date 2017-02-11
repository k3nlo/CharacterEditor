package player;


import java.io.IOException;

public class armor
{
    public static int armorPoint;
    public int        armorBonus;
    public int        shieldBonus;
    public int        sizeModifier;
    boolean           armor;

    public void armor1(String armorType, int dexBonus) throws IOException
    {

        /*
        switch (armorType)
        {
            case "Padded": {
                armorBonus = 1;
                armorPoint = 10 + armorBonus + dexBonus;
                break;
            }
            case "Leather": {
                armorBonus = 2;
                armorPoint = 10 + armorBonus + dexBonus;
                break;
            }
            case "Studded leather": {
                armorBonus = 3;
                armorPoint = 10 + armorBonus + dexBonus;
                break;
            }
            case "Chain Shirt": {
                armorBonus = 4;
                armorPoint = 10 + armorBonus + dexBonus;
                break;

            }
            case "Breast Plate": {
                armorBonus = 5;
                armorPoint = 10 + armorBonus + dexBonus;
                break;

            }
            case "Banded Plate": {
                armorBonus = 6;
                armorPoint = 10 + armorBonus + dexBonus;
                break;

            }
            case "Half Plate": {
                armorBonus = 7;
                armorPoint = 10 + armorBonus + dexBonus;
                break;

            }
            case "Full Plate": {
                armorBonus = 8;
                armorPoint = 10 + armorBonus + dexBonus;
                break;

            }
        }*/
    }

    public void shield(String shieldType, int dexBonus) throws IOException
    {
        /*
        switch (shieldType)
        {
            case "Buckler": {
                shieldBonus = 1;
                armorPoint = 10 + shieldBonus + dexBonus;
                break;
            }
            case "Heavy Shield": {
                shieldBonus = 2;
                armorPoint = 10 + shieldBonus + dexBonus;
                break;
            }
            case "Tower Shield":
            {
                shieldBonus = 4;
                armorPoint = 10 + shieldBonus + dexBonus;
                break;
            }
        }*/
    }
}
