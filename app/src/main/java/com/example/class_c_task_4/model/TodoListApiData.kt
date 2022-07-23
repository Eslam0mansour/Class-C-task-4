package com.example.class_c_task_4.model

data class TodoListApiData(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)