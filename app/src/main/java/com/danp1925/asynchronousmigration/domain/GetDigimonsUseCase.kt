package com.danp1925.asynchronousmigration.domain

class GetDigimonsUseCase(
    private val digimonRepository: IDigimonsRepository
) {

    operator fun invoke(onSuccess: (List<Digimon>) -> Unit, onFailure: (String) -> Unit) =
        digimonRepository.getDigimons(onSuccess, onFailure)

}