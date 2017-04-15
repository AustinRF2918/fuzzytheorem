package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import java.security.KeyException;
import java.util.HashMap;

public class Lemma extends FuzzyEntry {
    private String precondition;
    private String postcondition;

    // TODO: Add relation String for rendering on frontend. Enumerator
    // is too constrained: Just use string and handle it from frontend.

    public Lemma(String precondition, String postcondition) {
        this.precondition = precondition;
        this.postcondition = postcondition;
    }

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

    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap<String, String> currentState = new HashMap<String, String>();
        currentState.put("precondition", precondition);
        currentState.put("postcondition", postcondition);
        return currentState;
    }

    @Override
    void clearChild() {
        precondition = "";
        postcondition = "";
    }
}
