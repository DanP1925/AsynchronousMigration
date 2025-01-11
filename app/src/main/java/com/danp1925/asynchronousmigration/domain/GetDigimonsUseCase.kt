package com.danp1925.asynchronousmigration.domain

class GetDigimonsUseCase {

    operator fun invoke() = makeFakeDigimons()

    private fun makeFakeDigimons() = listOf(
        Digimon(name = "Monodramon", id = "BT1-010"),
        Digimon(name = "Agumon", id = "BT1-011")
    )

}