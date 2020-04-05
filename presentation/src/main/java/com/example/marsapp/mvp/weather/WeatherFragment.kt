package com.example.marsapp.mvp.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.App
import com.example.marsapp.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class WeatherFragment : MvpAppCompatFragment(), WeatherContract.View {

    @Inject
    @InjectPresenter
    lateinit var presenter: WeatherPresenter

    @ProvidePresenter
    fun providePresenter(): WeatherPresenter {
        return presenter
    }

    private lateinit var recycler: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getFragmentComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = layoutInflater
            .inflate(R.layout.list_fragment, container, false)
        recycler = view.findViewById(R.id.list)
        progressBar = view.findViewById(R.id.progress_bar_for_list)
        hideProgressBar()
        recycler.layoutManager = LinearLayoutManager(context)
        presenter.initializeData()
        return view
    }

    override fun setupRecycler(weatherAdapter: WeatherAdapter) {
        recycler.adapter = weatherAdapter
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}