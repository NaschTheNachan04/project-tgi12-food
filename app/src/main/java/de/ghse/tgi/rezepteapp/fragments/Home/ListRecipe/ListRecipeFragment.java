package de.ghse.tgi.rezepteapp.fragments.Home.ListRecipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.fragments.Home.HomeFragment;

/**
 * A {@link Fragment} subclass.
 * View (MVC) of {@link ListRecipeControl}.
 * Contains a ListView showing the {@link de.ghse.tgi.rezepteapp.Recipe} saved.
 */
public class ListRecipeFragment extends Fragment {
    private ListRecipeControl ctrl;
    private FloatingActionButton fab;
    private EditText etSearchRecipe;
    private ListView list;
    private View view;
    private HomeFragment homeFragment;
    private ListRecipeListViewAdapter adapter;
    private int clickedItem = 0;


    /**
     * Class constructor.
     * implements the associated homeFragment for navigation between Fragments.
     *
     * @param homeFragment HomeFragment to notify when page should be changed.
     */
    public ListRecipeFragment(HomeFragment homeFragment){
        super();
        this.homeFragment =homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_list_recipe, container, false);

        list = view.findViewById(R.id.listViewRecipe);
        adapter = new ListRecipeListViewAdapter(homeFragment.getMainActivity());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedItem = i;                                                                  //index
                homeFragment.replaceFragment(2);                                                // on click on ListViewItem, show Recipe at index.
            }
        });
        ctrl = new ListRecipeControl(this,adapter);

        fab =  view.findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.replaceFragment(1);                                                // if fab is clicked, open InputFragment
            }
        });

        etSearchRecipe = view.findViewById(R.id.etSearchRecipe);
        etSearchRecipe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ctrl.filter();                                                                    // if searchedText is edited, update ListView.
            }
        });

        return view;
    }

    /**
     *
     * @return Return the SearchText the {@link de.ghse.tgi.rezepteapp.Recipe}s should be filtered with.
     */
    public String getSearchText(){
        return etSearchRecipe.getText().toString();
    }

    /**
     * A method to get the latest clicked Item of {@link ListView}.
     * @return The index of the latest clicked ListviewItem
     */
    public  int getClickedItem(){
        return clickedItem;
    }

    /**
     * Has to be called when dataSet has been changed.
     * Updates the Listview
     */
    public void dataSetChanged(){
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}