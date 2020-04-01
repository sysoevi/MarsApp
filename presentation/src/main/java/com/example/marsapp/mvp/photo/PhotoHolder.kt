package com.example.marsapp.mvp.photo

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R

class PhotoHolder(
    view: View,
    private val photo: ImageView = view.findViewById(R.id.photo)
) : RecyclerView.ViewHolder(view), PhotoContract.ItemView {
    override fun setPhoto(drawable: Drawable) = photo.setImageDrawable(drawable)
}