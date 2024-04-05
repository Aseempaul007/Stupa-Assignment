package com.example.stupa.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.stupa.R
import com.example.stupa.data.local.entity.User
import com.example.stupa.databinding.FragmentRegisterBinding
import com.example.stupa.helper.Const
import com.example.stupa.helper.Validator
import com.example.stupa.view.activity.HomeActivity
import com.example.stupa.viewmodel.UserViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var emailTextView: EditText
    private lateinit var passwordTextView: EditText
    private lateinit var nameTextView: EditText
    private lateinit var phoneNumberTextView: EditText
    private lateinit var countrySpinner: Spinner
    private lateinit var registerButton: Button
    private lateinit var navigateToLogin: TextView
    private lateinit var user: User
    private lateinit var country: String
    private lateinit var viewmodel: UserViewmodel
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private val countries = Const.countries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViews()
        viewmodel = ViewModelProvider(this).get(UserViewmodel::class.java)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                country = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Please select country", Toast.LENGTH_SHORT).show()
            }
        }
        navigateToLogin.setOnClickListener {
            fragmentManager = requireActivity().supportFragmentManager
            fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.auth_framelayout, LoginFragment())
            fragmentTransaction.commit()
        }

        registerButton.setOnClickListener {

            val name = nameTextView.text.toString()
            val email = emailTextView.text.toString()
            val password = passwordTextView.text.toString()
            val phone = phoneNumberTextView.text.toString()

            //validation
            if (!Validator.isValidName(name)) {
                nameTextView.error = Const.nameError
                return@setOnClickListener
            }

            if (!Validator.isValidEmail(email)) {
                emailTextView.error = Const.emailError;
                return@setOnClickListener;
            }

            if (!Validator.isValidPassword(password)) {
                passwordTextView.error = Const.passwordError
                return@setOnClickListener;
            }

            if (!Validator.isValidPhoneNumber(phone)) {
                phoneNumberTextView.error = Const.phoneError
                return@setOnClickListener;
            }

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
                user = User(0, name, email, password, country, phone)
                CoroutineScope(Dispatchers.IO).launch {
                    viewmodel.addStudent(user)
                }
                Toast.makeText(
                    requireContext(),
                    "Registration successful! Please login",
                    Toast.LENGTH_SHORT
                )
                    .show()
                activity?.let {
                    fragmentManager = requireActivity().supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.auth_framelayout, LoginFragment())
                    fragmentTransaction.commit()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter all the feilds", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initializeViews() {
        emailTextView = binding.edittextEmail
        passwordTextView = binding.edittextPassword
        nameTextView = binding.edittextName
        phoneNumberTextView = binding.edittextMobileNo
        countrySpinner = binding.spinnerCountry
        registerButton = binding.buttonSubmit
        navigateToLogin = binding.navigateToLogin
    }
}