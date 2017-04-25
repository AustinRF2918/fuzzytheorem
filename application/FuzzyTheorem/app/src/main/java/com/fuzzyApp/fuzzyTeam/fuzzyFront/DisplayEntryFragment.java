package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominic on 4/17/2017.
 */

public class DisplayEntryFragment extends Fragment {
    private RelativeLayout entry_fragment_placeholder = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_entry_fragment, container, false);
        return view;
    }

    private void renderFuzzyEntry(FuzzyEntry entry) throws Exception {
        entry_fragment_placeholder = (RelativeLayout) getView().findViewById(R.id.entry_fragment_placeholder);
        ArrayList<View> widgetList = new ArrayList();


        switch (entry.entryType()) {
            case "Lemma":

                Button btn = new Button(getActivity());
                btn.setText("Manual Add");
                btn.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                entry_fragment_placeholder.addView(btn);
                break;
            case "Definition":
                getDefinitionWidgets();
                break;
            case "Proof":
                break;
            case "Theorem":
                break;
            case "Other":
                break;
            default:
                throw new Exception("Invalid FuzzyEntry type. Check FuzzyEntry.entryType()");

        }
    }



    private ArrayList<View> getDefinitionWidgets(){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryName = new EditText(getActivity());
        entryName.setEnabled(false);

        EditText entryDesc = new EditText(getActivity());
        entryDesc.setEnabled(false);

        






        widgetList.add();



        return
    }


}
