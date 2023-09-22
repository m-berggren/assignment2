package assignment2;

class Skill {
    private final String nameOfSkill;
    private final int attackPower;
    private final int energyCost;


    public Skill(String nameOfSkill, int attackPower, int energyCost) {
        this.nameOfSkill = nameOfSkill;
        this.attackPower = attackPower;
        this.energyCost = energyCost;
    }

    public String getNameOfSkill() {
        return this.nameOfSkill;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public int getEnergyCost() {
        return this.energyCost;
    }

    public String toString() {
        return String.format("%s -AP: %s -EP: %s", this.nameOfSkill, this.attackPower, this.energyCost);
    }
}
