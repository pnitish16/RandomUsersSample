package com.example.randomuserapp.data.network.mapper

import com.example.randomuserapp.data.network.model.ApiUserItem
import com.example.randomuserapp.domain.model.UserItem

interface ApiMapper {

  fun mapApiUserItemToDomain(apiUserItem: ApiUserItem): UserItem
}