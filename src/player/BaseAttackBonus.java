package player;


public class BaseAttackBonus
{
    int baseAttackBonus;

    public int baseAttackBonus()
    {
        int level = 12;
        if (level <= 5)
        {
            baseAttackBonus = level;
        }
        if (level > 5 && level <= 10)
        {
            baseAttackBonus = level + (level - 5);
        }
        if (level > 10 && level <= 15)
        {
            baseAttackBonus = level + (level - 5) + (level - 10);
        }
        if (level > 15 && level <= 20)
        {
            baseAttackBonus = level + (level - 5) + (level - 10) + (level - 15);
        }
        System.out.println(baseAttackBonus);
        return baseAttackBonus;
        
    }
    
}
