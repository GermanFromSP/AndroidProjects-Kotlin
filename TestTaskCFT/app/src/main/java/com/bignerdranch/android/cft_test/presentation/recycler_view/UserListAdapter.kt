package com.bignerdranch.android.cft_test.presentation.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bignerdranch.android.cft_test.R
import com.bignerdranch.android.cft_test.domain.User
import com.squareup.picasso.Picasso

class UserListAdapter : ListAdapter<User, UserItemViewHolder>(UserListDiffCallback()) {

    var onUserItemClickListener: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_user, parent, false
        )

        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = getItem(position)
        val binding = holder.binding

        with(binding) {
            tvAddress.text = getUserAddress(user, root.context)
            tvFullName.text = getUserFullName(user, root.context)
            tvPhoneNumber.text = String.format(
                root.context.getString(R.string.user_list_cell),
                user.cell
            )

            ivProfilePicture.apply {
                Picasso.get()
                    .load(user.profilePictures?.largeSize)
                    .into(this)
            }
        }

        holder.itemView.setOnClickListener {
            onUserItemClickListener?.invoke(user)
        }
    }

    private fun getUserFullName(user: User, context: Context): String {
        with(user.userName) {
            this?.let {
                return String.format(
                    context.getString(R.string.user_list_full_name),
                    title,
                    firstName,
                    lastName,
                )
            }
            return context.getString(R.string.user_list_empty_full_name)
        }
    }

    private fun getUserAddress(user: User, context: Context): String {
        with(user.userLocation) {
            this?.let {
                return String.format(
                    context.getString(R.string.user_list_address),
                    city,
                    country
                )
            }

            return context.getString(R.string.user_list_empty_address)
        }
    }
}