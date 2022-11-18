package com.example.moedascambio.repository

import com.example.moedascambio.model.MoedaDataModel
import com.example.moedascambio.service.MoedaRetrofit



class RepositorioMoeda {

    private val services = MoedaRetrofit().moedaRetrofitInstance()

    suspend fun lerMoedas(): MoedaDataModel {
        return services.buscarMoedas()
    }
}