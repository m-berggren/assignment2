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

    // Method to check if two Skills are equal
    // Skills are equal when their Name, AP and EP values are equal
    public boolean checkIfSkillsAreEqual(Pokemon anotherPokemon) {
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
        if (!knowsSkill()) {
            return String.format("%s (%s)", this.name, this.type);
        }
        return String.format("%s (%s). Knows %s - AP: %d EC: %d",
                this.name,
                this.type,
                this.skill.getNameOfSkill(),
                this.skill.getAttackPower(),
                this.skill.getEnergyCost());
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
        // If the attacker has fainted.
        if (this.currentHP == 0) {
            return String.format("Attack failed. %s fainted.", this.name);

            // If target pokemon has fainted.
        } else if (defender.currentHP == 0) {
            return String.format("Attack failed. %s fainted.", defender.name);

            // If attacker does not know a skill.
        } else if (!knowsSkill()) {
            return String.format("Attack failed. %s does not know a skill.", this.name);

            // If the attacker knows a skill and has less energy points than the cost of the skill (ec)
        } else if (knowsSkill() && this.currentEP < skill.getEnergyCost()) {
            return String.format("Attack failed. %s lacks energy: %d//%d",
                    this.name,
                    this.currentEP,
                    skill.getEnergyCost()
            );
            // If attacker has enough EP to use skill, attack is successful.
        } else {
            //
            Type attackerType = new Type(this.type);
            Type defenderType = new Type(defender.getType());
            // Show if attack is super effective or not effective.
            double valueMultiplier = attackerType.calculateDamage(defenderType);

            // Show if defender faints or not after the attack.
            int HPLeft = defender.currentHP - (int) (this.skill.getAttackPower() * valueMultiplier);
            if (HPLeft > 0) {
                defender.currentHP = HPLeft;
                this.currentEP -= this.skill.getEnergyCost();
                return String.format("%s%n%s has %d HP left.", multiplierMessage(valueMultiplier, defender), defender.name, defender.getCurrentHP());

            } else {
                defender.currentHP = 0;
                this.currentEP -= this.skill.getEnergyCost();
                return String.format("%s%n%s has 0 HP left. %s faints.", multiplierMessage(valueMultiplier, defender), defender.name, defender.name);
            }
        }
    }

    public String multiplierMessage(double multiplier, Pokemon opponent) {
        if (multiplier > 1) {
            return String.format("%s uses %s on %s. It is super effective!", this.name, this.skill.getNameOfSkill(), opponent.name);

        } else if (multiplier < 1) {
            return String.format("%s uses %s on %s. It is not very effective...", this.name, this.skill.getNameOfSkill(), opponent.name);
        }
        return String.format("%s uses %s on %s.", this.name, this.skill.getNameOfSkill(), opponent.name);
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