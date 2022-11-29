package com.example.moedascambio

import com.example.moedascambio.model.MoedaModel


object SingletonSimulandoValores {
    var totalsaldodisnponivel : Double = 1000.00

    private var usd = 6
    private var eur = 1
    private var gbp = 0
    private var ars = 3
    private var cad = 2
    private var aud = 0
    private var jpy = 4
    private var cny = 5
    private var btc = 2

    fun buscaValorSimulado(moeda: MoedaModel) {
        when {
            moeda.isoMoeda == "USD" -> moeda.totalsaldocaixa = usd
            moeda.isoMoeda == "EUR" -> moeda.totalsaldocaixa = eur
            moeda.isoMoeda == "GBP" -> moeda.totalsaldocaixa = gbp
            moeda.isoMoeda == "ARS" -> moeda.totalsaldocaixa = ars
            moeda.isoMoeda == "CAD" -> moeda.totalsaldocaixa = cad
            moeda.isoMoeda == "AUD" -> moeda.totalsaldocaixa = aud
            moeda.isoMoeda == "JPY" -> moeda.totalsaldocaixa = jpy
            moeda.isoMoeda == "CNY" -> moeda.totalsaldocaixa = cny
            moeda.isoMoeda == "BTC" -> moeda.totalsaldocaixa = btc
        }
    }

    fun modificaValorPosOperacao(moeda: MoedaModel) {
        when {
            moeda.isoMoeda == "USD" -> usd = moeda.totalsaldocaixa
            moeda.isoMoeda == "EUR" -> eur = moeda.totalsaldocaixa
            moeda.isoMoeda == "GBP" -> gbp = moeda.totalsaldocaixa
            moeda.isoMoeda == "ARS" -> ars = moeda.totalsaldocaixa
            moeda.isoMoeda == "CAD" -> cad = moeda.totalsaldocaixa
            moeda.isoMoeda == "AUD" -> aud = moeda.totalsaldocaixa
            moeda.isoMoeda == "JPY" -> jpy = moeda.totalsaldocaixa
            moeda.isoMoeda == "CNY" -> cny = moeda.totalsaldocaixa
            moeda.isoMoeda == "BTC" -> btc = moeda.totalsaldocaixa
        }
    }


}