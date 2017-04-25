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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
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

    private void renderFuzzyEntry(FuzzyEntry entry) throws Exception {
        display_placeholder = (FlexboxLayout) getView().findViewById(R.id.display_placeholder);
        ArrayList<View> widgetList = new ArrayList();


        switch (entry.entryType()) {
            case "Lemma":
                widgetList = getLemmaWidgets();
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Definition":
                widgetList = getDefinitionWidgets();
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Proof":
                widgetList = getProofWidgets();
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Theorem":
                widgetList = getTheoremWidgets();
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            case "Other":
                widgetList = getOtherWidgets();
                for (View widget : widgetList){
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    display_placeholder.addView(widget);
                }
                break;
            default:
                throw new Exception("Invalid FuzzyEntry type. Check FuzzyEntry.entryType()");

        }
    }



    private ArrayList<View> getDefinitionWidgets(){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryName = new EditText(getActivity());
        EditText entryDesc = new EditText(getActivity());
        ListView entryTags = new ListView(getActivity());

        //These will be replaced
        TextView entrySymbolContent = new TextView(getActivity());
        TextView symbolReplacer = new Button(getActivity());



        widgetList.add(entryName);
        widgetList.add(entryDesc);
        widgetList.add(entryTags);
        widgetList.add(entrySymbolContent);
        widgetList.add(symbolReplacer);

        return widgetList;
    }

    private ArrayList<View> getLemmaWidgets(){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryName = new EditText(getActivity());
        EditText entryDesc = new EditText(getActivity());
        ListView entryTags = new ListView(getActivity());

        EditText entryPreCondition = new EditText(getActivity());
        EditText entryPostCondition = new EditText(getActivity());



        widgetList.add(entryName);
        widgetList.add(entryDesc);
        widgetList.add(entryTags);
        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getProofWidgets(){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryName = new EditText(getActivity());
        EditText entryDesc = new EditText(getActivity());
        ListView entryTags = new ListView(getActivity());

        //These will be replaced
        EditText entryStatementName = new EditText(getActivity());
        EditText entryContent = new EditText(getActivity());



        widgetList.add(entryName);
        widgetList.add(entryDesc);
        widgetList.add(entryTags);
        widgetList.add(entryStatementName);
        widgetList.add(entryContent);

        return widgetList;
    }

    private ArrayList<View> getTheoremWidgets(){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryName = new EditText(getActivity());
        EditText entryDesc = new EditText(getActivity());
        ListView entryTags = new ListView(getActivity());


        EditText entryPreCondition = new EditText(getActivity());
        EditText entryPostCondition = new EditText(getActivity());


        widgetList.add(entryName);
        widgetList.add(entryDesc);
        widgetList.add(entryTags);
        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getOtherWidgets(){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryName = new EditText(getActivity());
        EditText entryDesc = new EditText(getActivity());
        ListView entryTags = new ListView(getActivity());


        EditText entryStatement = new EditText(getActivity());

        widgetList.add(entryName);
        widgetList.add(entryDesc);
        widgetList.add(entryTags);
        widgetList.add(entryStatement);

        return widgetList;
    }
}
