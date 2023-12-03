package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shop.yogizogi_android.R

class StoreInfoVPAdapter(private val imgList: List<String>) :
    RecyclerView.Adapter<StoreInfoVPAdapter.ImgInfoViewHolder>() {

    inner class ImgInfoViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imageView: ImageView = item.findViewById(R.id.iv_store_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vp_store_info_img, parent, false)
        return ImgInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImgInfoViewHolder, position: Int) {
        val imgUrl = imgList[position]
        Picasso.get().load(imgUrl).placeholder(R.drawable.logo_login_app).into(holder.imageView)
    }

    override fun getItemCount() = imgList.size
}