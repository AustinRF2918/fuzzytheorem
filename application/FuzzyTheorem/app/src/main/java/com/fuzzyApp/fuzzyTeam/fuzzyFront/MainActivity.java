package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Definition;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Other;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Proof;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Theorem;
import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity {

    private void generateSugarTables() {
        // You gotta generate them Sugar Tables man
        Definition.findById(Definition.class, (long) 1);
        Lemma.findById(Lemma.class, (long) 1);
        Other.findById(Other.class, (long) 1);
        Proof.findById(Proof.class, (long) 1);
        Theorem.findById(Theorem.class, (long) 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        generateSugarTables();
        setContentView(R.layout.activity_main);




        //Load up fragment
        // Create a new Fragment to be placed in the activity layout
        CreateEntryFragment createEntryFragment = new CreateEntryFragment();
        // Add the fragment to the 'fragment_container' FrameLayout

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.activity_main, createEntryFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }

    protected void onTerminate(Bundle savedInstanceState) {
        SugarContext.terminate();
    }



}
