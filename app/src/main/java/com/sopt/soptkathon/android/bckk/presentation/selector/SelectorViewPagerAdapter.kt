package com.sopt.soptkathon.android.bckk.presentation.selector

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SelectorViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val selectorFragments =
        listOf(SelectorFragment(), SelectorFragment(), SelectorFragment())

    override fun getItemCount(): Int = selectorFragments.size

    override fun createFragment(position: Int): Fragment = selectorFragments[position]
}