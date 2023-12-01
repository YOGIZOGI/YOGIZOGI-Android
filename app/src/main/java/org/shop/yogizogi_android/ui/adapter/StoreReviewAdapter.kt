package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shop.yogizogi_android.data.model.local.StoreReview
import org.shop.yogizogi_android.databinding.ItemStoreReviewsBinding

class StoreReviewAdapter : ListAdapter<StoreReview, StoreReviewAdapter.StoreReviewViewHolder>(
    diffUtil
) {

    inner class StoreReviewViewHolder(private val binding: ItemStoreReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreReview) {
            with(binding) {
                tvUserName.text = item.nickname
                ivIsverified.isVisible = item.isVerified
                ivIsfavorite.isSelected = item.isFavorite
                layoutHot.tvWell.text = item.hotWell.toString()
                layoutHot.tvPrefer.text = item.hotPrefer.toString()
                layoutSweet.tvWell.text = item.sweetWell.toString()
                layoutSweet.tvPrefer.text = item.sweetPrefer.toString()
                layoutSalty.tvWell.text = item.saltyWell.toString()
                layoutSalty.tvPrefer.text = item.saltyPrefer.toString()
                tvReviewContent.text = item.reviewContent
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreReviewViewHolder {
        return StoreReviewViewHolder(
            ItemStoreReviewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoreReviewViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StoreReview>() {
            override fun areItemsTheSame(oldItem: StoreReview, newItem: StoreReview): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: StoreReview, newItem: StoreReview): Boolean {
                return oldItem == newItem
            }
        }
    }
}