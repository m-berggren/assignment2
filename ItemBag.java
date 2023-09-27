package assignment2;

import java.util.ArrayList;

public class ItemBag {
    private double maxWeight;
    private double currentWeight;
    private ArrayList<Item> items;

    public ItemBag(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
        this.items = new ArrayList<>();
    }

    public double addItem(Item item) {
        double wasAdded = null;
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
        return wasAdded;
    }

    public Item removeItemAt(int index) {
        if (index >= 0 && index < items.size()) {
            Item removedItem = items.remove(index);
            currentWeight -= removedItem.getWeight();
            return removedItem;
        }
        return null;
    }

    public String peekItemAt(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index).toString();
        }
        return "";
    }

    public Item popItem() {
        if (!items.isEmpty()) {
            Item poppedItem = items.remove(0);
            currentWeight -= poppedItem.getWeight();
            return poppedItem;
        }
        return null;
    }

    public int getNumOfItems() {
        return items.size();
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
