package assignment2;

import java.util.Objects;

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
        return nameOfItem;
    }

    public int getHPValue() {
        return HPValue;
    }

    public double getWeight() {
        return weight;
    }

    public boolean equals(Object ItemEquality) {
        boolean isEqual = false;
        if (ItemEquality == this || ItemEquality instanceof Pokemon) {
            isEqual = true;
        } else if (ItemEquality == null || getClass() != ItemEquality.getClass()) {
            isEqual = false;
        }
        Item item = (Item) ItemEquality;
        return HPValue == item.HPValue && Double.compare(item.weight, weight) == 0 && Objects.equals(nameOfItem, item.nameOfItem);
    }

    public String toString() {
        double truncateWeight = ((int) (weight * 100)) / 100.0;
        return String.format("%s heals %s HP. (%.2f)", nameOfItem, HPValue, truncateWeight);
    }
}
