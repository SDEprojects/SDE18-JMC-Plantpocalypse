package com.plantpocalypse;

public class PlantMonster {
    private String monsterName;
    private int baseAttack = 2;
    private String monsterDescription;

    /* CONSTRUCTORS */
    public PlantMonster(String monsterName){
        setMonsterName(monsterName);
    }

    public PlantMonster(String monsterName, int baseAttack){
        setMonsterName(monsterName);
        setBaseAttack(baseAttack);
    }

    /* BUSINESS METHODS */
   public void attackPlayer(Player player){
       if (player != null) {
           player.getHurt(getBaseAttack());
           System.out.println("You were attacked by " + getMonsterName() + " and lost " + getBaseAttack() + " health points.");
       }
   }

    /* GETTERS AND SETTERS */
    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public String getMonsterDescription() {
        return monsterDescription;
    }

    public void setMonsterDescription(String monsterDescription) {
        this.monsterDescription = monsterDescription;
    }
}
