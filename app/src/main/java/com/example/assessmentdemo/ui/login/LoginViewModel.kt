package com.example.assessmentdemo.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assessmentdemo.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    private val _isFormValid = MediatorLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    init {
        _isFormValid.addSource(email) { validateForm() }
        _isFormValid.addSource(password) { validateForm() }
    }

    private fun validateForm() {
        val emailValid =
            Patterns.EMAIL_ADDRESS.matcher(email.value.orEmpty()).matches()

        val passwordValid =
            password.value.orEmpty().length in 8..15

        _isFormValid.value = emailValid && passwordValid
    }

    fun onLoginSuccess() {
        sessionManager.saveLogin()
    }

    fun isLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }
}