package com.example.marsapp.entity

import android.os.Parcel
import android.os.Parcelable

data class PhotoEntity(
    val urlId: Int,
    val imageUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(urlId)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoEntity> {
        override fun createFromParcel(parcel: Parcel): PhotoEntity {
            return PhotoEntity(parcel)
        }

        override fun newArray(size: Int): Array<PhotoEntity?> {
            return arrayOfNulls(size)
        }
    }
}