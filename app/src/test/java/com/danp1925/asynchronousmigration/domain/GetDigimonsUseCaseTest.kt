package com.danp1925.asynchronousmigration.domain

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Test

class GetDigimonsUseCaseTest {

    private val digimonRepository = mockk<IDigimonsRepository>()
    private val useCase by lazy {
        GetDigimonsUseCase(digimonRepository)
    }

    @Test
    fun `WHEN useCase is invoked THEN repository getDigimons is called once`() {
        every { digimonRepository.getDigimons(any(), any()) } just runs

        useCase({}, {})

        verify(exactly = 1) { digimonRepository.getDigimons(any(), any()) }
    }

}