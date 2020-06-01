package com.example.marsapp.mvp.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.App
import com.example.marsapp.R
import com.example.marsapp.mvp.photo.recycler.ItemPhotoDecoration
import com.example.marsapp.mvp.photo.recycler.PhotoAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PhotoFragment: MvpAppCompatFragment(), PhotoContract.View{

    private lateinit var recycler: RecyclerView
    private lateinit var progressBar:ProgressBar

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

        val columnNum = calculateNumOfColumns()
        recycler.layoutManager = GridLayoutManager(context, columnNum)
        val spacing = context!!.resources.getDimensionPixelSize(R.dimen.spacing_photo_card)
        recycler.addItemDecoration(ItemPhotoDecoration(spacing))

        progressBar = view.findViewById(R.id.progress_bar_for_list)
        presenter.loadData()
        return view
    }

    private fun calculateNumOfColumns(): Int {
        val context = context
        return if(context != null){
            val displayMetrics = context.resources.displayMetrics
            val dpWith = displayMetrics.widthPixels / displayMetrics.density
            (dpWith/180).toInt()
        }else{
            0
        }
    }

    override fun setupAdapter(photoAdapter: PhotoAdapter) {
        recycler.adapter = photoAdapter
    }

    override fun showError(exception: Throwable) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}