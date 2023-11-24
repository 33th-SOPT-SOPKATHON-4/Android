package com.sopt.soptkathon.android.bckk.util

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcelable

fun <T : Parcelable?> Intent.getExtParcelable(key: String, clazz: Class<T>): T? = when {
    SDK_INT >= TIRAMISU -> getParcelableExtra(key, clazz)
    else -> getParcelableExtra(key) as? T
}
