package player;


public class AttackBonus
{
    public int attackBonus;
    public int baseAttackBonus, STRENGHT, DEXTERITY; // to be pulled from
                                                     // fighter class//
    

    public int ABonus(String weapon)
    {
        
        
        switch (weapon)
        {
            case "Sword": {
                BaseAttackBonus bAB = new BaseAttackBonus();
                attackBonus = bAB.baseAttackBonus() + STRENGHT;
                break;
            }

            case "BowArrow": {
                BaseAttackBonus bAB = new BaseAttackBonus();
                attackBonus = bAB.baseAttackBonus() + DEXTERITY;
                break;
            }
        }
        return attackBonus;
    }
}