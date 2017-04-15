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

    // Easily set, add, and query tags on our fuzzyEntry objects.
    void setTags(ArrayList<String> tagList) {
        entryTags = tagList;
    }

    void addTag(String tag) {
        entryTags.add(tag);
    }

    boolean hasTag(String tag) {
        return entryTags.contains(tag);
    }

    ArrayList<String> getTags() {
        return entryTags;
    }

    // TODO: Document this function.
    void clear() {
        clearChild();

        entryDescription = "";
        entryName = "";
        entryTags = new ArrayList<>();
    }

    // TODO: Document this function.
    boolean hasAttribute(String key) {
        if (key == "name" || key == "description") {
            return true;
        }

        return getAttributeMapChild()
                .keySet()
                .contains(key);
    }

    // TODO: Document this function.
    String getString(String key) throws KeyException {
        if (key == "name") {
            return entryName;
        } else if (key == "description") {
            return entryDescription;
        } else {
            return getStringChild(key);
        }
    }

    // TODO: Document this function.
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
