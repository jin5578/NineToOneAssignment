package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.ninetooneassignment.databinding.ItemLocationListBinding
import com.tistory.jeongs0222.ninetooneassignment.model.Document


class LocationListAdapter(
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Document, LocationListViewHolder>(LocationDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListViewHolder {
        val binding = ItemLocationListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LocationListViewHolder(binding, lifecycleOwner)

    }

    override fun onBindViewHolder(holder: LocationListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class LocationListViewHolder(
    private val binding: ItemLocationListBinding,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Document) {
        binding.item = item

        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

object LocationDiff : DiffUtil.ItemCallback<Document>() {
    override fun areItemsTheSame(
        oldItem: Document,
        newItem: Document
    ): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Document,
        newItem: Document
    ): Boolean {
        return oldItem == newItem
    }
}