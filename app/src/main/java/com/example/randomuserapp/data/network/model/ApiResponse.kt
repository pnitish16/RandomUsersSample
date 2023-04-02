package com.example.randomuserapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("results")
	val results: List<ApiUserItem> = emptyList(),

	@field:SerializedName("info")
	val info: Info? = null
)

data class Info(

	@field:SerializedName("seed")
	val seed: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("results")
	val results: Int? = null,

	@field:SerializedName("version")
	val version: String? = null
)
