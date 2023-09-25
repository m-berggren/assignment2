package assignment2;

import java.util.Objects;

public class Item {
    // initialise attributes of name,HP and weight of Item
    // use encapsulation and immutable objects to make code robust
    // as well to improve maintenance of the class
    private final String nameOfItem;
    private final int HPValue;
    private final double weight;

    // constructor for attributes
    public Item(String nameOfItem, int HPValue, double weight) {
        this.nameOfItem = nameOfItem;
        this.HPValue = HPValue;
        this.weight = weight;
    }

    // Method to get Name of Item
    public String getNameOfItem() {
        return nameOfItem;
    }

    // Method to get HP value of Item
    public int getHPValue() {
        return HPValue;
    }

    // Method to get Weight of Item
    public double getWeight() {
        return weight;
    }

    // Method to check if two Items are equal
    // Items are equal when their Hp, weight and name values are equal
    public boolean equals(Object anotherObject) {
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else {
            Item anotherItem = (Item) anotherObject;
            boolean sameHP = this.HPValue == anotherItem.getHPValue();
            boolean sameWeight = this.weight == anotherItem.getWeight();
            boolean sameName = this.nameOfItem.equals(anotherItem.nameOfItem);

            isEqual = sameHP && sameWeight && sameName;
        }
        return isEqual;
    }

    // toString method to truncate value of the weight to a precision of two decimals
    public String toString() {
        double truncateWeight = ((int) (weight * 100)) / 100.0;
        return String.format("%s heals %s HP. (%.2f)", nameOfItem, HPValue, truncateWeight);
    }
}
