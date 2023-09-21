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
        if (Pokemon.getCurrentHP() >= 0) {
            Pokemon.getCurrentHP() += HPValue;
            String useItem = String.format("%s used %s. It healed %s HP.", Pokemon.getName(), this.nameOfItem, this.HPValue);
            if (Pokemon.getCurrentHP() >= Pokemon.getMAX_HP()) {
                Pokemon.getCurrentHP() = Pokemon.getMAX_HP();
                useItem = String.format("%s + %s = %s - %s HP cannot go beyond %s", Pokemon.getCurrentHP(), this.HPValue, Pokemon.getMAX_HP(), Pokemon.getName(), Pokemon.getMAX_HP());
            }
            Pokemon.getCurrentHP();
        } else if (Pokemon.getCurrentHP() == Pokemon.getMAX_HP()) {
            String useItem = String.format("%s could not use %s. HP is already full.", Pokemon.getName(), this.nameOfItem);
        }
        return useItem();
    }
}

