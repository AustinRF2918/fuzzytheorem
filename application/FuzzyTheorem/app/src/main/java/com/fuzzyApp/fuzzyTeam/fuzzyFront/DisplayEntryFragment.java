package com.fuzzyApp.fuzzyTeam.fuzzyFront;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dominic on 4/17/2017.
 */

public class DisplayEntryFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //saying what xml file were using
        View view = inflater.inflate(R.layout.display_entry_fragment, container, false);

        return view;
    }
    public String getFragmentName(){
        return "display";
    }
}
