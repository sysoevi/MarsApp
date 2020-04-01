package com.example.marsapp.mvp.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.App
import com.example.marsapp.R
import com.example.marsapp.mvp.activity.MainContract
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PhotoFragment: MvpAppCompatFragment(), PhotoContract.View{

    private lateinit var recyclerView: RecyclerView

    @Inject
    @InjectPresenter
    lateinit var presenter: PhotoPresenter

    @ProvidePresenter
    fun providePresenter():PhotoPresenter{
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getFragmentComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.photo_layout, container, false)
        recyclerView = view.findViewById(R.id.photo_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        presenter.initializeData()
        return view
    }

    override fun setupAdapter(photoAdapter: PhotoAdapter) {
        recyclerView.adapter = photoAdapter
    }

}