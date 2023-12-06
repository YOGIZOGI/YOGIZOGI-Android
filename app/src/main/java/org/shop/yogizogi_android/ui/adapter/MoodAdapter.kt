package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shop.yogizogi_android.databinding.ItemMoodBinding
import org.shop.yogizogi_android.utils.Moods
import org.shop.yogizogi_android.utils.clicklistener.MoodItemClick

class MoodAdapter(private val clickListener: MoodItemClick) :
    ListAdapter<Moods, MoodAdapter.MoodViewHolder>(diffUtil) {

    inner class MoodViewHolder(private val binding: ItemMoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Moods) {
            binding.tvMood.text = item.korean
            binding.cardMoodItem.setOnClickListener {
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
        val diffUtil = object : DiffUtil.ItemCallback<Moods>() {
            override fun areItemsTheSame(oldItem: Moods, newItem: Moods): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Moods, newItem: Moods): Boolean {
                return oldItem == newItem
            }
        }
    }
}