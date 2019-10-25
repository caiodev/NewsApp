package br.com.caiodev.newsapi.sections.newsHome.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.caiodev.newsapi.BuildConfig
import br.com.caiodev.newsapi.R
import br.com.caiodev.newsapi.sections.newsHome.model.adapter.NewsAdapter
import br.com.caiodev.newsapi.sections.newsHome.model.repository.NewsRepository
import br.com.caiodev.newsapi.sections.newsHome.viewModel.NewsViewModel
import br.com.caiodev.newsapi.sections.newsHome.viewModel.NewsViewModelFactory
import br.com.caiodev.newsapi.sections.utils.base.ActivityFlow
import br.com.caiodev.newsapi.sections.utils.constants.Constants.cellular
import br.com.caiodev.newsapi.sections.utils.constants.Constants.disconnected
import br.com.caiodev.newsapi.sections.utils.constants.Constants.wifi
import br.com.caiodev.newsapi.sections.utils.extensions.castAttributeThroughViewModel
import br.com.caiodev.newsapi.sections.utils.extensions.showSnackBar
import br.com.caiodev.newsapi.sections.utils.network.NetworkChecking
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), ActivityFlow {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            NewsViewModelFactory(NewsRepository())
        ).get(NewsViewModel::class.java)
    }

    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        handleViewModel()

        NetworkChecking.internetConnectionAvailabilityObservable(applicationContext)
            .observe(this, Observer {
                if (viewModel.hasAnUnsuccessfulCallBeenMade) {
                    callApi {
                        viewModel.getTrendingNews("google-news-br", BuildConfig.API_KEY)
                    }
                    newsProgressBar.visibility = VISIBLE
                }
            })
    }

    override fun setupUI() {
        newsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun handleViewModel() {

        if (!viewModel.hasACallBeenMade) callApi {
            viewModel.getTrendingNews("google-news-br", BuildConfig.API_KEY)
        }

        viewModel.successMutableLiveData.observe(this, Observer {
            newsAdapter.updateList(viewModel.castAttributeThroughViewModel(it))
            runLayoutAnimation(newsRecyclerView)
        })

        viewModel.errorSingleLiveEvent.observeSingleEvent(this, Observer {
            showSnackBar(this, getString(it))
            if (newsProgressBar.visibility == VISIBLE) newsProgressBar.visibility = GONE
            viewModel.hasAnUnsuccessfulCallBeenMade = true
        })
    }

    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                context,
                R.anim.layout_animation_fall_down
            )
            adapter?.notifyDataSetChanged()
            scheduleLayoutAnimation()
            viewModel.hasACallBeenMade = true
        }

        if (newsProgressBar.visibility == VISIBLE) newsProgressBar.visibility = GONE
    }


    private fun callApi(genericFunction: () -> Unit) {
        when (NetworkChecking.checkIfInternetConnectionIsAvailable(applicationContext)) {
            cellular, wifi -> genericFunction.invoke()
            disconnected -> {
                showSnackBar(this, getString(R.string.no_connection_error))
                if (newsProgressBar.visibility == VISIBLE) newsProgressBar.visibility = GONE
                viewModel.hasAnUnsuccessfulCallBeenMade = true
            }
        }
    }
}