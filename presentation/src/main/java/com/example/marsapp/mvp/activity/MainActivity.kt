package com.example.marsapp.mvp.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.marsapp.App
import com.example.marsapp.R
import com.example.marsapp.entity.PhotoEntity
import com.example.marsapp.mvp.PageAdapter
import com.example.marsapp.mvp.photo.pager.PhotoPagerAdapter
import com.example.marsapp.mvp.photo.pager.PhotoPagerFragment
import com.example.marsapp.mvp.photo.recycler.PhotoAdapter
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainContract.View, PhotoAdapter.OnPhotoClickListener,
PhotoPagerFragment.OnBackButtonClickListener{

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var backButton: ImageView

    @Inject
    @InjectPresenter
    lateinit var presenter: ActivityPresenter

    @ProvidePresenter
    fun providePresenter(): ActivityPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.main_activity_pager)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }

        tabLayout.setupWithViewPager(viewPager)
        presenter.initData(supportFragmentManager)
    }

    override fun showTabs(pageAdapter: PageAdapter) {
        viewPager.adapter = pageAdapter
    }

    override fun onPhotoClick(list: List<PhotoEntity>, position: Int) {
        val fragment = PhotoPagerFragment.newInstance(list, position)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackClicked() {
        supportFragmentManager.popBackStack()
    }
}
