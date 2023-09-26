import java.util.ArrayList;
import java.util.List;

class Item {
    private String name;
    private int weight;
    private double value;

    public Item(String name, int weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
    public int getWeight(){
        return weight;
    }
    public String toString() {
        return "(" + name + ", " + weight + ", " + value + ")";
    }
}

class itemBag {
    private int maxWeight;
    private List<Item> items;

    public itemBag(int maxWeight) {
        this.maxWeight = maxWeight;
        this.items = new ArrayList<>();
    }

    public int getNumOfItems() {
        return items.size();
    }

    public int getCurrentWeight() {
        int weight = 0;
        for (Item item : items) {
            weight += item.getWeight(); // Access the weight of the item
        }
        return weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int addItem(Item item) {
        int itemIndex = 0;
        int remainingCapacity = maxWeight - getCurrentWeight();

        if (remainingCapacity < item.getWeight()) {
            return -1; //cant fit the item
        }

        while (itemIndex < items.size() && item.getWeight() >= items.get(itemIndex).getWeight()) {
            itemIndex++;
        }

        items.add(itemIndex, item);
        return itemIndex;
    }

    public Item removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.remove(index);
        }
        return null; // Index is out of bounds
    }

    public String peekItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index).toString();
        }
        return null; // Index is out of bounds
    }

    public Item popItem() {
        if (!items.isEmpty()) {
            return items.remove(0);
        }
        return null; // Bag is empty
    }
}

