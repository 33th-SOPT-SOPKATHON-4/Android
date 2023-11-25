package com.sopt.soptkathon.android.bckk.presentation.selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.sopt.soptkathon.android.bckk.base.BindFragment
import com.sopt.soptkathon.android.bckk.data.api.model.ArticlesResponse
import com.sopt.soptkathon.android.bckk.databinding.FragmentSelectorBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SelectorFragment() : BindFragment<FragmentSelectorBinding>() {
    private val viewModel: SelectorViewModel by activityViewModels()
    private lateinit var postList: List<ArticlesResponse.PostDto>

    override fun setBinding(layoutInflater: LayoutInflater): FragmentSelectorBinding {
        return FragmentSelectorBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        viewModel.currentPage.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { currentPage ->
                if (viewModel.articleList.value.size == 12 && viewModel.articleList.value.isNotEmpty()) {
                    when (currentPage) {
                        0 -> postList = viewModel.articleList.value.subList(0, 4)
                        1 -> postList = viewModel.articleList.value.subList(4, 8)
                        2 -> postList = viewModel.articleList.value.subList(8, 12)
                        else -> Unit
                    }
                }

                with(binding.includeSelectorItemFirst) {
                    layoutSelectorItem.setOnClickListener {
                        when (currentPage) {
                            0 -> viewModel.setFirstSelect(postList[FIRST].postId)
                            1 -> viewModel.setSecondSelect(postList[FIRST].postId)
                            2 -> viewModel.setThirdSelect(postList[FIRST].postId)
                            else -> Unit
                        }
                    }
                    ivSelectorItem.load(postList[FIRST].postContent)
                    tvSelectorContext.text = postList[FIRST].postContent
                }

                with(binding.includeSelectorItemSecond) {
                    layoutSelectorItem.setOnClickListener {
                        when (currentPage) {
                            0 -> viewModel.setFirstSelect(postList[SECOND].postId)
                            1 -> viewModel.setSecondSelect(postList[SECOND].postId)
                            2 -> viewModel.setThirdSelect(postList[SECOND].postId)
                            else -> Unit
                        }
                    }
                    ivSelectorItem.load(postList[SECOND].postContent)
                    tvSelectorContext.text = postList[SECOND].postContent
                }

                with(binding.includeSelectorItemThird) {
                    layoutSelectorItem.setOnClickListener {
                        when (currentPage) {
                            0 -> viewModel.setFirstSelect(postList[THIRD].postId)
                            1 -> viewModel.setSecondSelect(postList[THIRD].postId)
                            2 -> viewModel.setThirdSelect(postList[THIRD].postId)
                            else -> Unit
                        }
                    }
                    ivSelectorItem.load(postList[THIRD].postContent)
                    tvSelectorContext.text = postList[THIRD].postContent
                }

                with(binding.includeSelectorItemFourth) {
                    layoutSelectorItem.setOnClickListener {
                        when (currentPage) {
                            0 -> viewModel.setFirstSelect(postList[FOURTH].postId)
                            1 -> viewModel.setSecondSelect(postList[FOURTH].postId)
                            2 -> viewModel.setThirdSelect(postList[FOURTH].postId)
                            else -> Unit
                        }
                    }
                    ivSelectorItem.load(postList[FOURTH].postContent)
                    tvSelectorContext.text = postList[FOURTH].postContent
                }

                with(binding) {
                    includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[FIRST].postId
                    if (viewModel.firstSelect.value == postList[FIRST].postId) includeSelectorItemFirst.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[SECOND].postId
                    if (viewModel.firstSelect.value == postList[SECOND].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[THIRD].postId
                    if (viewModel.firstSelect.value == postList[THIRD].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[FOURTH].postId
                    if (viewModel.firstSelect.value == postList[FOURTH].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                }

                with(binding) {
                    includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[FIRST].postId
                    if (viewModel.secondSelect.value == postList[FIRST].postId) includeSelectorItemFirst.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[SECOND].postId
                    if (viewModel.secondSelect.value == postList[SECOND].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[THIRD].postId
                    if (viewModel.secondSelect.value == postList[THIRD].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[FOURTH].postId
                    if (viewModel.secondSelect.value == postList[FOURTH].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                }

                with(binding) {
                    includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[FIRST].postId
                    if (viewModel.thirdSelect.value == postList[FIRST].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[SECOND].postId
                    if (viewModel.thirdSelect.value == postList[SECOND].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[THIRD].postId
                    if (viewModel.thirdSelect.value == postList[THIRD].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                    includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[FOURTH].postId
                    if (viewModel.thirdSelect.value == postList[FOURTH].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                }
            }.launchIn(lifecycleScope)

        viewModel.firstSelect.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { firstSelect ->
            with(binding) {
                includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[FIRST].postId
                if (viewModel.firstSelect.value == postList[FIRST].postId) includeSelectorItemFirst.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[SECOND].postId
                if (viewModel.firstSelect.value == postList[SECOND].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[THIRD].postId
                if (viewModel.firstSelect.value == postList[THIRD].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[FOURTH].postId
                if (viewModel.firstSelect.value == postList[FOURTH].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
            }
        }.launchIn(lifecycleScope)

        viewModel.secondSelect.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { secondSelect ->
            with(binding) {
                includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[FIRST].postId
                if (viewModel.secondSelect.value == postList[FIRST].postId) includeSelectorItemFirst.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[SECOND].postId
                if (viewModel.secondSelect.value == postList[SECOND].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[THIRD].postId
                if (viewModel.secondSelect.value == postList[THIRD].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[FOURTH].postId
                if (viewModel.secondSelect.value == postList[FOURTH].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
            }
        }.launchIn(lifecycleScope)

        viewModel.thirdSelect.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { thirdSelect ->
            with(binding) {
                includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[FIRST].postId
                if (viewModel.thirdSelect.value == postList[FIRST].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[SECOND].postId
                if (viewModel.thirdSelect.value == postList[SECOND].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[THIRD].postId
                if (viewModel.thirdSelect.value == postList[THIRD].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
                includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[FOURTH].postId
                if (viewModel.thirdSelect.value == postList[FOURTH].postId) includeSelectorItemSecond.ivSelectorSelectedIcon.visibility = View.VISIBLE else View.INVISIBLE
            }
        }.launchIn(lifecycleScope)
    }

    companion object {
        const val FIRST = 0
        const val SECOND = 1
        const val THIRD = 2
        const val FOURTH = 3
    }
}