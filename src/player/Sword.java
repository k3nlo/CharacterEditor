package player;

public class Sword
{
public void Sword()
{
    WornItems wi = new WornItems();
    if(wi.wornList.contains("Sword"));
    {
    
    AttackBonus aBonus= new AttackBonus();
    int attackbonus= aBonus.ABonus("Sword");
    System.out.println(attackbonus);
    }
    
    //code for fight with sword


}
}
