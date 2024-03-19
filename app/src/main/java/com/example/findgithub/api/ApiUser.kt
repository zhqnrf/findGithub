package com.example.findgithub.api

import com.example.findgithub.data.model.DetailUserResponse
import com.example.findgithub.data.model.User
import com.example.findgithub.data.model.UserRandom
import com.example.findgithub.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUser {
    @GET("search/users")
    @Headers("Authorization:token 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")
    fun getSearchUsers(
        @Query("q") query : String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ):Call<DetailUserResponse>

    @GET("users")
    @Headers("Authorization: token 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")
    fun getRandomUser(
        @Query("q") query: String
    ):Call<UserResponse>

    @GET("user/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUser(
        @Path("username") username: String
    ):Call<ArrayList<User>>

    @GET("user/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username") username: String
    ):Call<ArrayList<User>>


}