package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shop.yogizogi_android.data.model.local.MainFeed
import org.shop.yogizogi_android.databinding.ItemMainFeedBinding
import org.shop.yogizogi_android.utils.clicklistener.MainFeedClick

class MainFeedAdapter(private val clickListener: MainFeedClick) :
    ListAdapter<MainFeed, MainFeedAdapter.MainFeedViewHolder>(diffUtil) {

    inner class MainFeedViewHolder(private val binding: ItemMainFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainFeed) {
            binding.tvName.text = item.storeName
            binding.cardMainFeedItem.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFeedViewHolder {
        return MainFeedViewHolder(
            ItemMainFeedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainFeedViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MainFeed>() {
            override fun areItemsTheSame(oldItem: MainFeed, newItem: MainFeed): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MainFeed, newItem: MainFeed): Boolean {
                return oldItem == newItem
            }
        }
    }
}