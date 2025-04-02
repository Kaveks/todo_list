package com.example.todolist.data

import android.content.Context
import android.content.SharedPreferences
import com.example.todolist.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class TaskDataStore(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("task_data", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveTasks(tasks: List<Task>) {
        val json = gson.toJson(tasks)
        sharedPreferences.edit { putString("tasks", json) } // ✅ Fixed extra parentheses
    }

    fun loadTasks(): List<Task> {
        val json = sharedPreferences.getString("tasks", null)
        val type = object : TypeToken<List<Task>>() {}.type
        return if (json != null) gson.fromJson(json, type) else emptyList()
    }
}
