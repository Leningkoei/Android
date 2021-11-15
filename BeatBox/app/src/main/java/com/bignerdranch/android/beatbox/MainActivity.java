package com.bignerdranch.android.beatbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.databinding.ActivityMainBinding;
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BeatBox beatBox = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        this.beatBox = new BeatBox(getAssets());
        // beatBox.loadSounds();
        ActivityMainBinding binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        );
        binding.recyclerView.setLayoutManager(new GridLayoutManager(
            this,
            3
        ));
        binding.recyclerView.setAdapter(new SoundAdapter(this.beatBox.sounds));
    }

    private static class SoundHolder extends RecyclerView.ViewHolder {

        private SoundHolder(@NonNull ListItemSoundBinding binding) {
            super(binding.getRoot());
        }
    }
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private SoundAdapter(List<Sound> sounds) {
            this.sounds = sounds;
        }

        private List<Sound> sounds = null;

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
        ) {
            ListItemSoundBinding binding = DataBindingUtil.inflate(
                MainActivity.this.getLayoutInflater(),
                R.layout.list_item_sound,
                parent,
                false
            );
            return new SoundHolder(binding);
        }
        @Override
        public void onBindViewHolder(
            @NonNull SoundHolder holder,
            int position) {
        }
        @Override
        public int getItemCount() {
            return this.sounds.size();
        }
    }
}
