package com.example.moedascambio.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
// As informações que serão retornadas
data class MoedaModel(

    @SerializedName("name")
    val nome_moeda : String? = null,

    @SerializedName("variation")
    val variacao_moeda: Double? = null,

    @SerializedName("buy")
    val valor_compra: Double? = null,

    @SerializedName("sell")
    val valor_venda: Double? = null,
    var isoMoeda: String = ""

) : Serializable

