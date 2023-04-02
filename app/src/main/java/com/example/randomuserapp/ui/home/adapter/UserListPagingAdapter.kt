package com.example.randomuserapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.databinding.ListItemUserBinding
import com.example.randomuserapp.domain.model.UserItem

class UserListPagingAdapter(
    private val onItemClick: OnItemClickListener
) :
    PagingDataAdapter<UserItem, UserListPagingAdapter.UserListViewHolder>(
        COMPARATOR
    ) {

    inner class UserListViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: UserItem) {
            binding.userItem = userItem
        }
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        getItem(position)?.let { userItem ->
            holder.bind(userItem)
            holder.itemView.setOnClickListener {
                onItemClick.onItemClick(userItem)
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): UserListViewHolder {
        val view = ListItemUserBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return UserListViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(
                oldItem: UserItem,
                newItem: UserItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserItem,
                newItem: UserItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(userItem: UserItem)
    }
}