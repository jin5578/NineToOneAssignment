package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.ninetooneassignment.databinding.ItemLocationListBinding
import com.tistory.jeongs0222.ninetooneassignment.model.kakao.Document


class LocationListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<Document, LocationListViewHolder>(LocationDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListViewHolder {
        val binding = ItemLocationListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LocationListViewHolder(binding, lifecycleOwner, eventListener)

    }

    override fun onBindViewHolder(holder: LocationListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class LocationListViewHolder(
    private val binding: ItemLocationListBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Document) {
        binding.item = item

        binding.lifecycleOwner = lifecycleOwner
        binding.eventListener = eventListener
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