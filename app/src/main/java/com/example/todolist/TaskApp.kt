package com.example.todolist

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.data.TaskDataStore


data class Task(val name: String,var completed:Boolean=false)

@Composable
fun TaskApp(context: Context) {
    val taskDataStore = remember { TaskDataStore(context) }
    var tasks by remember { mutableStateOf(taskDataStore.loadTasks()) }
    var newTask by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input for new task
                OutlinedTextField(  // ✅ Switched from BasicTextField to OutlinedTextField
                    value = newTask,
                    onValueChange = { newTask = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Enter a task") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button to add task
                Button(onClick = {

                    if (newTask.isNotBlank()) {
                        tasks = tasks + Task(newTask)
                        newTask = ""
                        taskDataStore.saveTasks(tasks) // ✅ Save tasks when a new one is added
                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue // Blue "Add Task" Button
                    )
                ) {
                    Text("Add Task")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Display tasks
                LazyColumn {
                    itemsIndexed(tasks) { index, task ->

                        TaskItem(
                            task = task, onRemove = {
                                tasks = tasks.filterIndexed { i, _ -> i != index }
                                taskDataStore.saveTasks(tasks) // ✅ Save tasks after removing one
                            },
                            onToggleComplete = { toggledTask ->
                                tasks = tasks.map { task ->
                                    if (task == toggledTask) task.copy(completed = !task.completed) else task
                                }
                                taskDataStore.saveTasks(tasks)
                            }

                        )

                    }
                }
            }
        }
    )
}
