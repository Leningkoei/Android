package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment() : DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date);
    }

    companion object {

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle();
            args.putSerializable("date", date);
            val datePickerFragment: DatePickerFragment =  DatePickerFragment();
            datePickerFragment.arguments = args;
            return datePickerFragment;
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                datePicker: DatePicker,
                year: Int,
                month: Int,
                day: Int
            ) {
                val resultDate: Date = GregorianCalendar(year, month, day).time;
                this@DatePickerFragment.targetFragment?.let {
                    (it as Callbacks).onDateSelected(resultDate);
                }
            }
        }
        val calendar = Calendar.getInstance();
        val date = this.arguments?.getSerializable("date") as Date;
        calendar.time = date;
        val initialYear: Int = calendar.get(Calendar.YEAR);
        val initialMonth: Int = calendar.get(Calendar.MONTH);
        val initialDay: Int = calendar.get(Calendar.DAY_OF_MONTH);
        return DatePickerDialog(
            this.requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }
}
