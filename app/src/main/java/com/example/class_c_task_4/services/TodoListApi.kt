package com.example.class_c_task_4.services

import com.example.class_c_task_4.model.TodoListApiData
import retrofit2.Response
import retrofit2.http.GET

interface TodoListApi {

    @GET("/todos")
    suspend fun getTodos() : Response<List<TodoListApiData>>

}