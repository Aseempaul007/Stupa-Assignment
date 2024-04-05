package com.example.stupa.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stupa.R
import com.example.stupa.adapter.UserRecyclerviewAdapter
import com.example.stupa.data.local.entity.User
import com.example.stupa.databinding.FragmentLoginBinding
import com.example.stupa.databinding.FragmentUserBinding
import com.example.stupa.databinding.FragmentUserListBinding
import com.example.stupa.viewmodel.UserViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var viewModel: UserViewmodel
    private lateinit var listOfUsers: List<User>
    private lateinit var adapter: UserRecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserViewmodel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            listOfUsers = viewModel.showStudents()
            withContext(Dispatchers.Main){
                adapter = UserRecyclerviewAdapter(requireContext(), listOfUsers)
                binding.usersListRecyclerview.adapter = adapter
                binding.usersListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}