package com.example.marsapp.mvp.photo.recycler

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R
import com.example.marsapp.mvp.photo.PhotoContract

class PhotoHolder(
    view: View,
    private val photo: ImageView = view.findViewById(R.id.photo)
) : RecyclerView.ViewHolder(view),
    PhotoContract.ItemView {
    override fun setPhoto(drawable: Drawable) = photo.setImageDrawable(drawable)
}