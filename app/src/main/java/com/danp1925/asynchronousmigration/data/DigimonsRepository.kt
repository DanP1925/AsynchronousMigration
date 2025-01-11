package com.danp1925.asynchronousmigration.data

import com.danp1925.asynchronousmigration.domain.Digimon
import com.danp1925.asynchronousmigration.domain.IDigimonsRepository

class DigimonsRepository() : IDigimonsRepository {

    override fun getDigimons(): List<Digimon> {
        return makeFakeDigimons()
    }

    private fun makeFakeDigimons() = listOf(
        Digimon(name = "Monodramon", id = "BT1-010"),
        Digimon(name = "Agumon", id = "BT1-011")
    )

}