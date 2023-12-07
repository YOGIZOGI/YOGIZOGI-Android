package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.local.MyReview
import org.shop.yogizogi_android.databinding.ItemMyreviewBinding

class MyReviewAdapter : ListAdapter<MyReview, MyReviewAdapter.MyReviewViewHolder>(diffUtil) {

    inner class MyReviewViewHolder(private val binding: ItemMyreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyReview) {
            Picasso.get().load(item.image).placeholder(R.drawable.image_store_info_menu)
                .into(binding.ivImage)
            binding.tvName.text = item.name
            binding.selectorRecom.isSelected = item.recom
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