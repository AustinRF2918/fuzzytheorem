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

import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzySearcher;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Definition;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
            Bundle bundle = this.getArguments();
            String queryName = bundle.getString("item_name");

            FuzzySearcher fuzzySearcher = new FuzzySearcher();

            HashSet<FuzzyEntry> results = fuzzySearcher.filterByName(queryName);

            for (FuzzyEntry entry : results) {
                if (entry.getName().equals(queryName)) {
                    renderFuzzyEntry(entry);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void renderFuzzyEntry(FuzzyEntry entry) throws Exception {
        display_placeholder = (FlexboxLayout) getView().findViewById(R.id.display_placeholder);
        display_placeholder.removeAllViews(); //resets the placeholder so previous widgets are removed
        TextView entryName = (TextView) getView().findViewById(R.id.entry_title_EditText);
        TextView entryDesc = (TextView) getView().findViewById(R.id.entry_description_EditText);
        ArrayList<View> widgetList = new ArrayList();

        //update the name, description, and tags widgets with data from the FuzzyEntry
        entryName.setText(entry.getName());
        entryDesc.setText(entry.getDescription());
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, entry.getTags());


        entryName.setFocusable(false);
        entryDesc.setFocusable(false);

        //given the type of FuzzyEntry, the remaining widgets associated with each type will be dynamically rendered
        switch (entry.entryType()) {
            case "Lemma":
                widgetList = getLemmaWidgets((Lemma)entry);
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Definition":
                widgetList = getDefinitionWidgets((Definition)entry);
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



    private ArrayList<View> getDefinitionWidgets(FuzzyEntry entry) throws Exception{
        ArrayList<View> widgetList = new ArrayList();


        TextView entrySymbolContent = new TextView(getActivity());
        TextView entrySymbolReplacer = new TextView(getActivity());

        entrySymbolContent.setText(entry.getString("symbolContent"));
        entrySymbolReplacer.setText(entry.getString("symbolReplacer"));

        // TODO: Poplate symbolcontent and symbolreplacer widgets with values from the FuzzyEntry


        widgetList.add(entrySymbolContent);
        widgetList.add(entrySymbolReplacer);

        return widgetList;
    }

    private ArrayList<View> getLemmaWidgets(FuzzyEntry entry) throws Exception{
        ArrayList<View> widgetList = new ArrayList();
        TextView entryPreCondition = new TextView(getActivity());
        TextView entryPostCondition = new TextView(getActivity());


        entryPreCondition.setText(entry.getString("precondition"));
        entryPostCondition.setText(entry.getString("postcondition"));

        // TODO: Poplate precondition and postcondition widgets with values from the FuzzyEntry


        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getProofWidgets(FuzzyEntry entry) throws Exception{
        ArrayList<View> widgetList = new ArrayList();

        TextView entryStatementName = new TextView(getActivity());
        TextView entryContent = new TextView(getActivity());

        entryStatementName.setText(entry.getString("statementName"));
        entryContent.setText(entry.getString("content"));


        widgetList.add(entryStatementName);
        widgetList.add(entryContent);

        return widgetList;
    }

    private ArrayList<View> getTheoremWidgets(FuzzyEntry entry) throws Exception{
        ArrayList<View> widgetList = new ArrayList();

        TextView entryPreCondition = new TextView(getActivity());
        TextView entryPostCondition = new TextView(getActivity());


        entryPreCondition.setText(entry.getString("precondition"));
        entryPostCondition.setText(entry.getString("postcondition"));

        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getOtherWidgets(FuzzyEntry entry) throws Exception{
        ArrayList<View> widgetList = new ArrayList();


        TextView entryStatement = new TextView(getActivity());

        entryStatement.setText(entry.getString("statement"));

        widgetList.add(entryStatement);

        return widgetList;
    }
}
