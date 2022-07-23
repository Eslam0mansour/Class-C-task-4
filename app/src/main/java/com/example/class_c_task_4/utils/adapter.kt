
package com.example.class_c_task_4.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.class_c_task_4.databinding.ItemTodoBinding
import com.example.class_c_task_4.model.TodoListApiData

class adapter : RecyclerView.Adapter<adapter.TodoListApiViewHolder> (){

    inner class TodoListApiViewHolder(val binding: ItemTodoBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<TodoListApiData>(){
        override fun areItemsTheSame(oldItem: TodoListApiData, newItem: TodoListApiData): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: TodoListApiData, newItem: TodoListApiData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ  = AsyncListDiffer(this,diffCallBack)
    var todoss : List<TodoListApiData>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListApiViewHolder {
        return TodoListApiViewHolder(ItemTodoBinding.inflate(LayoutInflater
            .from(parent.context),parent,false
        )
        )
    }

    override fun onBindViewHolder(holder: TodoListApiViewHolder, position: Int) {

        holder.binding.apply {
            val singleTodo =  todoss[position]
            txTodoApiTitle.text = singleTodo.title
            cbTodoApiDone.isChecked = singleTodo.completed
        }


    }

    override fun getItemCount() = todoss.size

}