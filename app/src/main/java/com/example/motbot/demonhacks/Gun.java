package com.example.motbot.demonhacks;

/**creates Gun objects that have a tier and cost*/
public class Gun {
    //Weapon tier determines how many coins are received per attack
    private int gunTier;

    //Higher tiers add a higher possible range of coins received per attack
    //for example player receives 100 coins per attack + up to 10% bonus at tier 1
    private float gunMultiplier;

    //Each weapon tier has a specific cost to purchase the weapon
    private int cost;

    /**
     * Create a new gun object with a certain tier - when the Tower is first constructed, it creates a level 1 gun
     */
    Gun(int gunTier){

        //Gun Tier must be between 1 and 5
        if(gunTier <=5 && gunTier >= 1)
            this.gunTier = gunTier;
        else
            System.out.print("ERROR! Gun tier is not between 1 and 5");
    }

    /**
     * @return the Gun's Tier (Level)
     */
    public int getGunTier() {
        return gunTier;
    }

    /** Sets the Gun's Tier (Level)
     * @param gunTier
     */
    public void setGunTier(int gunTier) {
        this.gunTier = gunTier;
    }

    /**
     * @return what this Tier's purchase price is
     */
    public int getCost() {
        switch(gunTier){
            case(2):
                return 200;
            case(3):
                return 500;
            case(4):
                return 1000;
            case(5):
                return 2000;
            default:
                return 0;
        }
    }
}
