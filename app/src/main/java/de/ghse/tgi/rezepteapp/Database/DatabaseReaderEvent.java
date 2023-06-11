package de.ghse.tgi.rezepteapp.Database;

public class DatabaseReaderEvent {
    private String datum;
    private int stunden;
    private int minuten;
    private int    eID;

    public String geDatum() {return datum;}

    public void setDatum(String nameIngredient) {
        this.datum = nameIngredient;
    }

    public int getStunden() {
        return stunden;
    }

    public void setUnit(int stunden) {
        this.stunden = stunden;
    }

    public double getMinuten() {return minuten;}

    public void setMinuten(int minuten) {
        this.minuten = minuten;
    }

    public int getzID() {
        return eID;
    }

    public void setzID(int zID) {
        this.eID = zID;
    }

    public DatabaseReaderEvent(String datum, int stunden, int minuten)
    {
        this.datum=datum;
        this.stunden=stunden;
        this.minuten=minuten;
    }
}
