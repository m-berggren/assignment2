package assignment2;

import java.util.ArrayList;

public class ItemBag { // creating an item bag, item weight and item list.
    private double maxWeight;
    private double currentWeight;
    private ArrayList<Item> items;

    public ItemBag(double maxWeight) { // This part creates the maximum weight capacity of a bag
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
        this.items = new ArrayList<>();
    }

    public int addItem(Item item) { // method that adds items into the bag
        if (item != null) {
            if (currentWeight + item.getWeight() <= maxWeight) {
                int indexToInsert = 0;
                while (indexToInsert < items.size() && items.get(indexToInsert).getWeight() > item.getWeight()) {
                    indexToInsert++;
                }
                items.add(indexToInsert, item);
                currentWeight += item.getWeight();
                return indexToInsert;
            }
            return -1;
        }
        return 0;
    }


    public Item removeItemAt(int index) { // method that removes items from the bag
        if (index >= 0 && index < items.size()) {
            Item removedItem = items.remove(index);
            currentWeight -= removedItem.getWeight();
            return removedItem;
        }
        return null;
    }

    public String peekItemAt(int index) {  // method created to peek an item in the bag
        if (index >= 0 && index < items.size()) {
            return items.get(index).toString();
        }
        return "";
    }

    public Item popItem() { // method to remove an item from the bag and returns the item
        if (!items.isEmpty()) {
            Item poppedItem = items.remove(0);
            currentWeight -= poppedItem.getWeight();
            return poppedItem;
        }
        return null;
    }

    public int getNumOfItems() { // returns the number of items in the bag.
        return items.size();
    }

    public double getCurrentWeight() { // returns current weight of the bag
        return currentWeight;
    }

    public double getMaxWeight() { // method to get max value of the bag
        return maxWeight;
    }
}