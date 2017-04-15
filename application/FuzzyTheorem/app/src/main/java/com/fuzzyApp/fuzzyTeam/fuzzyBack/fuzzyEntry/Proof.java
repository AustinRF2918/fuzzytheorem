package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import java.security.KeyException;
import java.util.HashMap;

public class Proof extends FuzzyEntry {
    private String statementName;
    private String content;
    // TODO: Add relation String for rendering on frontend. Enumerator
    // is too constrained: Just use string and handle it from frontend.

    public Proof(String precondition, String postcondition) {
        this.statementName = precondition;
        this.content = postcondition;
    }

    @Override
    void putStringChild(String key, String value) throws KeyException {
        switch (key) {
            case "statementName":
                statementName = value;
                break;
            case "content":
                content = value;
                break;
            default:
                throw new KeyException("The key you entered to a Proof (" + key + ") does not exist.");
        }
    }

    @Override
    String getStringChild(String key) throws KeyException {
        switch (key) {
            case "statementName":
                return statementName;
            case "content":
                return content;
            default:
                throw new KeyException("The key you entered to a Proof (" + key + ") does not exist.");
        }
    }

    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap<String, String> currentState = new HashMap<String, String>();
        currentState.put("statementName", statementName);
        currentState.put("content", content);
        return currentState;
    }

    @Override
    void clearChild() {
        statementName = "";
        content = "";
    }
}
