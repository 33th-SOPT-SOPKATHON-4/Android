package com.sopt.soptkathon.android.bckk.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityMainBinding
import com.sopt.soptkathon.android.bckk.presentation.article.AddArticleActivity
import com.sopt.soptkathon.android.bckk.presentation.home.HomeActivity

class MainActivity : BindActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        startActivity(Intent(this, AddArticleActivity::class.java))
        initSplashAnimation(splashScreen)
        login()
    }

    @SuppressLint("HardwareIds")
    private fun login() {
        val androidId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID,
        ) ?: return
        viewModel.login(androidId)
    }

    private fun initSplashAnimation(splashScreen: SplashScreen) {
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView = splashScreenViewProvider.view
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    splashScreenViewProvider.remove()
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            splashScreenView.startAnimation(fadeOut)
//            intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
        }
    }
}
