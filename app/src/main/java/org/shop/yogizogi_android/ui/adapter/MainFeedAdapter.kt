package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.remote.response.auth.SpecificStoreResDTO
import org.shop.yogizogi_android.databinding.ItemMainFeedBinding
import org.shop.yogizogi_android.utils.clicklistener.MainFeedClick

class MainFeedAdapter(private val clickListener: MainFeedClick) :
    ListAdapter<SpecificStoreResDTO, MainFeedAdapter.MainFeedViewHolder>(diffUtil) {

    inner class MainFeedViewHolder(private val binding: ItemMainFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpecificStoreResDTO) {
            binding.tvName.text = item.restaurantDetails.name
            binding.cardMainFeedItem.setOnClickListener {
                clickListener.onItemClick(item)
            }
            Picasso.get().load(item.restaurantDetails.imageUrl)
                .placeholder(R.drawable.logo_login_app).into(binding.ivImage)
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
        val diffUtil = object : DiffUtil.ItemCallback<SpecificStoreResDTO>() {
            override fun areItemsTheSame(
                oldItem: SpecificStoreResDTO,
                newItem: SpecificStoreResDTO
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: SpecificStoreResDTO,
                newItem: SpecificStoreResDTO
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}