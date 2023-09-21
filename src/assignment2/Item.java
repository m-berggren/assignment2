package assignment2;
public class Item {
    private final String nameOfItem;
    private final int HPValue;
    private final double weight;

    public Item(String nameOfItem, int HPValue, double weight) {
        this.nameOfItem = nameOfItem;
        this.HPValue = HPValue;
        this.weight = weight;
    }

    //--> add equality of two items

    public String toString() {
        String Item = String.format("%s heals %s HP. %.2f", nameOfItem, HPValue, weight);
        return Item;
    }

    public int useItem() {
        int currentHP = Pokemon.getCurrentHP();
        int maxHP = Pokemon.getMAX_HP();

        if (currentHP >= 0) {
            currentHP += HPValue;
            String useItem = String.format("%s used %s. It healed %s HP.", Pokemon.getName(), this.nameOfItem, this.HPValue);
            if (currentHP >= maxHP) {
                currentHP = maxHP;
                useItem = String.format("%s + %s = %s - %s HP cannot go beyond %s", currentHP, this.HPValue, maxHP, Pokemon.getName(), maxHP);
            }
            Pokemon.getCurrentHP(currentHP);
        } else if (currentHP == maxHP) {
            String useItem = String.format("%s could not use %s. HP is already full.", Pokemon.getName(), this.nameOfItem);
        }
        return useItem();
    }
}

