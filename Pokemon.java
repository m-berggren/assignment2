package assignment2;

public class Pokemon {
    private String name;
    private final int maxHP;
    private final String type;
    private int currentHP;
    private int currentEP;
    private Skill skill;


    public Pokemon(String pokemonName, int maxHP, String type) {
        this.name = pokemonName;
        this.maxHP = maxHP;
        this.type = type;
        this.currentHP = maxHP;
        this.currentEP = 100;
        this.skill = null;
    }

    public String getName() {
        return this.name;
    }

    public int getEnergy() {
        return this.currentEP;
    }

    public int getMAX_HP() {
        return this.maxHP;
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

    @Override
    public boolean equals(Object anotherObject) {
        // check if reference is equal to itself and if reference is nothing
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else if(anotherObject instanceof Pokemon) {

            Pokemon anotherPokemon = (Pokemon)anotherObject;
            boolean sameName = this.name.equals(anotherPokemon.getName());
            boolean sameType = this.type.equals(anotherPokemon.getType());
            boolean sameSkill = checkIfSkillsAreEqual(anotherPokemon);
            boolean sameHP = this.currentHP == anotherPokemon.getCurrentHP();
            boolean sameMaxHP = this.maxHP == anotherPokemon.getMAX_HP();
            boolean sameEP = this.currentEP == anotherPokemon.getEnergy();

            isEqual = sameName && sameType && sameSkill && sameHP && sameMaxHP && sameEP;

        } else {
            isEqual = false;
        }
        return isEqual;
    }

    private boolean checkIfSkillsAreEqual(Pokemon anotherPokemon) {
        // Method added to
        boolean isEqual = false;
        if(this.skill == null && anotherPokemon.getSkill() == null) {
            isEqual = true;
        } else if(this.skill == null && anotherPokemon.getSkill() != null){
            isEqual = false;
        } else {
            isEqual = this.skill.equals(anotherPokemon.getSkill());
        }
        return isEqual;
    }

    @Override
    public String toString() {
        String message = "";
        if (!knowsSkill()) {
            message = this.name + " (" + this.type + ")";
        } else {
            message = this.name + " (" + this.type + "). Knows " + this.skill.toString();
        }
        return message;
    }

    public boolean knowsSkill() {
        return this.skill != null;
    }

    public void forgetSkill() {
        this.skill = null;
    }

    public void learnSkill(String nameOfSkill, int attackPower, int energyCost) {
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
    public String attack(Pokemon defender) {
        String message = "";
        // If the attacker has fainted (currentHP == 0).
        if (this.currentHP == 0) {
            message = "Attack failed. "+ this.name + " fainted.";

            // If target pokemon has fainted (currentHP == 0.
        } else if (defender.currentHP == 0) {
            message = "Attack failed. " + defender.getName() + " fainted.";

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

            message = this.name + " uses " + this.skill.getNameOfSkill() + " on " + defender.getName() + ".";

            // Show if defender faints or not after the attack.
            int HPLeft = defender.currentHP - (int) (this.skill.getAttackPower() * valueMultiplier);
            if (HPLeft > 0) {
                defender.currentHP = HPLeft;
                this.currentEP -= this.skill.getEnergyCost();
                message += multiplierMessage(valueMultiplier) + "\r\n" + defender.getName() + " has " + defender.getCurrentHP() + " HP left.";

            } else {
                defender.currentHP = 0;
                this.currentEP -= this.skill.getEnergyCost();
                message += multiplierMessage(valueMultiplier) + "\r\n" + defender.getName() + " has 0 HP left. " + defender.getName()+ " faints.";
            }
        }
        return message;
    }

    private double getValueMultiplier(String attacker, String defender) {
        Type attackerType = new Type(attacker);
        Type defenderType = new Type(defender);
        return attackerType.calculateDamage(defenderType);
    }

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
            this.currentHP = Math.min(this.maxHP, this.currentHP + 20);
        }
    }

    public void recoverEnergy() {
        this.currentEP = Math.min(100, this.currentEP + 25);
    }

    public String useItem(Item nameOfObject) {
        // Check if HP of Pokemon is equal to his Max HP
        // If the statement is true then Pokemon can not use item
        if (this.currentHP == this.maxHP) {
            return String.format("%s could not use %s. HP is already full.", this.name, nameOfObject.getNameOfItem());
        }

        // initialise variable healthHealed, to get healed HP after using item
        // (if item HP heal value + current HP of Pokemon is bigger than Pokemon Max HP)
        int healthHealed = Math.min(this.maxHP - this.currentHP, nameOfObject.getHPValue());
        this.currentHP += healthHealed;

        // return what item was used and what amount of HP it healed
        return String.format("%s used %s. It healed %s HP.", this.name, nameOfObject.getNameOfItem(), healthHealed);
    }

}
