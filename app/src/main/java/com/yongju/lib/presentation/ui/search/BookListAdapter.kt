package com.yongju.lib.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yongju.lib.databinding.ItemBookListInfoBinding
import com.yongju.lib.domain.entity.BookInfo

class BookListAdapter(private val onSelectedBook: (ItemBookListInfoBinding, BookInfo) -> Unit) : ListAdapter<BookInfo, BookListViewHolder>(getDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder =
        BookListViewHolder(
            ItemBookListInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position), onSelectedBook)
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
    fun bind(item: BookInfo, onSelectedBook: (ItemBookListInfoBinding, BookInfo) -> Unit) {
        binding.ivImg.transitionName = "bookImage${item.id}"
        binding.ivFavorite.transitionName = "favorite${item.id}"
        binding.root.setOnClickListener {
            onSelectedBook(binding, item)
        }
        binding.item = item
    }
}