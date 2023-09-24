package assignment2;

public class Pokemon {
    private String pokemonName;
    private final int maxHitPoints;
    private final String pokemonType;
    private int currentHitPoints;
    private int currentEnergyPoints;

    private boolean hasLearnedSkill;
    private Skill skill;
    private PokemonType hello;


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

    public PokemonType mapType(String typeName) {
        return switch(typeName.toLowerCase()) {
            case "water" -> PokemonType.WATER;
            case "fire" -> PokemonType.FIRE;
            case "grass" -> PokemonType.GRASS;
            default -> PokemonType.NORMAL;
        };
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
    public String attack(Pokemon opponent) {
        // If the attacker has fainted.
        if (this.currentHitPoints == 0) {
            return String.format("Attack failed. %s fainted.", this.pokemonName);

            // If target pokemon has fainted.
        } else if (opponent.currentHitPoints == 0) {
            return String.format("Attack failed. %s fainted.", opponent.pokemonName);

            // If attacker does not know a skill.
        } else if (!knowsSkill()) {
            return String.format("Attack failed. %s does not know a skill.", this.pokemonName);

            // If the attacker knows a skill and has less energy points than the cost of the skill (ec)
        } else if (knowsSkill() && this.currentEnergyPoints < skill.getEnergyCost()) {
            return String.format("Attack failed. %s lacks energy: %d//%d",
                    this.pokemonName,
                    this.currentEnergyPoints,
                    skill.getEnergyCost()
            );
            // If attacker has enough EP to use skill, attack is successful.
        } else {


            Type type = new Type(mapType(this.pokemonType));
            Type opponentType = new Type(mapType(opponent.pokemonType));
            // Show if attack is super effective or not effective.
            double valueMultiplier = type.calculateDamage(opponentType);

            // Show if opponent faints or not after the attack.
            int HPLeft = opponent.currentHitPoints - (int)(this.skill.getAttackPower()*valueMultiplier);
            if (HPLeft > 0) {
                opponent.currentHitPoints = HPLeft;
                this.currentEnergyPoints -= this.skill.getEnergyCost();
                return String.format("%s%n%s has %d HP left.", multiplierMessage(valueMultiplier, opponent), opponent.pokemonName, opponent.getCurrentHP());

            } else {
                opponent.currentHitPoints = 0;
                this.currentEnergyPoints -= this.skill.getEnergyCost();
                return String.format("%s%n%s has 0 HP left. %s faints.", multiplierMessage(valueMultiplier, opponent), opponent.pokemonName, opponent.pokemonName);
            }
        }
    }

    public String multiplierMessage(double multiplier, Pokemon opponent) {
        if (multiplier > 1) {
            return String.format("%s uses %s on %s. It is super effective!", this.pokemonName, this.skill.getNameOfSkill(), opponent.pokemonName);

        } else if (multiplier < 1) {
            return String.format("%s uses %s on %s. It is not very effective...", this.pokemonName, this.skill.getNameOfSkill(), opponent.pokemonName);
        }
        return String.format("%s uses %s on %s.", this.pokemonName, this.skill.getNameOfSkill(), opponent.pokemonName);
    }

    public void rest() {
        if(this.currentHitPoints != 0) {
            this.currentHitPoints = Math.min(this.maxHitPoints, this.currentHitPoints + 20);
        }
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
