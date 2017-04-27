package com.fuzzyApp.fuzzyTeam.fuzzyBack;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Definition;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Other;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Proof;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Theorem;

/**
 * Created by Austin on 4/15/17.
 * To be implemented.
 */

public class FuzzyTheorem {
    /**
     * Highly  mutable function that clears application state.
     * Use with caution!
     */
    public void clear() {
        Definition.deleteAll(Definition.class);
        Lemma.deleteAll(Lemma.class);
        Other.deleteAll(Other.class);
        Proof.deleteAll(Proof.class);
        Theorem.deleteAll(Theorem.class);
    }
}
