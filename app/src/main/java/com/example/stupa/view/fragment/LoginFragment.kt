package com.example.stupa.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.stupa.R
import com.example.stupa.data.local.entity.User
import com.example.stupa.databinding.FragmentLoginBinding
import com.example.stupa.helper.Const
import com.example.stupa.helper.Validator
import com.example.stupa.view.activity.HomeActivity
import com.example.stupa.viewmodel.UserViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewmodel
    private lateinit var listOfUsers: List<User>
    private lateinit var emailTextView: EditText
    private lateinit var passwordTextView: EditText
    private lateinit var loginButton: Button
    private lateinit var navigateToRegister: TextView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(UserViewmodel::class.java)
        emailTextView = binding.edittextEmail
        passwordTextView = binding.edittextPassword
        navigateToRegister = binding.navigateToRegister
        loginButton = binding.buttonLogin

        CoroutineScope(Dispatchers.IO).launch {
            listOfUsers = viewModel.showStudents()
        }

        loginButton.setOnClickListener {

            val email = emailTextView.text.toString()
            val password = passwordTextView.text.toString()

            //validation
            if (!Validator.isValidEmail(email)) {
                emailTextView.error = Const.emailError;
                return@setOnClickListener;
            }

            if (!Validator.isValidPassword(password)) {
                passwordTextView.error = Const.passwordError;
                return@setOnClickListener;
            }
            if (email.isNotEmpty() && password.isNotEmpty()) {
                for (i in listOfUsers.indices) {
                    if (
                        listOfUsers[i].userEmail.equals(
                            emailTextView.text.toString(),
                            ignoreCase = true
                        ) &&
                        listOfUsers[i].userPassword.equals(
                            passwordTextView.text.toString(),
                            ignoreCase = true
                        )
                    ) {
                        activity?.let {
                            val intent = Intent(it, HomeActivity::class.java)
                            intent.putExtra(Const.user, listOfUsers[i])
                            it.startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                }
                Toast.makeText(requireContext(), "Logged In!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "you are not registered! Please register",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //navigating to register fragment
        navigateToRegister.setOnClickListener {
            fragmentManager = requireActivity().supportFragmentManager
            fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.auth_framelayout, RegisterFragment())
            fragmentTransaction.commit()
        }
    }
}