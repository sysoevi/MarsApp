package com.example.marsapp.mvp.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.App
import com.example.marsapp.R
import com.example.marsapp.mvp.photo.recycler.PaginationListener
import com.example.marsapp.mvp.photo.recycler.PhotoAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PhotoFragment: MvpAppCompatFragment(), PhotoContract.View{

    private lateinit var recycler: RecyclerView
    private lateinit var progressBar:ProgressBar
    private var isLoading = false
    private lateinit var paginationListener: PaginationListener

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
        val view = layoutInflater.inflate(R.layout.list_fragment, container, false)
        recycler = view.findViewById(R.id.list)
        recycler.layoutManager = LinearLayoutManager(context)
        progressBar = view.findViewById(R.id.progress_bar_for_list)
        initRecycler()
        presenter.loadData()
        return view
    }

    override fun setupAdapter(photoAdapter: PhotoAdapter) {
        recycler.adapter = photoAdapter
    }

    override fun refreshRecycler() {
        recycler.adapter?.notifyDataSetChanged()
        isLoading = false
    }

    override fun lastPageWasLoaded() {
        paginationListener.isLastPage = true
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun initRecycler(){
        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        paginationListener = object : PaginationListener(layoutManager){
            override fun loadMore() {
                presenter.loadData()
                isLoading = true
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        }
        recycler.addOnScrollListener(paginationListener)
    }

}