package com.danp1925.asynchronousmigration.domain

class GetDigimonsUseCase(
    private val digimonsRepository: IDigimonsRepository
) {

    operator fun invoke() = digimonsRepository.getDigimons()

}