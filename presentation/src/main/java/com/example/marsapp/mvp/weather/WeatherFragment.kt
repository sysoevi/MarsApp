package com.example.marsapp.mvp.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.App
import com.example.marsapp.entity.WeatherEntity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getFragmentComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.initializeData()
    }

    override fun setupRecycler(weatherList: List<WeatherEntity>) {
        weatherList.forEach{
            println(it.sol)
        }
    }

}