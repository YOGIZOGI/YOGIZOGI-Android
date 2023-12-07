package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shop.yogizogi_android.data.model.local.MyReview
import org.shop.yogizogi_android.databinding.ItemMyreviewBinding

class MyReviewAdapter : ListAdapter<MyReview, MyReviewAdapter.MyReviewViewHolder>(diffUtil) {

    inner class MyReviewViewHolder(private val binding: ItemMyreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyReview) {
            binding.vpMyreviewImages.adapter = MyReviewVPAdapter(item.images)
            binding.tvName.text = item.id.toString()
            binding.selectorRecom.isSelected = item.recommendationStatus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewViewHolder {
        return MyReviewViewHolder(
            ItemMyreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyReviewViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MyReview>() {
            override fun areItemsTheSame(oldItem: MyReview, newItem: MyReview): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MyReview, newItem: MyReview): Boolean {
                return oldItem == newItem
            }
        }
    }
}