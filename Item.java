package assignment2;

public class Item {
    private final String nameOfItem;
    private final int HPValue;
    private double weight;

    public Item(String nameOfItem, int HPValue, double weight) {
        this.nameOfItem = nameOfItem;
        this.HPValue = HPValue;
        this.weight = weight;
    }

    public String getNameOfItem() {
        return this.nameOfItem;
    }

    public int getHPValue() {
        return this.HPValue;
    }

    public double getWeight() {
        return this.weight;
    }

    //--> add equality of two items

    public String toString() {
        // IDK still should I change the format of truncation or not
//        double truncateWeight = ((int) (weight * 100)) / 100.00;
//        return String.format("%s heals %s HP. (%s)", nameOfItem, HPValue, truncateWeight);
        return String.format("%s heals %s HP. (%s)", nameOfItem, HPValue, Math.floor(weight * 100) / 100);
    }

//    public int useItem() {
//        if (Pokemon.getCurrentHP() >= 0) {
//            Pokemon.getCurrentHP() += HPValue;
//            return String.format("%s used %s. It healed %s HP.", Pokemon.getName(), this.nameOfItem, this.HPValue);
//            if (Pokemon.getCurrentHP() >= Pokemon.getMAX_HP()) {
//                Pokemon.getCurrentHP() = Pokemon.getMAX_HP();
//                return String.format("%s + %s = %s - %s HP cannot go beyond %s", Pokemon.getCurrentHP(), this.HPValue, Pokemon.getMAX_HP(), Pokemon.getName(), Pokemon.getMAX_HP());
//            }
//            Pokemon.getCurrentHP();
//        } else if (Pokemon.getCurrentHP() == Pokemon.getMAX_HP()) {
//            return String.format("%s could not use %s. HP is already full.", Pokemon.getName(), this.nameOfItem);
//        }
//        return useItem();
//    }
}

