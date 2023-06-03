package de.ghse.tgi.rezepteapp;

public class Ingredient {
    private String unit;
    private String name;
    private double amount;

    public Ingredient(){}
    public String getIngredient() {
        return name;
    }

    public void setIngredient(String ingredient) {
        this.name = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



}
