package com.danp1925.asynchronousmigration

import com.danp1925.asynchronousmigration.domain.Digimon
import com.danp1925.asynchronousmigration.domain.GetDigimonsUseCase
import com.danp1925.asynchronousmigration.presentation.MainUiState
import com.danp1925.asynchronousmigration.presentation.MainViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MainViewModelTest {

    private val getDigimonsUseCase = mockk<GetDigimonsUseCase>()
    private val onSuccessSlot = slot<(List<Digimon>) -> Unit>()
    private val onFailureSlot = slot<(String) -> Unit>()

    private val viewModel by lazy {
        MainViewModel(getDigimonsUseCase)
    }

    private val digimons = listOf(
        Digimon("BT1-009", "Monodramon"),
        Digimon("BT1-010", "Agumon")
    )

    @Test
    fun `GIVEN useCase returns succesfully WHEN initializing viewModel THEN show digimon list`() {
        val expectedDigimons = digimons
        every {
            getDigimonsUseCase(onSuccess = capture(onSuccessSlot), onFailure = any())
        } just Runs

        viewModel
        onSuccessSlot.captured.invoke(digimons)
        val uiState = viewModel.uiState.value

        assertTrue(uiState is MainUiState.Success)
        (uiState as MainUiState.Success).let {
            assertEquals(2, uiState.digimons.size)
            uiState.digimons.forEachIndexed { index, actualDigimon ->
                val expectedDigimon = expectedDigimons[index]
                assertEquals(expectedDigimon, actualDigimon)
            }
        }
    }

    @Test
    fun `GIVEN useCase fails WHEN initializing viewModel THEN show error message`() {
        val expectedErrorMessage = "error message"
        every {
            getDigimonsUseCase(onSuccess = any(), onFailure = capture(onFailureSlot))
        } just Runs

        viewModel
        onFailureSlot.captured.invoke(expectedErrorMessage)
        val uiState = viewModel.uiState.value

        assertTrue(uiState is MainUiState.Error)
        assertEquals(expectedErrorMessage, (uiState as MainUiState.Error).errorMessage)
    }
}