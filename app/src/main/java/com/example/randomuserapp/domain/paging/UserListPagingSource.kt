package com.example.randomuserapp.domain.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomuserapp.data.network.client.UserApiClient
import com.example.randomuserapp.data.network.model.ApiUserItem

class UserListPagingSource(
    private val userApiClient: UserApiClient,
) : PagingSource<Int, ApiUserItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiUserItem> {
        return try {
            val position = params.key ?: 1
            val response = userApiClient.getRandomUsers(
                page = position
            )

            response.body()?.let { it ->
                return LoadResult.Page(
                    data = it.results,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position >= 1000) //need to insert the last page from the api call
                        null else position + 1
                )
            }
            return LoadResult.Error(throwable = Throwable("No Item found"))
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiUserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
