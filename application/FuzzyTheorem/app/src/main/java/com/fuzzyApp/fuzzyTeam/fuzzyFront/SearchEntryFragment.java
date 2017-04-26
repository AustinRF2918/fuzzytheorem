package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzySearcher;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Theorem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dominic on 4/17/2017.
 * Modified by Austin on 4/25/2017
 */

public class SearchEntryFragment extends Fragment {
    // Local UI elements to be instantiated onCreate.
    // We will use these in conjunction with the FuzzySearcher
    // API to give a really cohesive search experience.

    private ListView tagListView;
    private ListView resultListView;
    private TextView entryTitleInput;
    private TextView newTagInput;
    private Spinner typeSelector;
    private Button addTagButton;

    // Elements for binding to FuzzySearcher.
    private String currentType;
    private List<String> tagList;
    private ArrayList<FuzzyEntry> resultList;
    private FuzzySearcher search;


    private ArrayAdapter<String> tagListAdapter;

    private ArrayAdapter<FuzzyEntry> resultListAdapter; //Dominic
    private ArrayAdapter fuzzyItemAdapter;

    // Requerying on every search would SUCK performance-wise.
    float searchThreshold;
    boolean dirty;
    boolean tagsEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_entry_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        searchThreshold = 0.25f;
        dirty = false;
        tagsEmpty = true;
        currentType = "Lemma";
        search = new FuzzySearcher();

        tagListView = (ListView) getView().findViewById(R.id.tag_list);
        resultListView = (ListView) getView().findViewById(R.id.result_list);

        entryTitleInput = (TextView) getView().findViewById(R.id.title_entry);
        newTagInput = (TextView) getView().findViewById(R.id.tag_entry);

        typeSelector = (Spinner) getView().findViewById(R.id.type_selector);

        addTagButton = (Button) getView().findViewById(R.id.add_tag);

        tagList = new LinkedList<>();
        resultList = new ArrayList<>();
        FuzzyEntry x = new Theorem("Hello", "world");
        resultList.add(x);



        tagListAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, tagList);
        tagListView.setAdapter(tagListAdapter);
        tagListView.setOnItemClickListener(removeTag());


        resultListAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1,  resultList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(resultList.get(position).getName());
                text2.setText(resultList.get(position).getDescription());
                return view;
            }



        }; //Dominic

        resultListView.setAdapter(resultListAdapter);

        //resultListView.setListAdapter(new ArrayAdapter<String[]>(this, android.R.layout.simple_list_item_2, R.id.result_list, resultList) {

        };




    /*
        resultListView.setAdapter(fuzzyItemAdapter);
        addTagButton.setOnClickListener(addTag());

        entryTitleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                performSearch();
            }
        });


    }

    */

    @NonNull
    private View.OnClickListener addTag() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    addNewTag();
                } catch (IllegalStateException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @NonNull
    private AdapterView.OnItemClickListener removeTag() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = tagList.get(position);
                try {
                    removeTag(item);
                } catch (IllegalStateException e)  {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void addNewTag() throws IllegalStateException {
        String currentTag = newTagInput.getText().toString();

        if (currentTag.equals("")) {
            throw new IllegalStateException("Current tag must be nonempty.");
        }

        for (String tag : tagList) {
            if (currentTag.equals(tag)) {
                throw new IllegalStateException("Current tag must be unique to tags already entered.");
            }
        }

        tagList.add(currentTag);
        tagListAdapter.notifyDataSetChanged();
    }

    public void removeTag(String tag) throws  IllegalStateException {
        boolean result = tagList.remove(tag);

        if (result) {
            tagListAdapter.notifyDataSetChanged();
        } else {
            throw new IllegalStateException("Tag passed to removeTag does not exist.");
        }
    }

    public void performSearch() {
        HashSet<FuzzyEntry> nameMatches = search.filterByName(entryTitleInput.getText().toString());
        System.out.println(nameMatches);

        HashSet<FuzzyEntry> typeMatches = search.filterByCategory(typeSelector.getSelectedItem().toString());
        System.out.println(typeMatches);

        HashSet<FuzzyEntry> intersection = new HashSet<FuzzyEntry>(nameMatches);
        intersection.retainAll(typeMatches);

        if (tagList.isEmpty()) {
            ArrayList convertedTagList = new ArrayList<>();
            convertedTagList.addAll(tagList);
            HashSet<FuzzyEntry> tagMatches = search.filterByTags(convertedTagList);
            System.out.println(tagMatches);
            intersection.retainAll(tagMatches);
        }

        resultList.clear();

        for (FuzzyEntry entry : resultList) {
            resultList.add(entry);
        }

        fuzzyItemAdapter.notifyDataSetChanged();
    }
}