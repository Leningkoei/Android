package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {

    public UUID id = UUID.randomUUID();
    public String title = "";
    public Date date = new Date();
    public boolean isSolved = false;
}
