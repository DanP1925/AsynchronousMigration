package com.danp1925.asynchronousmigration.presentation

import androidx.lifecycle.ViewModel
import com.danp1925.asynchronousmigration.domain.Digimon
import com.danp1925.asynchronousmigration.domain.GetDigimonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val getDigimons: GetDigimonsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(emptyList<Digimon>())
    val uiState: StateFlow<List<Digimon>> = _uiState

    init {
        getDigimons(
            onSuccess = { digimons ->
                _uiState.update { digimons }
            }
        )
    }

}