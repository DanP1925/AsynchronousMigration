package com.danp1925.asynchronousmigration.data

import com.danp1925.asynchronousmigration.domain.Digimon
import com.danp1925.asynchronousmigration.domain.IDigimonsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DigimonRepository(
    private val digimonService: DigimonService
) : IDigimonsRepository {

    override fun getDigimons(onSuccess: (List<Digimon>) -> Unit) {
        digimonService.getDigimons().enqueue(
            object : Callback<List<RemoteDigimon>> {
                override fun onResponse(
                    call: Call<List<RemoteDigimon>>,
                    response: Response<List<RemoteDigimon>>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()?.map { it.toDomain() } ?: emptyList())
                    }
                }

                override fun onFailure(call: Call<List<RemoteDigimon>>, p1: Throwable) {
                }
            }
        )
    }

}