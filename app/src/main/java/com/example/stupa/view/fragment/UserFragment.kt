package com.example.stupa.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stupa.R
import com.example.stupa.adapter.UserRecyclerviewAdapter
import com.example.stupa.data.local.entity.User
import com.example.stupa.databinding.FragmentUserBinding
import com.example.stupa.databinding.FragmentUserListBinding
import com.example.stupa.viewmodel.UserViewmodel

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var userPhone: TextView
    private lateinit var userCountry: TextView
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViews()
        user = requireActivity().intent.getSerializableExtra("user") as User
        populateViews()
    }

    private fun initializeViews() {
        userName = binding.usernameTextview
        userEmail = binding.userEmailTextview
        userPhone = binding.userphoneTextview
        userCountry = binding.usercountryTextview
    }

    private fun populateViews() {
        userName.text = user.userName
        userEmail.text = user.userEmail
        userPhone.text = user.userPhoneNumber
        userCountry.text = user.userCountry
    }
}