package com.bignerdranch.android.cft_test.presentation.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.bignerdranch.android.cft_test.domain.User

class UserListDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return  oldItem == newItem
    }
}