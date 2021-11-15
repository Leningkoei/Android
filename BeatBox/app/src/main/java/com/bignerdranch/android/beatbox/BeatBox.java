/**
 * Res manager class;
 */

package com.bignerdranch.android.beatbox;

import android.content.res.AssetManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeatBox {

    public BeatBox(AssetManager assets) {
        this.assets = assets;
        this.sounds = this.loadSounds();
    }

    private AssetManager assets = null;
    public List<Sound> sounds = null;

    private List<Sound> loadSounds() {
        String[] soundNames = null;
        try {
            // Get all files in dir of path;
            soundNames = assets.list("sample_sounds");
            // Log.d("BeatBox", "Found" + soundNames.length + "sounds");
            // return Arrays.asList(soundNames);
        } catch (Exception e) {
            Log.e("BeatBox", "Could not list assets", e);
            return new ArrayList<>();
        }
        List<Sound> sounds = new ArrayList<>();
        for (String soundName : soundNames) {
            String assetPath = "sample_sounds/" + soundName;
            Sound sound = new Sound(assetPath);
            sounds.add(sound);
        }
        return sounds;
    }
}
