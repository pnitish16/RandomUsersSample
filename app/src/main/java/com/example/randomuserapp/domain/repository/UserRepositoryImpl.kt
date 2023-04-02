package com.example.randomuserapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.randomuserapp.data.network.client.UserApiClient
import com.example.randomuserapp.data.network.model.ApiUserItem
import com.example.randomuserapp.domain.paging.UserListPagingSource
import com.example.randomuserapp.util.Constants
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userApiClient: UserApiClient
) : UserRepository {
    override fun getRandomUsers(): Flow<PagingData<ApiUserItem>> = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE
        ),
        pagingSourceFactory = {
            UserListPagingSource(
                userApiClient = userApiClient,
            )
        }
    ).flow
}