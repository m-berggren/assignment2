package assignment2;

public class Pokemon {
    private String pokemonName;
    private final int MAX_HP;
    private final String pokemonType;
    private int currentHitPoints;
    private int currentEnergyPoints;
    private boolean knowsSkill;
    private String nameOfSkill;
    private int attackPower;
    private int energyCost;

    public Pokemon(String pokemonName, int maxHitPoints, String pokemonType) {
        this.pokemonName = pokemonName;
        this.MAX_HP = maxHitPoints;
        this.pokemonType = pokemonType;
        this.currentHitPoints = maxHitPoints;
        this.currentEnergyPoints = 100;
        this.knowsSkill = false;
        this.nameOfSkill = null;
        this.attackPower = 0;
        this.energyCost = 0;
    }

    public String getName() {
        return this.pokemonName;
    }

    public int getEnergy() {
        return this.currentEnergyPoints;
    }

    public int getMAX_HP() {
        return this.MAX_HP;
    }

    public String getType() {
        return this.pokemonType;
    }

    public int getCurrentHP() {
        return this.currentHitPoints;
    }

    public String toString() {
        if (!this.knowsSkill) {
            return String.format("%s (%s)%n", this.pokemonName, this.pokemonType);
        }
        return String.format("%s (%s). Knows %s - AP: %d EC: %d", this.pokemonName, this.pokemonType, this.attackPower, this.energyCost);
    }

    public boolean knowsSkill() {
        toString();
        return false;
    }

    public void forgetSkill() {
        this.knowsSkill = false;
        this.nameOfSkill = "";
        this.attackPower = 0;
        this.energyCost = 0;
        toString();
    }

    public void learnSkill(String nameOfSkill, int attackPower, int energyCost) {
        this.knowsSkill = true;
        this.nameOfSkill = nameOfSkill;
        this.attackPower = attackPower;
        this.energyCost = energyCost;
    }

    /* TBD:
     * Add type checks (super effective / not effective / normal)
     * Check so HP does not go below zero.
     */
    public Pokemon attack(Pokemon opponentToAttack) {
        this.currentEnergyPoints -= this.energyCost;
        opponentToAttack.currentHitPoints -= this.attackPower;
        return opponentToAttack;
    }

    public void rest() {
        this.currentHitPoints = Math.min(this.MAX_HP, this.currentHitPoints + 20);
    }

    public void recoverEnergy() {
        this.currentEnergyPoints = Math.min(100, this.currentEnergyPoints + 25);
    }

    public Object useItem(Item potion) {
        if (this.currentHitPoints == this.MAX_HP) {
            return String.format("%s could not use %s. HP is already full.", this.pokemonName, potion.getNameOfItem());
        } else if (this.currentHitPoints > this.MAX_HP) {
            return String.format("%s + %s = %s - %s HP cannot go beyond %s", this.currentHitPoints, potion.getHPValue(), this.MAX_HP, this.pokemonName, this.MAX_HP);
        } else {
            return String.format("%s used %s. It healed %s HP.", this.pokemonName, potion.getNameOfItem(), potion.getHPValue());
        }
    }
}

