package assignment2;

class Skill {
    private String nameOfSkill;
    private int attackPower;
    private int energyCost;
    private boolean knowsSkill;


    public Skill(String nameOfSkill, int attackPower, int energyCost) {
        this.knowsSkill = false;
        this.nameOfSkill = null;
        this.attackPower = 0;
        this.energyCost = 0;
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
