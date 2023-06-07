package de.ghse.tgi.rezepteapp.Database;

public class DatabaseReaderRecipe {

    private String nameRecipe;
    private String descritipionRecipe;
    private int    imageRecipe;
    private int    rID;


    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public String getDescritipionRecipe() {
        return descritipionRecipe;
    }

    public void setDescritipionRecipe(String descritipionRecipe) {
        this.descritipionRecipe = descritipionRecipe;

    }
    public int getImageRecipe() {
        return imageRecipe;
    }

    public void setImageRecipe(int imageRecipe) {
        this.imageRecipe = imageRecipe;
    }

    public int getrID() {
        return rID;
    }

    public void setrID(int rID) {
        this.rID = rID;
    }


    public DatabaseReaderRecipe(String nameRecipe, String descritipionRecipe,int imageRecipe,
                          int rID)
    {
        this.nameRecipe=nameRecipe;
        this.descritipionRecipe=descritipionRecipe;
        this.imageRecipe=imageRecipe;
        this.rID=rID;

    }
}
