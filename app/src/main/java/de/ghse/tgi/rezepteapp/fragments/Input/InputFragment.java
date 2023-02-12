package de.ghse.tgi.rezepteapp.fragments.Input;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import de.ghse.tgi.rezepteapp.MyViewPagerAdapter;
import de.ghse.tgi.rezepteapp.R;


public class InputFragment extends Fragment {
    private Button btSave;
    private EditText etName;
    private EditText etDescription;
    private View view;
    private MyViewPagerAdapter pager;
    private InputControll controllInput;

    public InputFragment(){super();}

    public InputFragment(MyViewPagerAdapter p) {
        super();
        pager = p;
        controllInput = new InputControll(this,p.getMainActivity().getStorage());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_input, container, false);
        etName = view.findViewById(R.id.etInputName);
        etDescription = view.findViewById(R.id.etInputDescription);
        btSave = view.findViewById(R.id.bSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controllInput.save();
                pager.getMainActivity().setFrag(0);
            }
        });
        return view;
    }
    public String getRecipeName(){
        return etName.getText().toString();
    }
    public String getRecipeDescription(){
        return etDescription.getText().toString();
    }
    public ArrayList<String> getRecipeIngredients(){
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
    public void clearTextFields(){
        etName.setText(' ');
        etDescription.setText(' ');
    }
}