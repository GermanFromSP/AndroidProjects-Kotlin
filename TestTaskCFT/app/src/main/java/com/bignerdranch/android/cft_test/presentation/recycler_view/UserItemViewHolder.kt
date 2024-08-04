package com.bignerdranch.android.cft_test.presentation.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.cft_test.databinding.RvItemUserBinding

class UserItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    val binding = RvItemUserBinding.bind(itemView)
}