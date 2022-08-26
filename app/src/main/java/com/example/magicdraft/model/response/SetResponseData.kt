package com.example.magicdraft.model.response

data class SetResponseData(
    val code: String,
    val name: String,
    val type: String,
    val booster: List<*>?,
    val releaseDate: String,
    val onlineOnly: Boolean
)
