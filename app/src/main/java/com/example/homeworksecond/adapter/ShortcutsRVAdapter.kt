package com.example.homeworksecond.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworksecond.databinding.SingleShortcutItemBinding
import com.example.homeworksecond.model.ShortcutModel

class ShortcutsRVAdapter (
        private val shortcutlist: ArrayList<ShortcutModel>
) : RecyclerView.Adapter<ShortcutsRVAdapter.ShortcutRVViewHolder>(){

    inner class ShortcutRVViewHolder(var binding: SingleShortcutItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortcutRVViewHolder {
        val binding = SingleShortcutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShortcutRVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShortcutRVViewHolder, position: Int) {
        val currentItem = shortcutlist[position]
        holder.binding.shortcutIcon.setImageResource(currentItem.shortcutIcon)
        holder.binding.shortcutTitle.text=currentItem.title
    }

    override fun getItemCount() = shortcutlist.size
}