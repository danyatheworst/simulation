package entities;


import worldMap.Cell;
import worldMap.WorldMap;

abstract public class Food extends Entity {
    protected WorldMap worldMap;
    protected int amount;
    public boolean isConsumed;

    protected Food(WorldMap worldMap, Cell cell, int amount) {
        super(cell);
        this.worldMap = worldMap;
        this.amount = amount;
    }

    public void decreaseAmount() {
        int currentAmountOfFood = this.getAmount();
        currentAmountOfFood -= 1;

        if (currentAmountOfFood <= 0) {
            worldMap.removeEntity(this.cell);
            return;
        }
        this.isConsumed = true;
        this.setAmount(currentAmountOfFood);
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = Math.min(amount, 0);
    }
}
