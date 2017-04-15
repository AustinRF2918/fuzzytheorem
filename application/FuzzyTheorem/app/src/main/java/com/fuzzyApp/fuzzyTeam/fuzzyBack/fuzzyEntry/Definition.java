package com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry;

import java.security.KeyException;
import java.util.HashMap;

public class Definition extends FuzzyEntry {
    private String symbolContent;
    private String symbolReplacer;

    public Definition(String symbolContent, String symbolReplacer) {
        this.symbolContent = symbolContent;
        this.symbolReplacer = symbolReplacer;
    }

    @Override
    void putStringChild(String key, String value) throws KeyException {
        switch (key) {
            case "symbolContent":
                symbolContent = value;
                break;
            case "symbolReplacer":
                symbolReplacer = value;
                break;
            default:
                throw new KeyException("The key you entered to a Lemma (" + key + ") does not exist.");
        }
    }

    @Override
    String getStringChild(String key) throws KeyException {
        switch (key) {
            case "symbolContent":
                return symbolContent;
            case "symbolReplacer":
                return symbolReplacer;
            default:
                throw new KeyException("The key you entered to a Lemma (" + key + ") does not exist.");
        }
    }

    @Override
    HashMap<String, String> getAttributeMapChild() {
        HashMap<String, String> currentState = new HashMap<String, String>();
        currentState.put("symbolContent", symbolContent);
        currentState.put("symbolReplacer", symbolReplacer);
        return currentState;
    }

    @Override
    void clearChild() {
        symbolContent = "";
        symbolReplacer = "";
    }
}
