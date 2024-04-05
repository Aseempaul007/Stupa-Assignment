package com.example.stupa.helper

import java.util.regex.Pattern

object Validator {

    private const val EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"

    private const val PASSWORD_REGEX = ".{8,}"

    private const val PHONE_NUMBER_REGEX = "\\d{10,}"

    private const val NAME_REGEX = "^[a-zA-Z\\s]+\$" // only alphabets and spaces

    private val emailPattern = Pattern.compile(EMAIL_REGEX)
    private val passwordPattern = Pattern.compile(PASSWORD_REGEX)
    private val phonePattern = Pattern.compile(PHONE_NUMBER_REGEX)
    private val namePattern = Pattern.compile(NAME_REGEX)

    fun isValidEmail(email: String): Boolean {
        val matcher = emailPattern.matcher(email)
        return matcher.matches()
    }

    fun isValidPassword(password: String): Boolean {
        val matcher = passwordPattern.matcher(password)
        return matcher.matches()
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val matcher = phonePattern.matcher(phoneNumber)
        return matcher.matches()
    }

    fun isValidName(name: String): Boolean {
        val matcher = namePattern.matcher(name)
        return matcher.matches()
    }
}

