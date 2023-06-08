package de.ghse.tgi.rezepteapp.Database;


public class DatabaseReaderIngredient {
    private String nameIngredient;
    private String unit;
    private double amount;
    private int    zID;
    public String getNameIngredient() {return nameIngredient;}
    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getzID() {
        return zID;
    }

    public void setzID(int zID) {
        this.zID = zID;
    }

    public DatabaseReaderIngredient(String nameIngredient, String unit, double amount)
    {
        this.nameIngredient=nameIngredient;
        this.unit=unit;
        this.amount=amount;
    }
}
