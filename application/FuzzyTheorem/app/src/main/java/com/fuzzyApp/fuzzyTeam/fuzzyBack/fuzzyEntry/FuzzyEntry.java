package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import com.orm.SugarRecord;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class FuzzyEntry extends SugarRecord {
    String entryName;
    String entryDescription;
    ArrayList<String> entryTags;

    // Easily set and get name of an item in our database
    // These methods apply to any child and should be the same across.
    void setName(String name) {
        entryName = name;
    }

    String getName() {
        return entryName;
    }

    // Easily set and get description of an item in our database
    // These methods apply to any child and should be the same across.
    void setDescription(String description) {
        entryDescription = description;
    }

    String getDescription() {
        return entryDescription;
    }

    /**
     * @param tagList
     *
     * Sets a FuzzyEntry's tags to be an ArrayList of strings.
     * Overwrites the previous array list.
     */
    // Easily set, add, and query tags on our fuzzyEntry objects.
    void setTags(ArrayList<String> tagList) {
        entryTags = tagList;
    }

    /**
     * @param tag
     *
     * Adds a tag to our FuzzyEntry. This allows for YouTube style
     * searching and querying.
     */
    void addTag(String tag) {
        entryTags.add(tag);
    }

    /**
     * @param tag
     * @return
     *
     * Checks for the existence of a tag in a FuzzyEntry. Useful
     * for querying from the frontend during a search.
     */
    boolean hasTag(String tag) {
        return entryTags.contains(tag);
    }

    ArrayList<String> getTags() {
        return entryTags;
    }

    /**
     * Uses a clearChild implementation on a child class
     * to clear the entire state of a FuzzyEntry. This clears
     * all of the fields, but does not actually destroy the data
     * base entry. To do this, one must call the commit method.
     */
    void clear() {
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
    boolean hasAttribute(String key) {
        if (key == "name" || key == "description") {
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
    String getString(String key) throws KeyException {
        if (key == "name") {
            return entryName;
        } else if (key == "description") {
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
    void putString(String key, String value) throws KeyException {
        if (key == "name") {
            entryName = value;
        } else if (key == "description") {
            entryDescription = value;
        } else {
            putStringChild(key, value);
        }
    }

    // These are functions that must be implemented by a child
    // of this abstract class to allow the API to work dynamically.
    abstract void putStringChild(String key, String value) throws KeyException;
    abstract String getStringChild(String key) throws KeyException;
    abstract HashMap<String, String> getAttributeMapChild();
    abstract void clearChild();
}
