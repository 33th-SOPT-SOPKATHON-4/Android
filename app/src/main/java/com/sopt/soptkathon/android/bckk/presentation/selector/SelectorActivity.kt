package com.sopt.soptkathon.android.bckk.presentation.selector

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.databinding.ActivitySelectorBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SelectorActivity : BindActivity<ActivitySelectorBinding>() {
    private val viewModel: SelectorViewModel by viewModels()
    private lateinit var selectorViewPagerAdapter: SelectorViewPagerAdapter

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            try {
                binding.vpSelectorContainer.currentItem--
            } catch (e: Exception) {
                finish()
            }
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySelectorBinding {
        return ActivitySelectorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(backPressedCallback)
        initLayout()
        addListeners()
        collectData()
    }

    private fun initLayout() {
        selectorViewPagerAdapter = SelectorViewPagerAdapter(this)

        binding.vpSelectorContainer.adapter = selectorViewPagerAdapter
        binding.vpSelectorContainer.isUserInputEnabled = false
        binding.vpSelectorContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)
            }
        })
    }

    private fun addListeners() {
        binding.btnSelectorJealous.setOnClickListener {
            when (binding.vpSelectorContainer.currentItem) {
                2 -> {

                }

                else -> {
                    binding.vpSelectorContainer.currentItem++
                }
            }
        }
    }

    private fun collectData() {
        viewModel.currentPage.flowWithLifecycle(lifecycle).onEach { currentPage ->
            currentPage?.let {
                binding.tvSelectorPageNumber.text = setPageText(it + 1)
            }
            Log.e("ㅋㅋ", viewModel.firstSelect.value.toString() + " " + viewModel.secondSelect.value.toString() + " " + viewModel.thirdSelect.value.toString())
        }.launchIn(lifecycleScope)

        viewModel.isSelectorBtnEnabled.flowWithLifecycle(lifecycle).onEach { isSelectorBtnEnabled ->
            binding.btnSelectorJealous.isEnabled = isSelectorBtnEnabled
        }.launchIn(lifecycleScope)
    }

    private fun setPageText(currentPage: Int): String {
        return String.format(PAGE_FORMAT, currentPage)
    }

    companion object {
        const val PAGE_FORMAT = "(%d/3)"
    }
}