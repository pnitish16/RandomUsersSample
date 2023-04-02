package com.example.randomuserapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.randomuserapp.data.network.model.ApiUserItem
import com.example.randomuserapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val isLoading : LiveData<Boolean> get() = _isLoading
    private var _isLoading = MutableLiveData(false)

    fun getRandomUsers(
    ): Flow<PagingData<ApiUserItem>> {
        _isLoading.value = true
        val userList = repository.getRandomUsers(
        ).cachedIn(viewModelScope)
        _isLoading.value = false
        return userList
    }

}
