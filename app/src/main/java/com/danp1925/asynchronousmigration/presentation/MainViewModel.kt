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

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Success())
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        _uiState.update { MainUiState.Loading }
        getDigimons(
            onSuccess = { digimons -> _uiState.update { MainUiState.Success(digimons) } }
        )
    }

}

sealed class MainUiState {
    data object Loading : MainUiState()
    data class Success(val digimons: List<Digimon> = emptyList()) : MainUiState()
    data class Error(val errorMessage: String) : MainUiState()
}