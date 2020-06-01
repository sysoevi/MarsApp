package com.example.lib

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FileManager @Inject constructor(val context: Context) {

    @SuppressLint("SimpleDateFormat")
    private fun createFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "$timeStamp.JPG"
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File(storageDir, imageFileName)
    }

    fun saveImage(drawable: Drawable): Uri {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val file = createFile()
        if(file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.close()
            out.flush()
            println("done${file.absolutePath}")
        }catch (exception: Exception){
            println(exception)
        }
        return Uri.fromFile(file)
    }

}