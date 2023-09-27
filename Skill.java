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

    // Method to check if two Skills are equal
    // Skills are equal when their Name, AP and EP values are equal
    public boolean equals(Object anotherObject) {
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else if(anotherObject instanceof Skill) {
            Skill anotherSkill = (Skill) anotherObject;
            boolean sameName = this.nameOfSkill.equals(anotherSkill.nameOfSkill);
            boolean sameAP = this.attackPower == anotherSkill.getAttackPower();
            boolean sameEP = this.energyCost == anotherSkill.getEnergyCost();

            isEqual = sameName && sameAP && sameEP;
        }
        return isEqual;
    }

    // toString method to return message of Skill name, AP and EP
    public String toString() {
        return String.format("%s -AP: %s -EP: %s", this.nameOfSkill, this.attackPower, this.energyCost);
    }
}