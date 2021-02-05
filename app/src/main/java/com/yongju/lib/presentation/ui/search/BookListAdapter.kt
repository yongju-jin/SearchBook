package com.yongju.lib.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yongju.lib.databinding.ItemBookListInfoBinding
import com.yongju.lib.domain.entity.BookInfo

class BookListAdapter() : ListAdapter<BookInfo, BookListViewHolder>(getDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder =
        BookListViewHolder(
            ItemBookListInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        fun getDiffItemCallback(): DiffUtil.ItemCallback<BookInfo> =
            object : DiffUtil.ItemCallback<BookInfo>() {
                override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo) =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo) =
                    oldItem == newItem
            }
    }
}

class BookListViewHolder(private val binding: ItemBookListInfoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BookInfo) {
        binding.item = item
    }
}