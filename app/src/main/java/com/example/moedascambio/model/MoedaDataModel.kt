package com.example.moedascambio.model

//Modelo as ser integrado
import com.google.gson.annotations.SerializedName

data class MoedaDataModel(

    @SerializedName("currencies")
    var currencies: ResultData = ResultData()
)

data class ResultData(

    @SerializedName("USD")
    var USD: MoedaModel? = null,
    @SerializedName("EUR")
    var EUR: MoedaModel? = null,
    @SerializedName("GBP")
    var GBP: MoedaModel? = null,
    @SerializedName("ARS")
    var ARS: MoedaModel? = null,
    @SerializedName("CAD")
    var CAD: MoedaModel? = null,
    @SerializedName("AUD")
    var AUD: MoedaModel? = null,
    @SerializedName("JPY")
    var JPY: MoedaModel? = null,
    @SerializedName("CNY")
    var CNY: MoedaModel? = null,
    @SerializedName("BTC")
    var BTC: MoedaModel? = null
)





