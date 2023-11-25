package com.sopt.soptkathon.android.bckk.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import com.sopt.soptkathon.android.bckk.databinding.ItemUploadedBinding

class MyPageAdapter(private val postList: List<UserResponse.UserDto.PostDto>) :
    RecyclerView.Adapter<MyPageAdapter.MyPageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding =
            ItemUploadedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    inner class MyPageViewHolder(private val binding: ItemUploadedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postDto: UserResponse.UserDto.PostDto) {
            with(binding) {
                binding.ivItemUploadedPicture.load(postDto.postImg) {
                    crossfade(true)
                    error(R.drawable.ic_launcher_background)
                }
                tvItemUploadedDate.text = postDto.createdDateTime
                tvItemUploadedEnvy.text = postDto.postDislikeReactionCount.toString()
                tvItemUploadedDescription.text = postDto.postContent
            }

        }

    }
}