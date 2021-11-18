package com.bignerdranch.android.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.util.*

class CrimeFragment() : Fragment(), DatePickerFragment.Callbacks {

    companion object {
        // MainActivity 应该调用 CrimeFragment.newInstance(UUID) 创建 CrimeFragment;
        fun newInstance(crimeId: UUID): CrimeFragment {
            val args: Bundle = Bundle();
            args.putSerializable("crime_id", crimeId);
            val crimeFragment: CrimeFragment = CrimeFragment();
            crimeFragment.arguments = args;
            return crimeFragment;
        }
    }

    private val crimeDetailViewModel by lazy {
        ViewModelProvider(this).get(CrimeDetailViewModel::class.java);
    }
    private lateinit var crime: Crime;
    private lateinit var titleField: EditText;
    private lateinit var dateButton: Button;
    private lateinit var solvedCheckBox: CheckBox;

    private fun updateUI() {
        this.titleField.setText(this.crime.title);
        this.dateButton.text = this.crime.date.toString();
        this.solvedCheckBox.isChecked = this.crime.isSolved;
        this.solvedCheckBox.jumpDrawablesToCurrentState();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        this.crime = Crime();
        val crimeId: UUID = arguments?.getSerializable("crime_id") as UUID;
        this.crimeDetailViewModel.loadCrime(crimeId);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_crime,
            container,
            false
        )
        this.titleField = view.findViewById(R.id.crime_title) as EditText;
        this.dateButton = view.findViewById(R.id.crime_date) as Button;
        // this.dateButton.text = crime.date.toString();
        // this.dateButton.isEnabled = false;
        this.solvedCheckBox = view.findViewById(R.id.crime_solved);
        return view;
    }
    override fun onViewCreated(view: View, savedinstanceState: Bundle?) {
        super.onViewCreated(view, savedinstanceState);

        this.crimeDetailViewModel.crimeLiveData.observe(
            this.viewLifecycleOwner,
            object : Observer<Crime?> {
                override fun onChanged(crime: Crime?) {
                    crime?.let {
                        // this@CrimeFragment.crime = it;
                        // this@CrimeFragment.updateUI();
                        // val that: CrimeFragment = this@CrimeFragment;
                        // that.crime = it;
                        // that.updateUI();
                        this@CrimeFragment.apply {
                            this.crime = it;
                            this.updateUI();
                        }
                    }
                }
            }
        )
    }
    override fun onStart() {
        super.onStart();

        this.titleField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // 感觉, 这个类似注解的 this@xxx.yyy 比爪哇的 xxx.this.yyy好多了阿;
                this@CrimeFragment.crime.title = sequence.toString();
            }
            override fun afterTextChanged(sequence: Editable?) {
                //
            }
        })
        this.dateButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(view: View) {
                    val datePickerFragment: DatePickerFragment =
                        DatePickerFragment
                            .newInstance(this@CrimeFragment.crime.date);
                    datePickerFragment.setTargetFragment(
                        this@CrimeFragment,
                        0
                    )
                    datePickerFragment.show(
                        this@CrimeFragment.parentFragmentManager,
                        "DialogDate"
                    )
                }
            }
        )
        this.solvedCheckBox.setOnCheckedChangeListener( // 可以写成 lambda;
            // 只有实现1个 method 的时候才可以用 lambda 简化匿名内部类🐎?
            object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(
                    compoundButton: CompoundButton,
                    isChecked: Boolean
                ) {
                    this@CrimeFragment.crime.isSolved = isChecked;
                }
            }
        )
    }
    override fun onStop() {
        super.onStop();

        this.crimeDetailViewModel.saveCrime(this.crime);
    }
    override fun onDateSelected(date: Date) {
        this.crime.date = date;
        this.updateUI();
    }
}
