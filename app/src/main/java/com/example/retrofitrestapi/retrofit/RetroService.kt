package com.example.retrofitrestapi.retrofit

import com.example.retrofitrestapi.Constants
import com.example.retrofitrestapi.User
import com.example.retrofitrestapi.UserList
import com.example.retrofitrestapi.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    /* https://gorest.co.in/public/v1/users */
    @GET("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json")
    fun getUsersList(): Call<UserList>


    /* https://gorest.co.in/public/v1/users/user_id */
    @GET("users/{user_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json"
    )
    fun getUser(@Path("user_id") user_id: String): Call<UserResponse>


    /* https://gorest.co.in/public/v1/users?name=a */
    @GET("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<UserList>


    @POST("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer ${Constants.ACCESS_TOKEN}")
    fun createUser(@Body params: User): Call<UserResponse>


    @PATCH("users/{user_id}")
    @Headers(
        "Accept: application/json",
        "Content-Type:application/json",
        "Authorization: Bearer ${Constants.ACCESS_TOKEN}")
    fun updateUser(
        @Path("user_id")
        user_id: String,
        @Body params: User
    ): Call<UserResponse>


    @DELETE("users/{user_id}")
    @Headers(
        "Accept:application/json",
        "Content_Type:application/json",
        "Authorization: Bearer ${Constants.ACCESS_TOKEN}")
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
}