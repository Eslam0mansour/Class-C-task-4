package com.example.class_c_task_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.class_c_task_4.databinding.ActivityMainBinding
import com.example.class_c_task_4.services.RetrofitInstanceTodoListApi
import com.example.class_c_task_4.utils.adapter
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var todoListApiAdapter: adapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBarTodoListApi.isVisible = true // Show Progress Bar
            val response = try {
                RetrofitInstanceTodoListApi.api.getTodos() // Getting List of Todos Data
            } catch (e: IOException) {
                Log.d("TodoListApiActivity", "You may have no internet connection")
                binding.progressBarTodoListApi.isVisible = false // Disable Progress Bar
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d("TodoListApiActivity", "Unexpected Response")
                binding.progressBarTodoListApi.isVisible = false // Disable Progress Bar
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                todoListApiAdapter.todoss = response.body()!!
            } else {
                Log.d("TodoListApiActivity", "Response is not successful")
            }
            binding.progressBarTodoListApi.isVisible = false // Disable Progress Bar
        }

    }

    private fun setupRecyclerView() = binding.rvTodoListApi.apply {
        todoListApiAdapter = adapter()
        adapter = todoListApiAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
    }
