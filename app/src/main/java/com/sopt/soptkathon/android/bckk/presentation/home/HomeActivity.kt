package com.sopt.soptkathon.android.bckk.presentation.home

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
import com.sopt.soptkathon.android.bckk.databinding.ActivityHomeBinding
import com.sopt.soptkathon.android.bckk.presentation.article.AddArticleActivity
import com.sopt.soptkathon.android.bckk.presentation.mypage.MyPageActivity
import com.sopt.soptkathon.android.bckk.presentation.selector.SelectorActivity

class HomeActivity : BindActivity<ActivityHomeBinding>() {
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun setBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSplashAnimation(splashScreen)
        login()

        binding.run {
            ibHomeGoMyPage.setOnClickListener {
                val intent = Intent(applicationContext, MyPageActivity::class.java)
                startActivity(intent)
            }

            ibHomeGoAddArticle.setOnClickListener {
                val intent = Intent(applicationContext, AddArticleActivity::class.java)
                startActivity(intent)
            }

            ibHomeGoSelector.setOnClickListener {
                val intent = Intent(applicationContext, SelectorActivity::class.java)
                startActivity(intent)
            }
        }
        homeViewModel.ticketCount.observe(
            this,
        ) { ticketCount ->
            binding.tvHome1.text = getString(R.string.test, ticketCount)
            binding.tvHomeTicketCount.text = ticketCount.toString()
        }
        homeViewModel.isGoArticleButtonClicked.observe(
            this,
        ) { isButtonClicked ->
            binding.ibHomeGoAddArticle.isEnabled = isButtonClicked
            if (isButtonClicked) {
                binding.ibHomeGoAddArticle.setImageResource(R.drawable.boast_total_pink)
            } else {
                binding.tvHome1.text = "질투나기를 누르면"
                binding.tvHome2.text = "자랑하기 티켓을 얻을 수 있어요!"
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun login() {
        val androidId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID,
        ) ?: return
        homeViewModel.login(androidId)
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
        }
    }
}
