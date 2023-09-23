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
        double truncateWeight = ((int) (weight * 100)) / 100.0;
        return String.format("%s heals %s HP. (%.2f)", nameOfItem, HPValue, truncateWeight);
    }
}

