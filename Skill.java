package assignment2;

public class Skill {
    // initialise attributes of name,AP and EP of Skill
    // use encapsulation and immutable objects to make code robust
    // as well to improve maintenance of the class
    private final String nameOfSkill;
    private final int attackPower;
    private final int energyCost;

    // constructor for attributes
    public Skill(String nameOfSkill, int attackPower, int energyCost) {
        this.nameOfSkill = nameOfSkill;
        this.attackPower = attackPower;
        this.energyCost = energyCost;
    }

    // Method to get Name of Skill
    public String getNameOfSkill() {
        return this.nameOfSkill;
    }

    // Method to get AP of Skill
    public int getAttackPower() {
        return this.attackPower;
    }

    //Method to get EP of Skill
    public int getEnergyCost() {
        return this.energyCost;
    }

    // toString method to return message of Skill name, AP and EP
    public String toString() {
        String message = "" ;
        message = String.format("%s - AP: %s EC: %s", this.nameOfSkill, this.attackPower, this.energyCost);
        return "";
    }
}