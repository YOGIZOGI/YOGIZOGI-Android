package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.remote.response.auth.MenuVO
import org.shop.yogizogi_android.databinding.ItemStoreInfoMenuBinding

class StoreMenuAdapter :
    ListAdapter<MenuVO, StoreMenuAdapter.StoreMenuViewHolder>(diffUtil) {

    inner class StoreMenuViewHolder(private val binding: ItemStoreInfoMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuVO) {
            with(binding) {
                Picasso.get().load(item.details.imageUrl)
                    .placeholder(R.drawable.image_store_info_menu)
                    .into(ivMenuImg)
                tvMenuName.text = item.details.name
                tvMenuPrice.text = item.details.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreMenuViewHolder {
        return StoreMenuViewHolder(
            ItemStoreInfoMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoreMenuViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MenuVO>() {
            override fun areItemsTheSame(oldItem: MenuVO, newItem: MenuVO): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MenuVO,
                newItem: MenuVO
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}