package com.bignerdranch.android.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment currentFragment
            = this
                .getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        // // add crime fragment to main activity;
        // if (currentFragment == null) {
        //     Fragment fragment = new CrimeFragment();
        //     this
        //         .getSupportFragmentManager()
        //         .beginTransaction()
        //         .add(R.id.fragment_container, fragment)
        //         .commit();
        // }
        // add crime list fragment to main activity;
        if (currentFragment == null) {
            CrimeListFragment crimeListFragment
                = CrimeListFragment.newInstance();
            this
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, crimeListFragment)
                .commit();
        }
    }
}
