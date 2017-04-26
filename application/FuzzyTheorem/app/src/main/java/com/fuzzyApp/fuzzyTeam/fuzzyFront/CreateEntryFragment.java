package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;


/**
 * Created by Dominic on 4/17/2017.
 */

public class CreateEntryFragment extends Fragment {
    private FlexboxLayout create_placeholder = null;
    private Spinner entry_type_spinner = null;
    private Button add_tag_button = null;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //saying what xml file were using
        View view = inflater.inflate(R.layout.create_entry_fragment, container, false);
        entry_type_spinner = (Spinner) view.findViewById(R.id.entry_type_Spinner);
        add_tag_button = (Button) view.findViewById(R.id.add_tag_Button);

        return view;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        entry_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String entryType = entry_type_spinner.getSelectedItem().toString();
                try {
                    renderCreateFuzzyEntryFragment(entryType);
                    //enable submit button once entryType is chosen
                } catch (Exception e) {
                    //TODO Add specific exception case and handle properly
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //TEMP, MOVE TO REAL ONCLICK LISTERN
    private void addButtonOnClick() {
        EditText entryName = (EditText) getView().findViewById(R.id.entry_title_EditText);
        EditText entryDesc = (EditText) getView().findViewById(R.id.entry_description_EditText);
        ListView entryTags = (ListView) getView().findViewById(R.id.entry_tags_ListView);

    }


    private void renderCreateFuzzyEntryFragment(String entryType) throws Exception {
        create_placeholder = (FlexboxLayout) getView().findViewById(R.id.create_placeholder);
        create_placeholder.removeAllViews(); //resets the placeholder so previous widgets are removed
        ArrayList<View> widgetList = new ArrayList();

        //given the type of FuzzyEntry, the remaining widgets associated with each type will be dynamically rendered
        switch (entryType) {
            case "Lemma":
                widgetList = getLemmaWidgets(entryType);
                for (View widget : widgetList) {
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    create_placeholder.addView(widget);
                }
                break;
            case "Definition":
                widgetList = getDefinitionWidgets(entryType);
                for (View widget : widgetList) {
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    create_placeholder.addView(widget);
                }
                break;
            case "Proof":
                widgetList = getProofWidgets(entryType);
                for (View widget : widgetList) {
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    create_placeholder.addView(widget);
                }
                break;
            case "Theorem":
                widgetList = getTheoremWidgets(entryType);
                for (View widget : widgetList) {
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    create_placeholder.addView(widget);
                }
                break;
            case "Other":
                widgetList = getOtherWidgets(entryType);
                for (View widget : widgetList) {
                    widget.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    create_placeholder.addView(widget);
                }
                break;
            default:
                throw new Exception("Invalid FuzzyEntry type. Check FuzzyEntry.entryType()");

        }
    }





    private ArrayList<View> getDefinitionWidgets(String entryType){
        ArrayList<View> widgetList = new ArrayList();
        EditText entrySymbolContent = new EditText(getActivity());
        EditText entrySymbolReplacer = new EditText(getActivity());

        entrySymbolContent.setHint("Symbol Content");
        entrySymbolReplacer.setHint("Symbol Replacer");


        widgetList.add(entrySymbolContent);
        widgetList.add(entrySymbolReplacer);

        return widgetList;
    }

    private ArrayList<View> getLemmaWidgets(String entryType){
        ArrayList<View> widgetList = new ArrayList();
        EditText entryPreCondition = new EditText(getActivity());
        EditText entryPostCondition = new EditText(getActivity());

        entryPreCondition.setHint("Pre Condition");
        entryPostCondition.setHint("Post Condition");


        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getProofWidgets(String entryType){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryStatementName = new EditText(getActivity());
        EditText entryContent = new EditText(getActivity());

        entryStatementName.setHint("Statment Name");
        entryContent.setHint("Content");


        widgetList.add(entryStatementName);
        widgetList.add(entryContent);

        return widgetList;
    }

    private ArrayList<View> getTheoremWidgets(String entryType){
        ArrayList<View> widgetList = new ArrayList();

        EditText entryPreCondition = new EditText(getActivity());
        EditText entryPostCondition = new EditText(getActivity());

        entryPreCondition.setHint("Pre Condition");
        entryPostCondition.setHint("Post Condition");



        widgetList.add(entryPreCondition);
        widgetList.add(entryPostCondition);

        return widgetList;
    }

    private ArrayList<View> getOtherWidgets(String entryType){
        ArrayList<View> widgetList = new ArrayList();


        EditText entryStatement = new EditText(getActivity());

        entryStatement.setHint("Entry Statement");

        widgetList.add(entryStatement);

        return widgetList;
    }

}

