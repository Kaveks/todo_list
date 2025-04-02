package com.example.todolist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun TaskItem(task: Task, onRemove: () -> Unit, onToggleComplete: (Task) -> Unit) {
    // Wrap the TaskItem content with AnimatedVisibility to control its visibility individually
    AnimatedVisibility(
        visible = true, // You can change the logic here to control visibility based on task completion or any other state
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = { onToggleComplete(task) }
                )
            }
            Text(
                text = task.name,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .then(if (task.completed) Modifier.graphicsLayer { alpha = 0.5f } else Modifier)
            )
            Button(onClick = onRemove,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red // Red "Remove" Button
                )
            ) {
                Text("Remove")
            }
        }
    }
}