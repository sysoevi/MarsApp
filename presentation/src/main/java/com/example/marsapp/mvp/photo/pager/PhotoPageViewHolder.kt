package com.example.marsapp.mvp.photo.pager

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R
import com.github.chrisbanes.photoview.PhotoView

class PhotoPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PhotoPageContract.ItemView {

    private val photo: PhotoView = itemView.findViewById(R.id.photo_pager_image)

    override fun setPhoto(drawable: Drawable) {
        photo.setImageDrawable(drawable)
    }

}