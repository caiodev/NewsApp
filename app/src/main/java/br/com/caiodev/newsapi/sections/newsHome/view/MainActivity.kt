package br.com.caiodev.newsapi.sections.newsHome.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.caiodev.newsapi.R
import br.com.caiodev.newsapi.sections.newsHome.model.repository.NewsRepository
import br.com.caiodev.newsapi.sections.newsHome.viewModel.NewsViewModel
import br.com.caiodev.newsapi.sections.newsHome.viewModel.NewsViewModelFactory
import br.com.caiodev.newsapi.sections.utils.base.ActivityFlow
import br.com.caiodev.newsapi.sections.utils.extensions.castAttributeThroughViewModel
import br.com.caiodev.newsapi.sections.utils.extensions.showSnackBar
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
    }

    override fun setupUI() {
        newsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun handleViewModel() {

        if (!viewModel.hasCallBeenMade) viewModel.getTrendingNews(
            "google-news-br",
            "INSERT YOUR API KEY: https://newsapi.org/"
        )

        viewModel.successMutableLiveData.observe(this, Observer {
            newsAdapter.updateList(viewModel.castAttributeThroughViewModel(it))
            runLayoutAnimation(newsRecyclerView)
        })

        viewModel.errorSingleLiveEvent.observeSingleEvent(this, Observer {
            showSnackBar(this, getString(it))
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
            viewModel.hasCallBeenMade = true
        }

        if (newsProgressBar.visibility == VISIBLE) newsProgressBar.visibility = GONE
    }
}