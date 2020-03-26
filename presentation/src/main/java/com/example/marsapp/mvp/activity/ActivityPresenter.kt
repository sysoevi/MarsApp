package com.example.marsapp.mvp.activity

import androidx.fragment.app.FragmentManager
import com.example.marsapp.manager.ResourceManager
import com.example.marsapp.mvp.PageAdapter
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ActivityPresenter
@Inject constructor(private val resourceManager: ResourceManager) : MvpPresenter<MainContract.View>(),
    MainContract.Presenter {
    override fun initData(fragmentManager: FragmentManager) {
        viewState.showTabs(PageAdapter(fragmentManager, resourceManager.getTabNames()))
    }
}