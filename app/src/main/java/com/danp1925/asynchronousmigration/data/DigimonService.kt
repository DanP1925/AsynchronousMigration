package com.danp1925.asynchronousmigration.data

import retrofit2.Call
import retrofit2.http.GET

interface DigimonService {

    @GET("search.php?type=digimon&pack=BT-01:%20Booster%20New%20Evolution")
    fun getDigimons(): Call<List<RemoteDigimon>>

}