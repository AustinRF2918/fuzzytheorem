package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzySearcher;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzyTheorem;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Definition;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Other;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Proof;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Theorem;
import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity{
    Fragment newMainFragment;   //newFragment is the next fragment that is going to be placed over the main activity
    FuzzyEntry displayedEntry;
    FragmentTransaction transaction;

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
        replaceMainFragment("Search");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String fragmentName = (String)item.getTitle();
        replaceMainFragment(fragmentName);
        return true;
    }

    protected void onTerminate(Bundle savedInstanceState) {
        SugarContext.terminate();
    }

    private void replaceMainFragment(String fragmentName) {
        try{
            switch (fragmentName) {
                case "Create":
                    newMainFragment = new CreateEntryFragment();
                    break;
                case "Search":
                    newMainFragment = new SearchEntryFragment();
                    break;
                case "Display":
                    newMainFragment = new DisplayEntryFragment();
                    break;
                //TODO safely remove the display option from this method as well as the actual menu
            }
        }
        catch (Exception e){
            System.out.println("trying to replace main activity with invalid fragment name");
        }

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.activity_main, newMainFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        catch (Exception e){
            System.out.println("could not replace main activity with new fragment");
        }
    }


    public FuzzyEntry getDisplayedEntry(){
        return displayedEntry;
    }

    public void setDisplayedEntry(FuzzyEntry entry){

    }
}
