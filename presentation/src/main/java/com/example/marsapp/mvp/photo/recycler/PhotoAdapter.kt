package com.example.marsapp.mvp.photo.recycler

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.marsapp.R
import com.example.marsapp.entity.PhotoEntity
import com.example.marsapp.mvp.activity.MainActivity

class PhotoAdapter(private val list: List<PhotoEntity>) : RecyclerView.Adapter<PhotoHolder>() {

    interface OnPhotoClickListener {
        fun onPhotoClick(list: List<PhotoEntity>, position: Int)
    }

    private var listener: OnPhotoClickListener? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val context: Context = recyclerView.context
        if (context is MainActivity) {
            listener = context
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item, parent, false)
        return PhotoHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener?.onPhotoClick(list, position)
        }
        Glide.with(holder.itemView.context)
            .load(list[position].imageUrl)
            .transition(withCrossFade(500))
            .into(holder.photo)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        listener = null
    }


}