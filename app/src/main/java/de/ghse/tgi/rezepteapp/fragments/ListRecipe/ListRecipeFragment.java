package de.ghse.tgi.rezepteapp.fragments.ListRecipe;

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

import de.ghse.tgi.rezepteapp.MainActivity;
import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;
import de.ghse.tgi.rezepteapp.StorageRecipe;


public class ListRecipeFragment extends Fragment {
    private ListRecipeControl ctrl;
    private FloatingActionButton fab;
    private StorageRecipe storage;
    private EditText etSearchRecipe;
    private ListView list;
    private View view;
    private MyViewPagerAdapter pager;
    private static int clickedItem;


    public ListRecipeFragment(MyViewPagerAdapter p){
        super();
        pager = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storage = MainActivity.getStorage();
        view =inflater.inflate(R.layout.fragment_list_recipe, container, false);

        list = view.findViewById(R.id.listViewRecipe);
        ListRecipeListViewAdapter adapter = new ListRecipeListViewAdapter(pager.getMainActivity());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedItem = i;
                etSearchRecipe.setText("");
                pager.getMainActivity().setFrag(2);
            }
        });
        ctrl = new ListRecipeControl(this,pager,adapter);

        fab =  view.findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.getMainActivity().setFrag(1);
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
                ctrl.filter();
            }
        });

        return view;
    }
    public String getSearchText(){
        return etSearchRecipe.getText().toString();
    }
    public static int getClickedItem(){
        return clickedItem;
    }
}