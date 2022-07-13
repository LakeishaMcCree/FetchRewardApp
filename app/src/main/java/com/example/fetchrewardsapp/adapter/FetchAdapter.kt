package com.example.fetchrewardsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapp.databinding.FetchItemBinding
import com.example.fetchrewardsapp.model.FetchListItem

class FetchAdapter(
     private val itemClicked:(FetchListItem) -> Unit
    ): RecyclerView.Adapter<FetchAdapter.FetchViewHolder>() {

    private var itemList: List<FetchListItem> = emptyList()

    fun bindData(items: List<FetchListItem>) {
        itemList = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchViewHolder {
        val binding = FetchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FetchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FetchViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class FetchViewHolder (val binding: FetchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FetchListItem){
            binding.listId.text = "List Id: ${item.listId}"
            binding.name.text = "Name: ${item.name}"

            itemView.setOnClickListener {
                itemClicked(item)
            }
        }
    }






}
