package com.example.retrofitrestapi

data class UserList
constructor(val data: List<User>)

data class User
constructor(
    val id: String?,
    val name: String?,
    val email: String?,
    val status: String?,
    val gender: String?
)

data class UserResponse
constructor(
    val code: Int?,
    val meta: String?,
    val data: User?
)
