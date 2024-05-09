package entities;


import worldMap.InteractionCallback;
import worldMap.Cell;
import worldMap.WorldMap;

abstract public class Food extends Entity {
    protected WorldMap worldMap;
    protected int amount;
    protected InteractionCallback interactionCallback;

    protected Food(WorldMap worldMap, Cell cell, int amount) {
        super(cell);
        this.worldMap = worldMap;
        this.amount = amount;
    }

    public void setTest(InteractionCallback interactionCallback) {
        this.interactionCallback = interactionCallback;
    }

    public void decreaseAmount() {
        this.interactionCallback.onInteract(this);
        int currentAmountOfFood = this.getAmount();
        currentAmountOfFood -= 1;

        if (currentAmountOfFood <= 0) {
            worldMap.removeEntity(this.cell);
            return;
        }
        this.setAmount(currentAmountOfFood);
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = Math.min(amount, 0);
    }
}
