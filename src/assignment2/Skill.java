package assignment2;

class Skill {
    private final String nameOfSkill;
    private int atackPower;
    private int energyCost;


    public Skill(String nameOfSkill, int atackPower, int energyCost) {
        this.nameOfSkill = nameOfSkill;
        this.atackPower = atackPower;
        this.energyCost = energyCost;
    }

    public String gerNameOfSkill() {
        return this.nameOfSkill;
    }

    public int getAtackPower() {
        return this.atackPower;
    }

    public int getEnergyCost() {
        return this.energyCost;
    }

    public String toString() {
        return String.format("%s -AP: %s -EP: %s", this.nameOfSkill, this.atackPower, this.energyCost);
    }
}
