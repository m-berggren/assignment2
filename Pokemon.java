package assignment2;

public class Pokemon {
    private String pokemonName;
    private final int maxHitPoints;
    private final String pokemonType;
    private int currentHitPoints;
    private int currentEnergyPoints;

    private boolean hasLearnedSkill;
    private Skill skill;


    public Pokemon(String pokemonName, int maxHitPoints, String pokemonType) {
        this.pokemonName = pokemonName;
        this.maxHitPoints = maxHitPoints;
        this.pokemonType = pokemonType;
        this.currentHitPoints = maxHitPoints;
        this.currentEnergyPoints = 100;
        this.hasLearnedSkill = false;
        this.skill = null;

    }

    public String getName() {
        return this.pokemonName;
    }

    public int getEnergy() {
        return this.currentEnergyPoints;
    }

    public int getMAX_HP() {
        return this.maxHitPoints;
    }

    public String getType() {
        return this.pokemonType;
    }

    public int getCurrentHP() {
        return this.currentHitPoints;
    }

    @Override
    public String toString() {
        if (!knowsSkill()) {
            return String.format("%s (%s)", this.pokemonName, this.pokemonType);
        }
        return String.format("%s (%s). Knows %s - AP: %d EC: %d",
                this.pokemonName,
                this.pokemonType,
                this.skill.getNameOfSkill(),
                this.skill.getAttackPower(),
                this.skill.getEnergyCost());
    }


    public boolean knowsSkill() {
        return hasLearnedSkill;
    }

    public void forgetSkill() {
        this.hasLearnedSkill = false;
    }

    public void learnSkill(String nameOfSkill, int attackPower, int energyCost) {
        this.hasLearnedSkill = true;
        this.skill = new Skill(nameOfSkill, attackPower, energyCost);
    }

    /*
    Attacking/receiving damage
    1: If the attacking pokemon is fainted, the message should be:
        "Attack failed. <attacker> fainted."

    2: If the target pokemon is fainted, the message should be:
        "Attack failed. <target> fainted."

    3: If the attacking pokemon does not know a skill, the message should be:
        "Attack failed. <attacker> does not know a skill."

    4: If the attacker knows a skill and has less energy points than the cost of the skill (ec):
        "Attack failed. <attacker> lacks energy: <ep>/<ec>"

    5: If the attacker has enough EP to use the Skill, then the attack is successful.
        */
    public Pokemon attack(Pokemon opponentToAttack) {
        this.currentEnergyPoints -= this.skill.getEnergyCost();
        opponentToAttack.currentHitPoints -= this.skill.getAttackPower();
        return opponentToAttack;
    }

    public void rest() {
        this.currentHitPoints = Math.min(this.maxHitPoints, this.currentHitPoints + 20);
    }

    public void recoverEnergy() {
        this.currentEnergyPoints = Math.min(100, this.currentEnergyPoints + 25);
    }

    public String useItem(Item potion) {
        if (this.currentHitPoints == this.maxHitPoints) {
            return String.format("%s could not use %s. HP is already full.", this.pokemonName, potion.getNameOfItem());
        } else if (this.currentHitPoints > this.maxHitPoints) {
            return String.format("%s + %s = %s - %s HP cannot go beyond %s", this.currentHitPoints, potion.getHPValue(), this.maxHitPoints, this.pokemonName, this.maxHitPoints);
        } else {
            return String.format("%s used %s. It healed %s HP.", this.pokemonName, potion.getNameOfItem(), potion.getHPValue());
        }
    }
}
