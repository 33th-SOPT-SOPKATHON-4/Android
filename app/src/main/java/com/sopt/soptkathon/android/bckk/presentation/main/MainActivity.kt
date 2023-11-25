package com.sopt.soptkathon.android.bckk.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.load
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivityMainBinding

class MainActivity : BindActivity<ActivityMainBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView = splashScreenViewProvider.view
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    // 애니메이션 종료 후 스플래시 스크린 제거
                    splashScreenViewProvider.remove()
                }
                override fun onAnimationRepeat(animation: Animation) {}
            })
            splashScreenView.startAnimation(fadeOut)
        }
        binding.ivMain.load(R.drawable.ic_launcher_background)
    }
}
