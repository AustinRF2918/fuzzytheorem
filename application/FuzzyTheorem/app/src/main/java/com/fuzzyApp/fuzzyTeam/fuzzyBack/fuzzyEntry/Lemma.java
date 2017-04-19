package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import java.security.KeyException;
import java.util.HashMap;

public class Lemma extends FuzzyEntry {
    private String precondition;
    private String postcondition;

    public Lemma() {
    }

    public Lemma(String precondition, String postcondition) {
        this.precondition = precondition;
        this.postcondition = postcondition;
    }

    /**
     * @param key
     * @param value
     * @throws KeyException
     *
     * Child implementation of putString which attempts to find a field
     * and replace said field with a value. The chain of command will go
     * down to this if the parent class cannot find an item. As a result,
     * if our class genuinely does not have an item, it will throw right here.
     */
    @Override
    void putStringChild(String key, String value) throws KeyException {
        switch (key) {
            case "precondition":
                precondition = value;
                break;
            case "postcondition":
                postcondition = value;
                break;
            default:
                throw new KeyException("The key you entered to a Lemma (" + key + ") does not exist.");
        }
    }

    /**
     * @param key
     * @return
     * @throws KeyException
     *
     * Child implementation of getString, which attempts to find a field
     * and return said field's value. The chain of command will go down to
     * this if the parent class cannot find an item. As a result, if our class
     * genuinely does not have an item, it will throw right here.
     */
    @Override
    String getStringChild(String key) throws KeyException {
        switch (key) {
            case "precondition":
                return precondition;
            case "postcondition":
                return postcondition;
            default:
                throw new KeyException("The key you entered to a Lemma (" + key + ") does not exist.");
        }
    }

    /**
     * Child implementation of clearChild, which attempts to clear all fields of
     * a FuzzyEntries child class. Does not actually refresh the database entry,
     * to do this, one must call commit from the parent class.
     */
    @Override
    void clearChild() {
        precondition = "";
        postcondition = "";
    }

    /**
     * @return Returns a HashMap of a child's local fields. Useful for leveraging dynamic dispatch
     * from the child's parent.
     */
    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap<String, String> currentState = new HashMap<String, String>();
        currentState.put("precondition", precondition);
        currentState.put("postcondition", postcondition);
        return currentState;
    }

    /**
     * @return Returns a stringified version of a FuzzyEntry's name.
     */
    @Override
    public String entryType() {
	return "Lemma";
    }
}
