package assignment2;

public class Pokemon {
    private String name;
    private final int maxHp;
    private final String type;
    private int currentHP;
    private int currentEP;
    private Skill skill;


    public Pokemon(String pokemonName, int maxHp, String type) {
        this.name = pokemonName;
        this.maxHp = maxHp;
        this.type = type;
        this.currentHP = maxHp;
        this.currentEP = 100;
        this.skill = null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return this.currentEP;
    }

    public int getMAX_HP() {
        return this.maxHp;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public String getType() {
        return this.type;
    }

    public int getCurrentHP() {
        return this.currentHP;
    }

    public boolean equals(Object anotherObject) {
        // check if reference is equal to itself and if reference is nothing
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else if (anotherObject instanceof Pokemon) {

            Pokemon anotherPokemon = (Pokemon) anotherObject;
            boolean sameName = this.name.equals(anotherPokemon.getName());
            boolean sameType = this.type.equals(anotherPokemon.getType());
            boolean sameSkill = this.skill == anotherPokemon.skill;
            boolean sameHP = this.currentHP == anotherPokemon.getCurrentHP();
            boolean sameMaxHP = this.maxHp == anotherPokemon.getMAX_HP();
            boolean sameEP = this.currentEP == anotherPokemon.getEnergy();

            isEqual = sameName && sameType && sameSkill && sameHP && sameMaxHP && sameEP;

        } else {
            isEqual = false;
        }
        return isEqual;
    }

    public String toString() {
        String message = this.name + " (" + this.type + ")";
        if (!knowsSkill()) {
            return message;
        } else {
            return message + ". Knows " + this.skill;
        }
    }

    // If 'skill' is not null then return will be true and vice versa.
    public boolean knowsSkill() {
        return this.skill != null;
    }

    // This will return the 'skill' instance variable into null state.
    public void forgetSkill() {
        this.skill = null;
    }

    // Learns a skill by creating a new instance of the 'Skill' class
    // and assigning it to the 'skill' instance variable.
    // This will overwrite any currently learned skill.
    public void learnSkill(String nameOfSkill, int attackPower, int energyCost) {
        this.skill = new Skill(nameOfSkill, attackPower, energyCost);
    }


    public String attack(Pokemon defender) {
        String message = "";
        // If the attacker has fainted (currentHP == 0).
        if (this.currentHP == 0) {
            message = String.format("Attack failed. %s fainted.", this.name);

            // If target pokemon has fainted (currentHP == 0.
        } else if (defender.currentHP == 0) {
            message = String.format("Attack failed. %s fainted.", defender.getName());

            // If attacker does not know a skill.
        } else if (!knowsSkill()) {
            message = "Attack failed. " + this.name + " does not know a skill.";

            // If the attacker knows a skill and has less energy points than the cost of the skill
        } else if (knowsSkill() && this.currentEP < skill.getEnergyCost()) {
            message = "Attack failed. " + this.name + "lacks energy: " + this.currentEP + "/" + this.skill.getEnergyCost();

            // If attacker has enough EP to use skill, attack is successful.
        } else {

            // Gets a value multiplier for the attack. Possible outcomes: {0.5, 1.0, 2}.
            double valueMultiplier = getValueMultiplier(this.type, defender.getType());

            String beginningOfMessage = this.name + " uses " + this.skill.getNameOfSkill() + " on " + defender.getName() + ".";

            int HPLeft = defender.currentHP - (int) (this.skill.getAttackPower() * valueMultiplier);
            // Check if defender faints or not after the attack.
            if (HPLeft > 0) {
                defender.currentHP = HPLeft;
                this.currentEP -= this.skill.getEnergyCost();
                message = String.format("%s%s%n%s has %d HP left.", beginningOfMessage, multiplierMessage(valueMultiplier), defender.getName(), defender.getCurrentHP());

            } else {
                defender.currentHP = 0;
                this.currentEP -= this.skill.getEnergyCost();
                message = String.format("%s%s%n%s has 0 HP left. %s faints.", beginningOfMessage, multiplierMessage(valueMultiplier), defender.getName(), defender.getName());
            }
        }
        return message;
    }

    // Refactored from 'attack' method to make it more readable.
    private double getValueMultiplier(String attacker, String defender) {
        Type attackerType = new Type(attacker);
        Type defenderType = new Type(defender);
        return attackerType.calculateDamage(defenderType);
    }

    // This refactor ensures 'attack' method does not need added nested if statements.
    private String multiplierMessage(double multiplier) {
        String message = "";
        if (multiplier > 1) {
            message = " It is super effective!";

        } else if (multiplier < 1) {
            message = " It is not very effective...";
        }
        return message;
    }

    public void rest() {
        if (this.currentHP != 0) {
            // If current HP + 20 is higher than max HP it will return the lowest (maxHP),
            // similarly, if current HP + 20 is less than max HP it will return the min value.
            this.currentHP = Math.min(this.maxHp, this.currentHP + 20);
        }
    }

    public void recoverEnergy() {
        // min method chooses the lowest value, so, if current EP + 25 is higher than 100 it chooses 100.
        // In the same way it chooses current EP + 25 if it is lower than 100.
        this.currentEP = Math.min(100, this.currentEP + 25);
    }

    public String useItem(Item nameOfObject) {
        String message = "";
        // Check if HP of Pokemon is equal to his Max HP
        // If the statement is true then Pokemon can not use item
        if (this.currentHP == this.maxHp) {
            message = String.format("%s could not use %s. HP is already full.", this.name, nameOfObject.getNameOfItem());
            return message;
        }

        // initialise variable healthHealed, to get healed HP after using item
        // (if item HP heal value + current HP of Pokemon is bigger than Pokemon Max HP)
        int healthHealed = Math.min(this.maxHp - this.currentHP, nameOfObject.getHpValue());
        this.currentHP += healthHealed;

        // return what item was used and what amount of HP it healed
        message = String.format("%s used %s. It healed %s HP.", this.name, nameOfObject.getNameOfItem(), healthHealed);
        return message;
    }

}