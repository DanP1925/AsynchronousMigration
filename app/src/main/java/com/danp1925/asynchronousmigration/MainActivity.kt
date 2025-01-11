package com.danp1925.asynchronousmigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.danp1925.asynchronousmigration.data.DigimonsRepository
import com.danp1925.asynchronousmigration.domain.GetDigimonsUseCase
import com.danp1925.asynchronousmigration.presentation.MainScreen
import com.danp1925.asynchronousmigration.presentation.MainViewModel
import com.danp1925.asynchronousmigration.ui.theme.AsynchronousMigrationTheme

class MainActivity : ComponentActivity() {

    private val viewModel = MainViewModel(
        GetDigimonsUseCase(digimonsRepository = DigimonsRepository())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AsynchronousMigrationTheme {
                MainScreen(viewModel)
            }
        }
    }
}
