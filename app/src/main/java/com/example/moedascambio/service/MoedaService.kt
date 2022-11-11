package com.example.moedascambio.service


import com.example.moedascambio.model.MoedaDataModel
import retrofit2.http.GET

// chama aPI
interface MoedaService {
    @GET("finance?fields=only_results,currencies&key=ebdfd183")
    suspend fun buscarMoedas() : MoedaDataModel

}