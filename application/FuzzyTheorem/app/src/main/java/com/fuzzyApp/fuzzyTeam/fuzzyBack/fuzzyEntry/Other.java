package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import java.security.KeyException;
import java.util.HashMap;

public class Other extends FuzzyEntry {
    private String statement;

    public Other(String statement) {
        this.statement = statement;
    }

    @Override
    void putStringChild(String key, String value) throws KeyException {
        switch (key) {
            case "statement":
                statement = value;
                break;
            default:
                throw new KeyException("The key you entered to a Other (" + key + ") does not exist.");
        }
    }

    @Override
    String getStringChild(String key) throws KeyException {
        switch (key) {
            case "statement":
                return statement;
            default:
                throw new KeyException("The key you entered to a Other (" + key + ") does not exist.");
        }
    }

    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap<String, String> currentState = new HashMap<String, String>();
        currentState.put("statement", statement);
        return currentState;
    }

    @Override
    void clearChild() {
        statement = "";
    }
}
