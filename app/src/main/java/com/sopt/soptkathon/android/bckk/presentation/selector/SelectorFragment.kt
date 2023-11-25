package com.sopt.soptkathon.android.bckk.presentation.selector

import android.os.Bundle
import android.util.Log
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
                when (currentPage) {
                    0 -> postList = viewModel.dummyPost.subList(0, 4)
                    1 -> postList = viewModel.dummyPost.subList(4, 8)
                    2 -> postList = viewModel.dummyPost.subList(8, 12)
                    else -> Unit
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
                    includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[SECOND].postId
                    includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[THIRD].postId
                    includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.firstSelect.value == postList[FOURTH].postId
                }

                with(binding) {
                    includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[FIRST].postId
                    includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[SECOND].postId
                    includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[THIRD].postId
                    includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.secondSelect.value == postList[FOURTH].postId
                }

                with(binding) {
                    includeSelectorItemFirst.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[FIRST].postId
                    includeSelectorItemSecond.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[SECOND].postId
                    includeSelectorItemThird.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[THIRD].postId
                    includeSelectorItemFourth.layoutSelectorItem.isSelected = viewModel.thirdSelect.value == postList[FOURTH].postId
                }

                Log.e("ㅋㅋ", postList[FIRST].postId.toString() + " " + viewModel.firstSelect.value.toString() + " " + viewModel.secondSelect.value.toString()+ " " + viewModel.thirdSelect.value.toString())
            }.launchIn(lifecycleScope)

        viewModel.firstSelect.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { firstSelect ->
            with(binding) {
                includeSelectorItemFirst.layoutSelectorItem.isSelected = firstSelect == postList[FIRST].postId
                includeSelectorItemSecond.layoutSelectorItem.isSelected = firstSelect == postList[SECOND].postId
                includeSelectorItemThird.layoutSelectorItem.isSelected = firstSelect == postList[THIRD].postId
                includeSelectorItemFourth.layoutSelectorItem.isSelected = firstSelect == postList[FOURTH].postId
            }
        }.launchIn(lifecycleScope)

        viewModel.secondSelect.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { secondSelect ->
            with(binding) {
                includeSelectorItemFirst.layoutSelectorItem.isSelected = secondSelect == postList[FIRST].postId
                includeSelectorItemSecond.layoutSelectorItem.isSelected = secondSelect == postList[SECOND].postId
                includeSelectorItemThird.layoutSelectorItem.isSelected = secondSelect == postList[THIRD].postId
                includeSelectorItemFourth.layoutSelectorItem.isSelected = secondSelect == postList[FOURTH].postId
            }
        }.launchIn(lifecycleScope)

        viewModel.thirdSelect.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach { thirdSelect ->
            with(binding) {
                includeSelectorItemFirst.layoutSelectorItem.isSelected = thirdSelect == postList[FIRST].postId
                includeSelectorItemSecond.layoutSelectorItem.isSelected = thirdSelect == postList[SECOND].postId
                includeSelectorItemThird.layoutSelectorItem.isSelected = thirdSelect == postList[THIRD].postId
                includeSelectorItemFourth.layoutSelectorItem.isSelected = thirdSelect == postList[FOURTH].postId
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