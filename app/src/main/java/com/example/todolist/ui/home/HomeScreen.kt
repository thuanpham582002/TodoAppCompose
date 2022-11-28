package com.example.todolist.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen() {
    val homeScreenVimel: HomeScreenVimel = viewModel()
    val state by homeScreenVimel.state.collectAsState()
    LazyColumn(content = {
        items(state.listTodoEntity.size) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
//                    .clip(MaterialTheme.shapes.medium)
                    .shadow(4.dp)
                    .border(2.dp, Color.Black),
            ) {
                Text(
                    text = state.listTodoEntity[it].title,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                Text(
                    text = state.listTodoEntity[it].description,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
            }
        }
    })
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}