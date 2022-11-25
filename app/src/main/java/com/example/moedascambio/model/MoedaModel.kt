package com.example.moedascambio.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
// As informações que serão retornadas
data class MoedaModel(

    @SerializedName("name")
    val nome_moeda : String? = null,

    @SerializedName("variation")
    val variacao_moeda: Double = 0.0,

    @SerializedName("buy")
    val valor_compra: Double = 0.0,

    @SerializedName("sell")
    val valor_venda: Double = 0.0,
    var isoMoeda: String = "",
    var totalsaldocaixa : Int = 0

) : Serializable



