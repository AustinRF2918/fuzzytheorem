package com.fuzzyApp.fuzzyTeam.fuzzyBack;

/**
 * Created by Austin on 4/15/17.
 * To be implemented.
 */

public class FuzzySearcher {
    public FuzzySearcher() {

    }

    public HashSet<FuzzyEntry> getAllFuzzyItems() {
	ArrayList<FuzzyEntry> definitionList = Definition.listAll(Definition.class);
	ArrayList<FuzzyEntry> lemmaList = Lemma.listAll(Lemma.class);
	ArrayList<FuzzyEntry> otherList = Other.listAll(Other.class);
	ArrayList<FuzzyEntry> proofList = Proof.listAll(Proof.class);
	ArrayList<FuzzyEntry> theoremList = Theorem.listAll(Theorem.class);

	ArrayList<FuzzyEntry> allList = new ArrayList<FuzzyEntry>();

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
	    bool entryState = true;

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
