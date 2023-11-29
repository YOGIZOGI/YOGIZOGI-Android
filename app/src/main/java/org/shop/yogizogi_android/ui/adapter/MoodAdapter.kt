package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shop.yogizogi_android.data.model.local.ItemMood
import org.shop.yogizogi_android.databinding.ItemMoodBinding
import org.shop.yogizogi_android.utils.clicklistener.MoodItemClick

class MoodAdapter(private val clickListener: MoodItemClick) :
    ListAdapter<ItemMood, MoodAdapter.MoodViewHolder>(diffUtil) {

    inner class MoodViewHolder(private val binding: ItemMoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMood) {
            binding.tvMood.text = item.moodTitle
            binding.cardItem.setOnClickListener {
                clickListener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        return MoodViewHolder(
            ItemMoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ItemMood>() {
            override fun areItemsTheSame(oldItem: ItemMood, newItem: ItemMood): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemMood, newItem: ItemMood): Boolean {
                return oldItem == newItem
            }
        }
    }
}