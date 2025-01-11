package com.danp1925.asynchronousmigration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(emptyList<Digimon>())
    val uiState: StateFlow<List<Digimon>> = _uiState

    init {
        _uiState.update { fetchDigimons() }
    }

    private fun fetchDigimons() = makeFakeDigimons()

    private fun makeFakeDigimons() = listOf(
        Digimon(name = "Monodramon", id = "BT1-010"),
        Digimon(name = "Agumon", id = "BT1-011")
    )
}