package com.example.marsapp.mvp.activity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.marsapp.App
import com.example.marsapp.R
import com.example.marsapp.mvp.PageAdapter
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainContract.View {

    private lateinit var viewPager:ViewPager
    private lateinit var tabLayout:TabLayout

    @Inject
    @InjectPresenter
    lateinit var presenter: ActivityPresenter

    @ProvidePresenter
    fun providePresenter():ActivityPresenter{
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.main_activity_pager)
        tabLayout.setupWithViewPager(viewPager)
        presenter.initData(supportFragmentManager)
    }

    override fun showTabs(pageAdapter: PageAdapter) {
        viewPager.adapter = pageAdapter
    }
}
