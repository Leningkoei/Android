package com.bignerdranch.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.util.*

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment: Fragment?
            = supportFragmentManager.findFragmentById(R.id.fragment_container);
        if (currentFragment == null) {
            // val fragment: CrimeFragment = CrimeFragment();
            val fragment: CrimeListFragment = CrimeListFragment.newInstance();
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
    override fun onCrimeSelected(crimeId: UUID) {
        // val fragment: CrimeFragment = CrimeFragment();
        val fragment: CrimeFragment = CrimeFragment.newInstance(crimeId);
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit();
    }
}
