package com.example.trailmatetasklist
// Danielle Cook 2026
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

private data class Task(
    val id: Int,
    val title: String,
    val isDone: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TrailMateTasksApp()
            }
        }
    }
}

@Composable
fun TrailMateTasksApp() {
    val tasks = remember {
        mutableStateListOf(
            Task(1, "Pack water and snacks"),
            Task(2, "Choose a trail"),
            Task(3, "Check the weather")
        )
    }
    var taskInput by remember { mutableStateOf("") }
    var nextId by remember { mutableStateOf(4) }
    var showCompleted by remember { mutableStateOf(true) }

    val completedCount = tasks.count { it.isDone }
    val visibleTasks = if (showCompleted) tasks.toList() else tasks.filterNot { it.isDone }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "TrailMate",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Your simple hiking checklist",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Trip preparation", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("$completedCount of ${tasks.size} tasks completed")
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                value = taskInput,
                onValueChange = { taskInput = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Add a task") },
                placeholder = { Text("e.g., Bring a flashlight") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        val cleaned = taskInput.trim()
                        if (cleaned.isNotEmpty()) {
                            tasks.add(Task(nextId, cleaned))
                            nextId += 1
                            taskInput = ""
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val cleaned = taskInput.trim()
                    if (cleaned.isNotEmpty()) {
                        tasks.add(Task(nextId, cleaned))
                        nextId += 1
                        taskInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = taskInput.isNotBlank()
            ) {
                Text("Add task")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("My checklist", style = MaterialTheme.typography.titleLarge)
                TextButton(onClick = { showCompleted = !showCompleted }) {
                    Text(if (showCompleted) "Hide completed" else "Show all")
                }
            }

            Divider()

            if (visibleTasks.isEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = if (tasks.isEmpty()) "No tasks yet. Add one above!" else "All tasks are completed.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(visibleTasks, key = { it.id }) { task ->
                        TaskRow(
                            task = task,
                            onCheckedChange = { checked ->
                                val index = tasks.indexOfFirst { it.id == task.id }
                                if (index >= 0) tasks[index] = tasks[index].copy(isDone = checked)
                            },
                            onDelete = { tasks.removeAll { it.id == task.id } }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tip: add your own tasks before heading out.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TaskRow(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = onCheckedChange
            )
            Text(
                text = task.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                color = if (task.isDone) MaterialTheme.colorScheme.onSurfaceVariant
                else MaterialTheme.colorScheme.onSurface
            )
            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}
