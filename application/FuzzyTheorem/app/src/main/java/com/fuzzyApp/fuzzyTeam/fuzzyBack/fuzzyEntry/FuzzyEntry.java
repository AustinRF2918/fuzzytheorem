package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import com.orm.SugarRecord;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class FuzzyEntry extends SugarRecord {
    private String entryName;
    private String entryDescription;
    private ArrayList<String> entryTags;

    public void setName(String name) {
        entryName = name;
    }

    public String getName() {
        return entryName;
    }

    void setDescription(String description) {
        entryDescription = description;
    }

    public String getDescription() {
        return entryDescription;
    }

    public ArrayList<String> getTags() {
        return entryTags;
    }

    public void setTags(ArrayList<String> tagList) {
        entryTags = tagList;
    }

    @Override
    public long save() {
	if (entryName.equals("") || entryName == null) {
	    throw new IllegalStateException("FuzzyEntry's require names to be instantiated.");
	}

	return super.save();
    }

    /**
     * @param tag
     *
     * Adds a tag to our FuzzyEntry. This allows for YouTube style
     * searching and querying.
     */
    public void addTag(String tag) {
        entryTags.add(tag);
    }

    /**
     * @param tag
     * @return
     *
     * Checks for the existence of a tag in a FuzzyEntry. Useful
     * for querying from the frontend during a search.
     */
    public boolean hasTag(String tag) {
        return entryTags.contains(tag);
    }

    /**
     * Uses a clearChild implementation on a child class
     * to clear the entire state of a FuzzyEntry. This clears
     * all of the fields, but does not actually destroy the data
     * base entry. To do this, one must call the commit method.
     */
    public void clear() {
        clearChild();

        entryDescription = "";
        entryName = "";
        entryTags = new ArrayList<>();
    }

    /**
     * @param key
     * @return a boolean value representing the
     * existence of an attribute in a FuzzyEntry.
     *
     */
    public boolean hasAttribute(String key) {
        if (key.equals("name") || key.equals("description")) {
            return true;
        }

        return getAttributeMapChild()
                .keySet()
                .contains(key);
    }

    /**
     * @param key
     * @return a string representing that key entry in a FuzzyEntry.
     * @throws KeyException
     *
     * Resembles the SharedPreferences API. One difference is that
     * this method will throw a KeyException in the case that said
     * key is not found in our FuzzyEntry.
     */
    public String getString(String key) throws KeyException {
        if (key.equals("name")) {
            return entryName;
        } else if (key.equals("description")) {
            return entryDescription;
        } else {
            return getStringChild(key);
        }
    }

    /**
     * @param key
     * @param value
     * @throws KeyException
     *
     * Attempts to pass a key to our FuzzyEntry. This is useful because it
     * allows us to leverage dynamic dispatch across a FuzzyEntry object,
     * assisting in querying from the front end. It is useful because otherwise
     * we would have to maintain lists of different items.
     */
    public void putString(String key, String value) throws KeyException {
        if (key.equals("name")) {
            entryName = value;
        } else if (key.equals("description")) {
            entryDescription = value;
        } else {
            putStringChild(key, value);
        }
    }

    // These are functions that must be implemented by a child
    // of this abstract class to allow the API to work dynamically.

    abstract public String entryType();
    abstract void putStringChild(String key, String value) throws KeyException;
    abstract String getStringChild(String key) throws KeyException;
    abstract HashMap<String, String> getAttributeMapChild();
    abstract void clearChild();
}
