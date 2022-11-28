package com.example.todolist.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue),
) {
    val homeScreenVimel: HomeScreenVimel = viewModel()
    val state = homeScreenVimel.state.collectAsState()
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen( modifier = Modifier
        .padding(0.dp)
        .fillMaxSize()
        .background(Color.Black))
}