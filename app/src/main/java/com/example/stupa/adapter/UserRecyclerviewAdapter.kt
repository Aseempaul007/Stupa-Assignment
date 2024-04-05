package com.example.stupa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stupa.data.local.entity.User
import com.example.stupa.databinding.UserItemBinding

//recycler view adapter to show list of all users
class UserRecyclerviewAdapter(
    private val context: Context,
    private val users: List<User>,
) : RecyclerView.Adapter<UserRecyclerviewAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.usernameTextview.text = users.get(position).userName
        holder.binding.userEmailTextview.text = users.get(position).userEmail
        holder.binding.userphoneTextview.text = users.get(position).userPhoneNumber
        holder.binding.usercountryTextview.text = users.get(position).userCountry
    }
}