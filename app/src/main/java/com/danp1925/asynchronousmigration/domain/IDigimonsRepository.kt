package com.danp1925.asynchronousmigration.domain

interface IDigimonsRepository {
    fun getDigimons(onSuccess:(List<Digimon>)-> Unit)
}