package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzySearcher;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;

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

        tagListAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, tagList);
        tagListView.setAdapter(tagListAdapter);
        tagListView.setOnItemClickListener(removeTag());

        addTagButton.setOnClickListener(addTag());
    }

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
        HashSet<FuzzyEntry> typeMatches = search.filterByCategory(typeSelector.getSelectedItem().toString());

        if (tagList.isEmpty()) {
            HashSet<FuzzyEntry> tagMatches = search.filterByTags((ArrayList<String>) tagList);
        } else {
        }
    }
}