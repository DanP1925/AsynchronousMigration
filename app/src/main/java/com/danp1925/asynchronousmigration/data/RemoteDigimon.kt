package com.danp1925.asynchronousmigration.data

import com.danp1925.asynchronousmigration.domain.Digimon
import com.google.gson.annotations.SerializedName

data class RemoteDigimon(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: String
) {

    companion object {
        fun fromDomain(digimon: Digimon) = RemoteDigimon(
            name = digimon.name,
            id = digimon.id
        )
    }

    fun toDomain() = Digimon(
        id = id,
        name = name
    )

}