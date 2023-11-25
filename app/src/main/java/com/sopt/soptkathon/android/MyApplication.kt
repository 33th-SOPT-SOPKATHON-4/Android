package com.sopt.soptkathon.android

import android.app.Application
import com.sopt.soptkathon.android.bckk.BuildConfig
import com.sopt.soptkathon.android.bckk.data.api.ApiFactory
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer
import com.sopt.soptkathon.android.bckk.data.sharedpreference.SharedPreferenceContainer.sharedPreferencesInstance

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        myApplicationInstance = this
        sharedPreferencesInstance = SharedPreferenceContainer.getAppPreferences(this)
    }

    companion object {
        lateinit var myApplicationInstance: MyApplication
    }
}
