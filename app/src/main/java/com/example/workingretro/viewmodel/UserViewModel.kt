package com.example.workingretro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workingretro.dtos.UserSdTo
import com.example.workingretro.retroclientandapibridge.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel()
{
    private val _userVm = MutableStateFlow<List<UserSdTo>>(emptyList())
    val userVm: StateFlow<List<UserSdTo>> = _userVm

    fun fetchUsers() {
        viewModelScope.launch {

            try {
                val response = RetrofitClient.apiService.getAllUsers()
                if(response.isSuccessful)
                {
                    _userVm.value = response.body() ?: emptyList()
                }
            }catch (e: Exception)
            {
                e.printStackTrace()
            }

        }
    }

}