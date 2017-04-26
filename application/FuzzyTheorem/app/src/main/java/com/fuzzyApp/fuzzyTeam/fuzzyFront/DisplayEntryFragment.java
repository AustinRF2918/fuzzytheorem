package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fuzzyApp.fuzzyTeam.fuzzyFront.R.id.entry_fragment_placeholder;

/**
 * Created by Dominic on 4/17/2017.
 */

public class DisplayEntryFragment extends Fragment {
    private FlexboxLayout display_placeholder = null;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_entry_fragment, container, false);
        return view;
    }

    //method is currently only used to pass a dummy FuzzyEntry to renderFuzzyEntry
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)   {
        FuzzyEntry pumpingLemma = new Lemma("honestly...", "I never knew what the Yoon was talking about here.");
        pumpingLemma.setName("Pumping Lemma");
        pumpingLemma.setDescription("dummy description");
        ArrayList<String> dummyTags = new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3"));
        pumpingLemma.setTags(dummyTags);


        try{
            renderFuzzyEntry(pumpingLemma);
        }
        catch (Exception e){
            //TODO Add specific exception case and handle properly
        }
    }



    private void renderFuzzyEntry(FuzzyEntry entry) throws Exception {
        display_placeholder = (FlexboxLayout) getView().findViewById(R.id.display_placeholder);
        EditText entryName = (EditText) getView().findViewById(R.id.entry_title_EditText);
        EditText entryDesc = (EditText) getView().findViewById(R.id.entry_description_EditText);
        ListView entryTags = (ListView) getView().findViewById(R.id.entry_tags_ListView);
        ArrayList<View> widgetList = new ArrayList();

        //update the name, description, and tags widgets with data from the FuzzyEntry
        entryName.setText(entry.getName());
        entryDesc.setText(entry.getDescription());
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, entry.getTags());
        entryTags.setAdapter(listAdapter);

        entryName.setFocusable(false);
        entryDesc.setFocusable(false);
        entryTags.setEnabled(false);

        //given the type of FuzzyEntry, the remaining widgets associated with each type will be dynamically rendered
        switch (entry.entryType()) {
            case "Lemma":
                widgetList = getLemmaWidgets(entry);
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Definition":
                widgetList = getDefinitionWidgets(entry);
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Proof":
                widgetList = getProofWidgets(entry);
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Theorem":
                widgetList = getTheoremWidgets(entry);
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Other":
                widgetList = getOtherWidgets(entry);
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            default:
                throw new Exception("Invalid FuzzyEntry type. Check FuzzyEntry.entryType()");

        }
    }



    private ArrayList<View> getDefinitionWidgets(FuzzyEntry entry){
        ArrayList<View> widgetList = new ArrayList();


        TextView entrySymbolContent = new TextView(getActivity());
        TextView entrySymbolReplacer = new TextView(getActivity());


        // TODO: Poplate symbolcontent and symbolreplacer widgets with values from the FuzzyEntry


        widgetList.add(entrySymbolContent);
        widgetList.add(entrySymbolReplacer);

        return widgetList;
    }

    private ArrayList<View> getLemmaWidgets(FuzzyEntry entry){
        ArrayList<View> widgetList = new ArrayList();
        EditText entryPreCondition = new EditText(getActivity());
        EditText entryPostCondition = new EditText(getActivity());


        // TODO: Poplate precondition and postcondition widgets with values from the FuzzyEntry


        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getProofWidgets(FuzzyEntry entry){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryStatementName = new EditText(getActivity());
        EditText entryContent = new EditText(getActivity());

        // TODO: Poplate precondition and postcondition widgets with values from the FuzzyEntry


        widgetList.add(entryStatementName);
        widgetList.add(entryContent);

        return widgetList;
    }

    private ArrayList<View> getTheoremWidgets(FuzzyEntry entry){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryPreCondition = new EditText(getActivity());
        EditText entryPostCondition = new EditText(getActivity());


        // TODO: Poplate precondition and postcondition widgets with values from the FuzzyEntry

        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getOtherWidgets(FuzzyEntry entry){
        ArrayList<View> widgetList = new ArrayList();


        EditText entryStatement = new EditText(getActivity());

        // TODO: Poplate entryStatment widget with values from the FuzzyEntry

        widgetList.add(entryStatement);

        return widgetList;
    }
}
