package org.shop.yogizogi_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.shop.yogizogi_android.R

class StoreReviewVPAdapter(private val imgList: List<String>) :
    RecyclerView.Adapter<StoreReviewVPAdapter.ImgViewHolder>() {

    inner class ImgViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val imageView: ImageView = item.findViewById(R.id.iv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store_review_img, parent, false)
        return ImgViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        val imgUrl = imgList[position]
        Picasso.get().load(imgUrl).placeholder(R.drawable.logo_login_app).into(holder.imageView)
    }

    override fun getItemCount() = imgList.size
}