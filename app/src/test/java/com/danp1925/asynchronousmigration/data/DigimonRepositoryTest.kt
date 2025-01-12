package com.danp1925.asynchronousmigration.data

import com.danp1925.asynchronousmigration.domain.Digimon
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DigimonRepositoryTest {

    private val digimonService = mockk<DigimonService>()
    private val callbackSlot = slot<Callback<List<RemoteDigimon>>>()

    private val repository by lazy {
        DigimonRepository(digimonService)
    }


    @Test
    fun `GIVEN successful response WHEN getDigimons returns a response THEN onSuccess is called with digimons`() {
        val call = mockk<Call<List<RemoteDigimon>>>()
        val response = mockk<Response<List<RemoteDigimon>>>()
        val onSuccess = mockk<(List<Digimon>) -> Unit>()
        val digimons = listOf(
            RemoteDigimon(id = "BT1-009", name = "Monodramon"),
            RemoteDigimon(id = "BT1-010", name = "Agumon")
        )
        val expectedDigimons = listOf(
            Digimon(id = "BT1-009", name = "Monodramon"),
            Digimon(id = "BT1-010", name = "Agumon")
        )
        every { digimonService.getDigimons().enqueue(capture(callbackSlot)) } just runs
        every { response.isSuccessful } returns true
        every { response.body() } returns digimons
        every { onSuccess(any()) } just runs

        repository.getDigimons(onSuccess, {})
        callbackSlot.captured.onResponse(call, response)

        verify(exactly = 1) { onSuccess(expectedDigimons) }
    }

    @Test
    fun `GIVEN failed response WHEN getDigimons returns a response THEN onFailure is called with message`() {
        val call = mockk<Call<List<RemoteDigimon>>>()
        val response = mockk<Response<List<RemoteDigimon>>>()
        val onFailure = mockk<(String) -> Unit>()
        every { digimonService.getDigimons().enqueue(capture(callbackSlot)) } just runs
        every { response.isSuccessful } returns false
        every { onFailure(any()) } just runs

        repository.getDigimons({}, onFailure)
        callbackSlot.captured.onResponse(call, response)

        verify(exactly = 1) { onFailure(any()) }
    }

    @Test
    fun `GIVEN an exception WHEN getDigimons is called THEN onFailure is called with message`() {
        val call = mockk<Call<List<RemoteDigimon>>>()
        val onFailure = mockk<(String) -> Unit>()
        val errorMessage = "error message"
        val error = Exception(errorMessage)
        every { digimonService.getDigimons().enqueue(capture(callbackSlot)) } just runs
        every { onFailure(any()) } just runs

        repository.getDigimons({}, onFailure)
        callbackSlot.captured.onFailure(call, error)

        verify(exactly = 1) { onFailure(errorMessage) }
    }
}