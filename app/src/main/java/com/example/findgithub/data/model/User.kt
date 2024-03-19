package com.example.findgithub.data.model

data class User(
    val id: Int,
    val login: String,
    val name: String,
    val followers_url: String,
    val following_url: String,
    val avatar_url:String
)
