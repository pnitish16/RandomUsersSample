package com.example.randomuserapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(
    val id: String,
    val fullName: String,
    val dob: String,
    val profileImage: String,
    val thumbnail: String,
    val address: String
) : Parcelable