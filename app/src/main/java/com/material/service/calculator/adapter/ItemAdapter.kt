package com.material.service.calculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.material.service.calculator.R
import com.material.service.calculator.RoomDB.ShoppingItem
import com.material.service.calculator.databinding.ItemLayoutBinding

class ItemAdapter():RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         var binding = ItemLayoutBinding.bind(itemView)
    }

    val diffCallBack = object : DiffUtil.ItemCallback<ShoppingItem>(){
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem.hashCode()==newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this,diffCallBack)

    fun submitList(list:List<ShoppingItem>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))

        return viewHolder
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]

        Glide.with(holder.itemView).load(item.imageUrl).into(holder.binding.itemImage)

        holder.binding.itemAmount.text = item.amount.toString()

        holder.binding.itemName.text =item.name

        holder.binding.itemAmountperitem.text =item.price.toString()
    }

}