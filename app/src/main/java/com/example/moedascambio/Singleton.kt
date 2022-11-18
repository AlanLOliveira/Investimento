package com.example.moedascambio

import com.example.moedascambio.model.MoedaModel

object Singleton {
    var totalsaldodisnponivel : Double = 1000.00
    var totalsaldocaixa : Int = 0
    var usd = 6
    var eur = 1
    var gbp = 0
    var ars = 3
    var cad = 2
    var aud = 0
    var jpy = 4
    var cny = 5
    var btc = 2

    fun buscaValorSimuladoParaModel(moeda: MoedaModel) {
        when {
            moeda.isoMoeda.equals("USD") -> totalsaldocaixa = usd
            moeda.isoMoeda.equals("EUR") -> totalsaldocaixa = eur
            moeda.isoMoeda.equals("GBP") -> totalsaldocaixa = gbp
            moeda.isoMoeda.equals("ARS") -> totalsaldocaixa = ars
            moeda.isoMoeda.equals("CAD") -> totalsaldocaixa = cad
            moeda.isoMoeda.equals("AUD") -> totalsaldocaixa = aud
            moeda.isoMoeda.equals("JPY") -> totalsaldocaixa = jpy
            moeda.isoMoeda.equals("CNY") -> totalsaldocaixa = cny
            moeda.isoMoeda.equals("BTC") -> totalsaldocaixa = btc
        }
    }

    fun modificaValorPosOperacao(moeda: MoedaModel) {
        when {
            moeda.isoMoeda.equals("USD") -> usd = totalsaldocaixa
            moeda.isoMoeda.equals("EUR") -> eur = totalsaldocaixa
            moeda.isoMoeda.equals("GBP") -> gbp = totalsaldocaixa
            moeda.isoMoeda.equals("ARS") -> ars = totalsaldocaixa
            moeda.isoMoeda.equals("CAD") -> cad = totalsaldocaixa
            moeda.isoMoeda.equals("AUD") -> aud = totalsaldocaixa
            moeda.isoMoeda.equals("JPY") -> jpy = totalsaldocaixa
            moeda.isoMoeda.equals("CNY") -> cny = totalsaldocaixa
            moeda.isoMoeda.equals("BTC") -> btc = totalsaldocaixa
        }
    }


}