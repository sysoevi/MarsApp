package com.example.marsapp.mvp.photo.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marsapp.R

class PhotoHolder(
    view: View,
    val photo: ImageView = view.findViewById(R.id.photo)
) : RecyclerView.ViewHolder(view)