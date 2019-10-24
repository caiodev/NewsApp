package br.com.caiodev.newsapi.sections.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

object NetworkChecking {

    private val networkState = MutableLiveData<Boolean>()

    private val networkRequest = NetworkRequest.Builder().apply {
        addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    }

    private val connectivityCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            networkState.postValue(true)
        }

        override fun onLost(network: Network) {
            networkState.postValue(false)
        }
    }

    fun internetConnectionAvailabilityObservable(applicationContext: Context): MutableLiveData<Boolean> {
        (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            requestNetwork(
                networkRequest.build(), connectivityCallback
            )
        }
        return networkState
    }
}