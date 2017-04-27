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
 */

public class FuzzySearcher {
    /**
     * @return A HashSet with all the unique FuzzyEntries current loaded.
     *
     * Gets every FuzzyItem that is currently serialized in our
     * databaase and deserializes them as a hashset.
     *
     * Important note: Make sure to use this in conjunction
     * with the Java Set API to do unions and intersections during
     * queries for items.
     */
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

        return new HashSet<>(allList);
    }

    /**
     * @param name
     * @return A HashSet with unique FuzzyEntries.
     * 
     * Gets every FuzzyItem that is currently serialized in our
     * databaase and deserializes them as a hashset.
     *
     * Important note: Make sure to use this in conjunction
     * with the Java Set API to do unions and intersections during
     * queries for items.
     */
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

    /**
     * @param tagList
     * @return A HashSet with unique FuzzyEntries.
     * 
     * Takes a list of tags (which are strings) and 
     * filters our local database for items with that tag.
     *
     * Important note: Make sure to use this in conjunction
     * with the Java Set API to do unions and intersections during
     * queries for items.
     */
    public HashSet<FuzzyEntry> filterByTags(ArrayList<String> tagList) {
	// TODO: Perhaps make this a variable length function
	// so we don't have to constantly pass in ArrayLists.

        HashSet<FuzzyEntry> filteredSet = new HashSet<FuzzyEntry>();
        HashSet<FuzzyEntry> superSet = getAllFuzzyItems();

        for (FuzzyEntry fuzzyEntry : superSet) {
            boolean entryState = true;

            for (String tag : tagList) {
                if (fuzzyEntry.getTags() == null || !fuzzyEntry.getTags().contains(tag)) {
                    entryState = false;
                }
            }

            if (entryState) {
                filteredSet.add(fuzzyEntry);
            }
        }

        return filteredSet;
    }

    /**
     * @param category
     * @return A HashSet with unique FuzzyEntries.
     * 
     * Takes a single string which represents a category of 
     * FuzzyEntries (currently Definition, Lemma, Other, Proof, Theorem.)
     *
     * Important note: Make sure to use this in conjunction
     * with the Java Set API to do unions and intersections during
     * queries for items.
     */

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
