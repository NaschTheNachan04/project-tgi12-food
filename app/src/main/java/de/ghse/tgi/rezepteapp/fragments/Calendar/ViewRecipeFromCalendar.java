package de.ghse.tgi.rezepteapp.fragments.Calendar;

import de.ghse.tgi.rezepteapp.fragments.fragmentclasses.ViewRecipe.ViewRecipe.ViewRecipeFragment;

public class ViewRecipeFromCalendar extends ViewRecipeFragment {
    private final CalendarFragment main;

    public ViewRecipeFromCalendar(CalendarFragment main) {
        this.main = main;
    }


    /**
     * method is called to leave this View
     */
    @Override
    protected void returnToPreviousFragment() {
        main.replaceFragment(CalendarFragment.SHOW_FRAGMENT_DAY);
    }

    /**
     * method is called to get the Recipe it should show. <p>
     * It is mandatory to call {@link  #ctrl#setRecipe(int)} during this process.
     */
    @Override
    protected void getItemID() {
        itemId = main.getCurrentRecipe();
    }
}
