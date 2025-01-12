package com.danp1925.asynchronousmigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.danp1925.asynchronousmigration.data.DigimonRepository
import com.danp1925.asynchronousmigration.data.DigimonService
import com.danp1925.asynchronousmigration.domain.GetDigimonsUseCase
import com.danp1925.asynchronousmigration.presentation.MainScreen
import com.danp1925.asynchronousmigration.presentation.MainViewModel
import com.danp1925.asynchronousmigration.ui.theme.AsynchronousMigrationTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://digimoncard.io/api-public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val digimonService = retrofit.create(DigimonService::class.java)

    private val viewModel = MainViewModel(
        GetDigimonsUseCase(
            DigimonRepository(digimonService)
        )
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
