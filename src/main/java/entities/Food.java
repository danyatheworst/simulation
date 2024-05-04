package entities;


import worldMap.Cell;

abstract public class Food extends Entity {
    protected int amount;
    public boolean isConsumed;

    protected Food(Cell cell, int amount) {
        super(cell);
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = Math.min(amount, 0);
    }
}
