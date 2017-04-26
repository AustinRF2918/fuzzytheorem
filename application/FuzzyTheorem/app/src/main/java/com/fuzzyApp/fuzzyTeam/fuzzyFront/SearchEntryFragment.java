package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;

import java.util.ArrayList;
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
    private Button addTag;

    // Elements for binding to FuzzySearcher.
    private String currentType;
    private List<String> tagList;
    private ArrayList<FuzzyEntry> resultList;

    private ArrayAdapter<String> tagListAdapter;

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

        tagListView = (ListView) getView().findViewById(R.id.tag_list);
        resultListView = (ListView) getView().findViewById(R.id.result_list);

        entryTitleInput = (TextView) getView().findViewById(R.id.title_entry);
        newTagInput = (TextView) getView().findViewById(R.id.tag_entry);

        typeSelector = (Spinner) getView().findViewById(R.id.type_selector);

        addTag = (Button) getView().findViewById(R.id.add_tag);

        tagList = new LinkedList<>();
        resultList = new ArrayList<>();

        tagListAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, tagList);

        addTag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    addNewTag();
                } catch (IllegalStateException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void refreshCurrentTags() {
        String newType = typeSelector.getSelectedItem().toString();

        if (!newType.equals(currentType)) {
            dirty = true;
        }
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
}