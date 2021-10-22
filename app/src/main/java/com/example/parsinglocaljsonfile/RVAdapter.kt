package com.example.parsinglocaljsonfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljsonfile.databinding.ImagesViewBinding

class RVAdapter (private var list: ArrayList<Data>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ImagesViewBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    private lateinit var myListener: OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener:OnItemClickListener ){
        myListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ImagesViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            myListener
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = list[position]

        holder.binding.apply {
            titleTV.text = photo.title
            Glide.with(mainLay)
                .load(photo.image)
                .into(imageView)
        }
    }

    override fun getItemCount() = list.size
}
