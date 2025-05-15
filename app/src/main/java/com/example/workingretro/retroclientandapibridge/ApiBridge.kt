package com.example.workingretro.retroclientandapibridge

import com.example.workingretro.dtos.UserDto
import com.example.workingretro.dtos.UserSdTo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiBridge {
    //for creating user
    @POST("api/User")
    suspend fun createUser(@Body user: UserDto): Response<UserDto>
    //for showing users
    @GET("api/User")
    suspend fun getAllUsers(): Response<List<UserSdTo>>
    //delete user
    @DELETE("api/User/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Void>
    //update user
    @POST("api/User/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UserSdTo): Response<UserSdTo>
}