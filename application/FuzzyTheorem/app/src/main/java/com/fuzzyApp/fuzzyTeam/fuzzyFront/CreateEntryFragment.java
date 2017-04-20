package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;


/**
 * Created by Dominic on 4/17/2017.
 */

public class CreateEntryFragment extends Fragment {
    private RelativeLayout entry_fragment_placeholder = null;
    private Spinner entry_type_spinner = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //saying what xml file were using
        View view = inflater.inflate(R.layout.create_entry_fragment, container, false);
        entry_type_spinner = (Spinner) view.findViewById(R.id.entry_type_Spinner);

        return view;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        entry_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)  {

                String entryType = selectedItemView.toString();
                try{
                    renderCreateFuzzyEntryFragment(entryType);
                }
                catch (Exception e){
                    //TODO Add specific exception case and handle properly
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void renderCreateFuzzyEntryFragment(String entryType) throws Exception {
        entry_fragment_placeholder = (RelativeLayout) getView().findViewById(R.id.entry_fragment_placeholder);
        switch (entryType) {
            case "Lemma":
                //render Lemma entry fields
                break;
            case "Definition":
                //render Definition entry fields
                break;
            case "Proof":
                //render Proof entry fields
                break;
            case "Theorem":
                //render Theorem entry fields
                break;
            case "Other":
                //render Other entry fields
                break;
            default:
                throw new Exception("Invalid FuzzyEntry type. Check FuzzyEntry.entryType()");

        }

        }
}




