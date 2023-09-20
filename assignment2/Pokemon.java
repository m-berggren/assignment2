public class Pokemon {
    private final String name;
    private final String type;
    private String skill;
    private final int maxHP;
    private int HP;
    private final int startEP;
    private int EP;

    public Pokemon(String name, String type, String skill, int maxHp, int HP, int startEP, int EP) {
        this.name = name;
        this.type = type;
        this.skill = null;
        this.maxHP = maxHp;
        this.HP = maxHp; // Set current HP to max HP when created
        this.startEP = 100;
        this.EP = startEP; // Set current EP to max EP when created
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getSkill() {
        return this.skill;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public int getHP() {
        return this.HP;
    }

    public int getStartEP() {
        return this.startEP;
    }

    public int getEP() {
        return this.EP;
    }


    public void takeDamage(int damage) {
        this.HP -= damage;
        if (this.HP < 0) {
            this.HP = 0;
        }
    }

    public void restoreHP(int amount) {
        this.HP += amount;
        if (this.HP > this.maxHP) {
            this.HP = this.maxHP;
        }
    }

    public String toString() {
        if (skill != null) {
            
        } else {

        }
    }

    public class Skill {
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

        public int getEnergeCost() {
            return this.energyCost;
        }

        public String toString() {
            String skills = String.format("%s -AP: %s -EP: %s", this.nameOfSkill, this.atackPower, this.energyCost);
            return skills;
        }
    }
}
