package com.example.marsapp.mvp.photo.pager

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.marsapp.R
import com.example.marsapp.entity.PhotoEntity

class PhotoPagerAdapter(
    private val list: List<PhotoEntity>
) : RecyclerView.Adapter<PhotoPageViewHolder>() {

    var currentDrawable: Drawable? = null
    private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoPageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_item_page,
            parent,
            false
        )
        return PhotoPageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotoPageViewHolder, position: Int) {
        currentDrawable = null
        Glide.with(holder.itemView.context)
            .load(list[position].imageUrl)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                    //not needed
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    holder.setPhoto(resource)
                    currentDrawable = resource
                }

            })
    }

}