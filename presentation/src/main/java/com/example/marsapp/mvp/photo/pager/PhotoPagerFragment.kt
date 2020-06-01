package com.example.marsapp.mvp.photo.pager

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.lib.FileManager
import com.example.marsapp.App
import com.example.marsapp.R
import com.example.marsapp.entity.PhotoEntity
import com.example.marsapp.mvp.activity.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.io.File
import javax.inject.Inject

private const val LIST_ARG = "com.example.marsapp.mvp.photo.pager.listarg"
private const val POSITION_ARG = "com.example.marsapp.mvp.photo.pager.position"
private const val PERMISSION_REQUEST_STORAGE = 1

class PhotoPagerFragment : MvpAppCompatFragment(), PhotoPageContract.View {

    companion object {
        fun newInstance(list: List<PhotoEntity>, position: Int): PhotoPagerFragment {
            val bundle = Bundle().apply {
                putParcelableArrayList(LIST_ARG, ArrayList(list))
                putInt(POSITION_ARG, position)
            }
            val fragment = PhotoPagerFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    interface OnBackButtonClickListener {
        fun onBackClicked()
    }

    private lateinit var pager: ViewPager2
    private lateinit var backButton: ImageView
    private lateinit var elementCount: TextView
    private lateinit var actionMenuView: ImageView
    private var listener: OnBackButtonClickListener? = null

    @InjectPresenter
    lateinit var presenter: PhotoPagerPresenter

    @Inject
    lateinit var fileManager: FileManager

    @ProvidePresenter
    fun providePresenter(): PhotoPagerPresenter {
        val list = arguments?.getParcelableArrayList<PhotoEntity>(LIST_ARG)
        val position = arguments?.getInt(POSITION_ARG)
        return if (list != null && position != null) {
            PhotoPagerPresenter(list, position)
        } else {
            PhotoPagerPresenter(listOf(), 0)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
        val view = inflater.inflate(R.layout.image_pager, container, false)

        with(view) {
            pager = findViewById(R.id.photo_pager)
            backButton = findViewById(R.id.photo_pager_back_button)
            elementCount = findViewById(R.id.element_count)
            actionMenuView = findViewById(R.id.action_menu)
        }

        listener?.let { listener ->
            backButton.setOnClickListener {
                listener.onBackClicked()
            }
        }

        val popupMenu = PopupMenu(context, actionMenuView)
        popupMenu.inflate(R.menu.photo_pager_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save_to_gallery -> {
                    saveImage()
                    true
                }
                else -> true
            }
        }

        actionMenuView.setOnClickListener {
            popupMenu.show()
        }

        presenter.initData()

        return view
    }


    override fun setupAdapter(adapter: PhotoPagerAdapter) {
        pager.adapter = adapter
        arguments?.let {
            val position: Int = it.getInt(POSITION_ARG)
            pager.setCurrentItem(position, false)
        }
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter.pageScrolled(position)
            }
        })
    }

    override fun setCounter(size: Int, position: Int) {
        val count = resources.getString(R.string.photo_count, position, size)
        elementCount.text = count
    }

    private fun saveImage() {
        if(checkWriteExStoragePermission()){
            pager.adapter?.let {
                if (it is PhotoPagerAdapter) {
                    val drawable = it.currentDrawable
                    if (drawable == null) {
                        Toast.makeText(
                            context,
                            context!!.resources.getString(R.string.saving_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val uri = fileManager.saveImage(drawable)
                        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
                            mediaScanIntent.data = uri
                            context!!.sendBroadcast(mediaScanIntent)
                        }
                    }
                }
            }
        }else{
            requestWriteExStoragePermission()
        }
    }

    private fun checkWriteExStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestWriteExStoragePermission() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_STORAGE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_STORAGE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    saveImage()
                }
            }
        }
    }


}