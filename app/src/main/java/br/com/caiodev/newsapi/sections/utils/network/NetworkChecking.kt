package br.com.caiodev.newsapi.sections.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import br.com.caiodev.newsapi.sections.utils.constants.Constants.cellular
import br.com.caiodev.newsapi.sections.utils.constants.Constants.disconnected
import br.com.caiodev.newsapi.sections.utils.constants.Constants.generic
import br.com.caiodev.newsapi.sections.utils.constants.Constants.wifi

object NetworkChecking {

    private val networkState = MutableLiveData<Boolean>()

    private val networkRequest = NetworkRequest.Builder().apply {
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

    //Checks whether or not there is internet connection
    fun checkIfInternetConnectionIsAvailable(applicationContext: Context): Int {
        (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            allNetworks.let { networkArray ->
                if (networkArray.isNotEmpty()) {
                    networkArray.forEach { network ->
                        getNetworkCapabilities(network)?.let { networkCapabilities ->
                            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                                when {
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return wifi
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return cellular
                                }
                            }
                        }
                    }
                } else return disconnected
            }
        }

        return generic
    }

    //Returns a LiveData so internet connection related state changes can be observed
    fun internetConnectionAvailabilityObservable(applicationContext: Context): MutableLiveData<Boolean> {
        (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            requestNetwork(
                networkRequest.build(), connectivityCallback
            )
        }
        return networkState
    }
}