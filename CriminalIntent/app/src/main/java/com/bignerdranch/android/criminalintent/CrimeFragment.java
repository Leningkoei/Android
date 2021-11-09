package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment {

    private Crime crime = null;
    private EditText titleField = null;
    private Button dateButton = null;
    private CheckBox solvedCheckBox = null;
    // private CheckBox

    // Pay attention  onCreate function here is a public function, but in Main-
    //    Activity is a protected function;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.crime = new Crime();
    }
    // Create view;
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view =  inflater.inflate(
            R.layout.fragment_crime,    // view's resources file;
            container,                  // view's parent view;
            false           // 是否立即将生成的视图添加给父视图;
        );

        this.titleField = view.findViewById(R.id.crime_title);
        this.dateButton = view.findViewById(R.id.crime_date);
        this.solvedCheckBox = view.findViewById(R.id.crime_solved);

        this.titleField.addTextChangedListener(
            new TextWatcher() {

                @Override
                public void beforeTextChanged(
                    CharSequence sequence,
                    int start,
                    int count,
                    int after
                ) {
                    // This space intentionally;
                }
                @Override
                public void onTextChanged(
                    CharSequence sequence,
                    int start,
                    int before,
                    int count
                ) {
                    crime.title = sequence.toString();
                }
                @Override
                public void afterTextChanged(Editable sequence) {
                    // This space intentionally;
            }
        });
        this.dateButton.setEnabled(false);
        this.dateButton.setText(crime.date.toString());

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        // this.solvedCheckBox.setOnCheckedChangeListener(
        //     new CompoundButton.OnCheckedChangeListener() {
        //         @Override
        //         public void onCheckedChanged(
        //             CompoundButton compoundButton,
        //             boolean isChecked
        //         ) {
        //             crime.isSolved = isChecked;
        //         }
        //     }
        // );
        this.solvedCheckBox.setOnCheckedChangeListener(
            (compoundButton, isChecked) -> {
                crime.isSolved = isChecked;
            }
        );
    }
}
