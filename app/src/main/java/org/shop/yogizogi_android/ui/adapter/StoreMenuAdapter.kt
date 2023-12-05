package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shop.yogizogi_android.R
import org.shop.yogizogi_android.data.model.local.StoreInfoMenu
import org.shop.yogizogi_android.databinding.ItemStoreInfoMenuBinding

class StoreMenuAdapter :
    ListAdapter<StoreInfoMenu, StoreMenuAdapter.StoreMenuViewHolder>(diffUtil) {

    inner class StoreMenuViewHolder(private val binding: ItemStoreInfoMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreInfoMenu) {
            with(binding) {
                Picasso.get().load(item.image).placeholder(R.drawable.image_store_info_menu)
                    .into(ivMenuImg)
                tvMenuName.text = item.menuName
                tvMenuPrice.text = item.menuPrice
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
        val diffUtil = object : DiffUtil.ItemCallback<StoreInfoMenu>() {
            override fun areItemsTheSame(oldItem: StoreInfoMenu, newItem: StoreInfoMenu): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: StoreInfoMenu,
                newItem: StoreInfoMenu
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}