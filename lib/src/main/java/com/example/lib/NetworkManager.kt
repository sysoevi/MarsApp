package com.example.lib

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import java.util.*
import javax.inject.Inject

class NetworkManager
@Inject constructor(private val context: Context) {

    fun isConnected(): Boolean {
        val connec: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            return Objects.requireNonNull(connec).activeNetwork != null
        }else{
            val  networkInfo:NetworkInfo = connec.activeNetworkInfo
            return networkInfo.isConnectedOrConnecting
        }
    }

}