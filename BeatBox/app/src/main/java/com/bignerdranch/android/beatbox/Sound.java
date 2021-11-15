/**
 * A class manage sound name, file name, et to users;
 */

package com.bignerdranch.android.beatbox;

public class Sound {

    public String name = null;

    public Sound(String assetPath) {
        String[] path = assetPath.split("/");
        this.name = path[path.length - 1]
            .replace(".wav", "");
    }
}
