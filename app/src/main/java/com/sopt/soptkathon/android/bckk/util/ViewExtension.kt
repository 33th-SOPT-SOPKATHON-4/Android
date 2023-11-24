package com.sopt.soptkathon.android.bckk.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}
