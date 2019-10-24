package br.com.caiodev.newsapi.sections.utils.extensions

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

@Suppress("unused")
fun Context.showSnackBar(
    fragmentActivity: FragmentActivity, message: String
) {
    Snackbar.make(
        fragmentActivity.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_LONG
    ).show()
}