package com.fuzzyApp.fuzzyTeam.fuzzyBack;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Definition;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Other;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Proof;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Theorem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Austin on 4/15/17.
 * To be implemented.
 */

public class FuzzySearcher {
    public FuzzySearcher() {

    }

    public HashSet<FuzzyEntry> getAllFuzzyItems() {
        List<Definition> definitionList = Definition.listAll(Definition.class);
        List<Lemma> lemmaList = Lemma.listAll(Lemma.class);
        List<Other> otherList = Other.listAll(Other.class);
        List<Proof> proofList = Proof.listAll(Proof.class);
        List<Theorem> theoremList = Theorem.listAll(Theorem.class);

        List<FuzzyEntry> allList = new ArrayList<FuzzyEntry>();

        allList.addAll(definitionList);
        allList.addAll(lemmaList);
        allList.addAll(otherList);
        allList.addAll(proofList);
        allList.addAll(theoremList);

        return new HashSet<FuzzyEntry>(allList);
    }

    public HashSet<FuzzyEntry> filterByName(String name) {
        HashSet<FuzzyEntry> filteredSet = new HashSet<FuzzyEntry>();
        HashSet<FuzzyEntry> superSet = getAllFuzzyItems();

        for (FuzzyEntry fuzzyEntry : superSet) {
            if (fuzzyEntry.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredSet.add(fuzzyEntry);
            }
        }

        return filteredSet;
    }

    public HashSet<FuzzyEntry> filterByTags(ArrayList<String> tagList) {
        HashSet<FuzzyEntry> filteredSet = new HashSet<FuzzyEntry>();
        HashSet<FuzzyEntry> superSet = getAllFuzzyItems();

        for (FuzzyEntry fuzzyEntry : superSet) {
            boolean entryState = true;

            for (String tag : tagList) {
                if (!fuzzyEntry.getTags().contains(tag)) {
                    entryState = false;
                }
            }

            if (entryState) {
                filteredSet.add(fuzzyEntry);
            }
        }

        return filteredSet;
    }

    public HashSet<FuzzyEntry> filterByCategory(String category) {
        HashSet<FuzzyEntry> filteredSet = new HashSet<FuzzyEntry>();
        HashSet<FuzzyEntry> superSet = getAllFuzzyItems();

        for (FuzzyEntry fuzzyEntry : superSet) {
            if (fuzzyEntry.entryType().equals(category)) {
                filteredSet.add(fuzzyEntry);
            }
        }

        return filteredSet;
    }
}
