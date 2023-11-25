package com.sopt.soptkathon.android.bckk.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.soptkathon.android.bckk.R
import com.sopt.soptkathon.android.bckk.data.api.model.UserResponse
import com.sopt.soptkathon.android.bckk.databinding.ItemUploadedBinding
import com.sopt.soptkathon.android.bckk.util.ItemDiffCallback

class MyPageAdapter : ListAdapter<UserResponse.PostDto, MyPageAdapter.MyPageViewHolder>(
    ItemDiffCallback<UserResponse.PostDto>(
        onItemsTheSame = { old, new -> old.postId == new.postId },
        onContentsTheSame = { old, new -> old == new },
    ),
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding =
            ItemUploadedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MyPageViewHolder(private val binding: ItemUploadedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postDto: UserResponse.PostDto) {
            with(binding) {
                binding.ivItemUploadedPicture.load(postDto.postImg) {
                    crossfade(true)
                    error(R.drawable.rectangle_item_uploaded_blank)
                }
                tvItemUploadedDate.text = postDto.createdDateTime
                tvItemUploadedEnvy.text = postDto.postDislikeCount.toString()
                tvItemUploadedDescription.text = postDto.postContent
            }
        }
    }
}
