package com.danp1925.asynchronousmigration.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danp1925.asynchronousmigration.domain.Digimon
import com.danp1925.asynchronousmigration.ui.theme.AsynchronousMigrationTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreenContent(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(uiState: MainUiState) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Digimons")
                }
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is MainUiState.Success -> {
                MainScreenSuccessful(
                    digimons = uiState.digimons,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is MainUiState.Error -> {}
            MainUiState.Loading -> {}
        }
    }
}

@Composable
private fun MainScreenSuccessful(
    digimons: List<Digimon>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(digimons) { digimon ->
            Row(
                modifier = Modifier.height(24.dp)
            ) {
                Text(digimon.id)
                Spacer(modifier = Modifier.width(16.dp))
                Text(digimon.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenSuccessfulPreview() {
    val digimons = listOf(
        Digimon(name = "Monodramon", id = "BT1-010"),
        Digimon(name = "Agumon", id = "BT1-011")
    )

    AsynchronousMigrationTheme {
        MainScreenSuccessful(digimons = digimons)
    }
}


