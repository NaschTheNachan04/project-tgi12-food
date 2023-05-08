package de.ghse.tgi.rezepteapp.fragments.Home.ViewRecipe;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.StorageRecipe;

/**
 * Controller(VMC) of {@link ViewRecipeFragment} (View)
 */
public class ViewRecipeControl {
    private ViewRecipeFragment gui;
    private StorageRecipe storage;
    private int recipeID;
    private double[] ingredientAmount;
    private String[] ingredientUnit;
    private String[] ingredientName;

    /**
     * Class constructor.
     * @param gui
     */
    public ViewRecipeControl(ViewRecipeFragment gui){
        this.gui = gui;
        this.storage = MainActivity.getStorage();
    }

    /**
     * method that updates View to show {@link de.ghse.tgi.rezepteapp.Recipe}  given index.
     *
     * @param itemId index of the {@link de.ghse.tgi.rezepteapp.Recipe} tht should be shown.
     */
    public void onCreate(int itemId){
        recipeID = itemId;
        gui.setDescription(storage.getRecipeDescription(itemId));           //set the description of selected Recipe
        gui.setRName(storage.getRecipeName(itemId));                        //set the Name of selected Recipe
        gui.setImage(storage.getRecipeImage(itemId));                       //set the Picture of selected Recipe
        ingredientAmount = MainActivity.getStorage().getIngredientsAmount(itemId);
        ingredientUnit = MainActivity.getStorage().getIngredientsUnit(itemId);
        ingredientName = MainActivity.getStorage().getIngredientsName(itemId);
    }
    public void setListViewHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        int width = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        if (adapter.getCount()!= 0) {
            int height  = 0;
            for (int i = 0; i< adapter.getCount();i++) {
                View listItem = adapter.getView(0, null, listView);
                listItem.measure(width, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            height += (listView.getDividerHeight())*(adapter.getCount()-1);
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = height;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

    public int getIngredientCount(){return ingredientName.length;}
    public String getIngredientName(int pos){return ingredientName[pos];}
    public String getIngredientUnit(int pos){return ingredientUnit[pos];}
    public double getIngredientAmount(int pos){return ingredientAmount[pos];}
}