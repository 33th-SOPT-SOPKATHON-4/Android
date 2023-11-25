package com.sopt.soptkathon.android.bckk.presentation.selector

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.sopt.soptkathon.android.bckk.base.BindActivity
import com.sopt.soptkathon.android.bckk.data.api.model.PostDisLikeRequest
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
                    viewModel.firstSelect.value?.let { firstSelect ->
                        viewModel.secondSelect.value?.let { secondSelect ->
                            viewModel.thirdSelect.value?.let { thirdSelect ->
                                viewModel.postDisLike(
                                    PostDisLikeRequest(
                                        listOf(
                                            firstSelect,
                                            secondSelect,
                                            thirdSelect
                                        )
                                    )
                                )
                            }
                        }
                    }
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
        }.launchIn(lifecycleScope)

        viewModel.isSelectorBtnEnabled.flowWithLifecycle(lifecycle).onEach { isSelectorBtnEnabled ->
            binding.btnSelectorJealous.isEnabled = isSelectorBtnEnabled
        }.launchIn(lifecycleScope)

        viewModel.postDislikeState.flowWithLifecycle(lifecycle).onEach { postDislikeState ->
            if (postDislikeState) {

            }
        }
    }

    private fun setPageText(currentPage: Int): String {
        return String.format(PAGE_FORMAT, currentPage)
    }

    companion object {
        const val PAGE_FORMAT = "(%d/3)"
    }
}