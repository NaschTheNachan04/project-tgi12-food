package de.ghse.tgi.rezepteapp.fragments.Calendar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Calendar.Calendar.MainCalendar;

public class CalendarFragment extends Fragment {
    private final MyViewPagerAdapter pager;
    private View view;
    private final AddEventFragment addEventFragment = new AddEventFragment(this);
    private final MainCalendar calendar = new MainCalendar(this);
    private final Show_Fragment_Day show_fragment_day = new Show_Fragment_Day(this);
    private final ViewRecipeFromCalendar viewRecipe = new ViewRecipeFromCalendar(this);
    private final CalendarSelectRecipe calendarSelectRecipe = new CalendarSelectRecipe(this);
    private int recipeID;
    public static final int CALENDAR = 0;
    public static final int ADD_EVENT_FRAGMENT = 1;
    public static final int SHOW_FRAGMENT_DAY = 2;
    public static final int VIEW_RECIPE =3;
    public static final int SELECT_RECIPE = 4;

    public CalendarFragment(MyViewPagerAdapter p) {
        super();
        pager =p;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(CALENDAR);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        return view;
    }

    public void replaceFragment(int i){
        Fragment frag;
        switch (i){

            case CALENDAR: frag = calendar;
            break;

            case ADD_EVENT_FRAGMENT: frag = addEventFragment;
            break;

            case SHOW_FRAGMENT_DAY: frag = show_fragment_day;
            break;

            case VIEW_RECIPE: frag = viewRecipe;
            break;

            case SELECT_RECIPE: frag = calendarSelectRecipe;
            break;

            default:frag = calendar;
        }

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutCalender,frag);
        fragmentTransaction.commit();
    }

    public void goToDay(int day, int month, int year) {
        show_fragment_day.setDay(day,month,year);
    }

    public void setRecipe(int RID){
        recipeID = RID;
    }

    public int getCurrentRecipe(){
        return recipeID;
    }

    public void setEventRecipe(int ID){
        addEventFragment.addEventRecipe(ID);
    }



}