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

    // Method to get EC of Skill
    public int getEnergyCost() {
        return this.energyCost;
    }

    // Method to check if two Skills are equal
    // --> Skills are equal when their name, EC and AP values are equal,
    public boolean equals(Object anotherObject) {
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else {
            Skill anotherSkill = (Skill) anotherObject;
            boolean sameName = this.nameOfSkill.equals(anotherSkill.getNameOfSkill());
            boolean sameEC = this.energyCost == anotherSkill.getEnergyCost();
            boolean sameAP = this.attackPower == anotherSkill.getAttackPower();
            isEqual = sameName && sameEC && sameAP;
        }
        return isEqual;
    }

    // toString method to return message of Skill name, AP and EC
    public String toString() {
        String message = "";
        message = String.format("%s - AP: %s EC: %s", this.nameOfSkill, this.attackPower, this.energyCost);
        return message;
    }
}