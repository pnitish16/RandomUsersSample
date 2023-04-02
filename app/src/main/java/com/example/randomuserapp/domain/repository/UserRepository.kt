package com.example.randomuserapp.domain.repository

import androidx.paging.PagingData
import com.example.randomuserapp.data.network.model.ApiUserItem
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getRandomUsers(): Flow<PagingData<ApiUserItem>>
}