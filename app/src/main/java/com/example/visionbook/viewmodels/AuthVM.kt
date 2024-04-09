package com.example.visionbook.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.visionbook.models.api.AuthApi
import com.example.visionbook.models.dataclasses.RegistrationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthVM : ViewModel() {


    private val _emailState = MutableLiveData<String>()
    val emailState: LiveData<String> = _emailState
    private val _passwordState = MutableLiveData<String>()
    val passwordState: LiveData<String> = _passwordState
    private val _secondPasswordState = MutableLiveData<String>()
    val secondPasswordState: LiveData<String> = _secondPasswordState

    private val _passwordMatchesState = MutableLiveData<String>()
    val passwordMatchesState: LiveData<String> = _passwordMatchesState

    private val _tokenState = MutableLiveData<String>()
    val tokenState: LiveData<String> = _tokenState

    fun checkPasswordMatch(password: String, secondPassword: String?): Boolean {
        return if (secondPassword != null) {
            password == secondPassword
        } else {
            true
        }
    }
    fun setToken(token: String) {
        _tokenState.value = token
    }

    suspend fun registration(email: String, password: String, authApi: AuthApi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    authApi.userRegistration(
                        RegistrationModel(
                            email,
                            password
                        )
                    )
                }
            } catch (e: Exception) {
                // Обработка ошибок
                e.printStackTrace()
            }
        }
    }

    suspend fun authorization(email: String, password: String, authApi: AuthApi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                withContext(Dispatchers.Main) {
                    val userToken = authApi.userLogin(
                        RegistrationModel(
                            email,
                            password
                        )

                    )
                    Log.d("ВЕРНИ ТОКЕН ЗАРАЗА", userToken)
                    // Установка токена после успешной аутентификации
                    setToken(userToken)
                }
            } catch (e: Exception) {
                // Обработка ошибок
                e.printStackTrace()
            }
        }
    }


}