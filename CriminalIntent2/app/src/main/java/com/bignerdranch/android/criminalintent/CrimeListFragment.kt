package com.bignerdranch.android.criminalintent

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CrimeListFragment() : Fragment() {

    /**
     * required interface for hosting activities;
     */
    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID);
    }
    private var callbacks: Callbacks? = null;

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment();
        }
    }

    // about lazy: https://www.jianshu.com/p/e2cb4c65d4ff;
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java);
    }
    private lateinit var crimeRecyclerView: RecyclerView;
    // private lateinit var adapter: CrimeAdapter;
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList());

    /*
    private fun updateUI() {
        val crimes = this.crimeListViewModel.crimes;
        this.adapter = CrimeAdapter(crimes);
        this.crimeRecyclerView.adapter = this.adapter;
    }
    */
    private fun updateUI(crimes: List<Crime>) {
        this.adapter = CrimeAdapter(crimes);
        this.crimeRecyclerView.adapter = this.adapter;
    }

    // 当 fragment 附加到 activity 时, 会调用 Fragment.onAttach(Context) 生命周期函数;
    override fun onAttach(context: Context) {
        super.onAttach(context);

        this.callbacks = context as Callbacks?; // 类型转换?;
    }
    /*
    // Kotlin 是不是调了 super.xxx 就不用加入 override fun 修饰 method 了呢;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
    }
    */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savecdInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_crime_list,
            container,
            false
        )
        this.crimeRecyclerView =
            view.findViewById(R.id.crime_recycler_view) as RecyclerView;
        this.crimeRecyclerView.layoutManager = LinearLayoutManager(context);
        // this.updateUI();
        this.crimeRecyclerView.adapter = this.adapter;
        return view;
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }
    override fun onViewCreated(view: View, savecdInstanceState: Bundle?) {
        super.onViewCreated(view, savecdInstanceState);

        this.crimeListViewModel.crimeListLiveData.observe(
            this.viewLifecycleOwner,
            object : Observer<List<Crime>?> {
                override fun onChanged(crimes: List<Crime>?) {
                    crimes?.let {
                        this@CrimeListFragment.updateUI(it);
                    }
                }
            }
        )
    }
    // 当 fragment 快脱离 activity 时, 会调用 Fragment.onDetach() 生命周期函数;
    override fun onDetach() {
        super.onDetach();

        this.callbacks = null;
    }

    private inner class CrimeHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var crime: Crime;
        private val titleTextView: TextView =
            this.itemView.findViewById(R.id.crime_title);
        private val dateTextView: TextView =
            this.itemView.findViewById(R.id.crime_date);
        private val solvedImageView: ImageView =
            this.itemView.findViewById(R.id.crime_solved);

        fun bind(crime: Crime) {
            this.crime = crime;
            this.titleTextView.text = this.crime.title;
            this.dateTextView.text = this.crime.date.toString();
            this.solvedImageView.visibility =
                if (crime.isSolved) {
                    View.VISIBLE;
                } else {
                    View.GONE;
                }
        }

        init {
            this.itemView.setOnClickListener(this);
        }

        override fun onClick(view: View) {
            /*
            Toast.makeText(
                context,
                "${this.crime.title} pressed!",
                Toast.LENGTH_SHORT
            ).show();
            */
            this@CrimeListFragment.callbacks?.onCrimeSelected(this.crime.id);
        }
    }
    private inner class CrimeAdapter(var crimes: List<Crime>):
        RecyclerView.Adapter<CrimeHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CrimeHolder {
            val view = this@CrimeListFragment.layoutInflater.inflate(
                R.layout.list_item_crime,
                parent,
                false
            )
            return CrimeHolder(view);
        }
        override fun getItemCount(): Int {  // 可以写成1行;
            return this.crimes.size;
        }
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position];
            // holder.titleTextView.text = crime.title;
            // holder.dateTextView.text = crime.date.toString();
            holder.bind(crime);
        }
    }
}
