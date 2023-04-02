package com.example.randomuserapp.data.network.mapper

import com.example.randomuserapp.data.network.model.ApiUserItem
import com.example.randomuserapp.domain.model.UserItem

class ApiMapperImpl : ApiMapper {
    override fun mapApiUserItemToDomain(apiUserItem: ApiUserItem): UserItem {
        return with(apiUserItem) {
            val fullName =
                "${this.name?.title ?: ""}.${this.name?.first ?: ""} ${this.name?.last ?: ""}"
            val dob = this.dob.toString().subSequence(0, 11)
            UserItem(
                id = "",
                fullName = fullName,
                dob = dob.toString(),
                profileImage = this.picture?.medium.toString(),
                thumbnail = this.picture?.thumbnail.toString(),
                address = fullName
            )
        }
    }

}