package com.example.unsplashapi.ui.listOfImage

import com.example.unsplashapi.R
import com.example.unsplashapi.databinding.ItemRecyclerListBinding
import com.example.unsplashapi.model.Data
import com.example.unsplashapi.model.DataItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerListOfImageAdapter(): RecyclerView.Adapter<RecyclerListOfImageAdapter.ViewHolder>() {

    private lateinit var myListener: onItemClickListener

    private val callback = object : DiffUtil.ItemCallback<DataItem>(){
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.created_at == newItem.created_at
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    interface onItemClickListener{
        fun onItemClickAdd(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        myListener = listener
    }

    fun removeAt(position: Int) {
        differ.currentList.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(listener: onItemClickListener, val binding: ItemRecyclerListBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.cardView.setOnClickListener {
                listener.onItemClickAdd(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(myListener, ItemRecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val shoes = differ.currentList[position]

        Picasso.get()
            .load(differ.currentList[position].urls?.small_s3)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.ivLogo)

        holder.binding.tvAuthor.text = differ.currentList[position].user?.username
    }

    override fun getItemCount(): Int = differ.currentList.size
}