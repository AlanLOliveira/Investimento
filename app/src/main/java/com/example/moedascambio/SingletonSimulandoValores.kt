package com.example.moedascambio

import com.example.moedascambio.main.COMPRA

object SingletonSimulandoValores {

    var totalsaldodisnponivel: Double = 950.35

    var valoresSimulados = HashMap<String, Int>().apply {

        put("USD", 0)
        put("EUR", 1)
        put("GBP", 2)
        put("ARS", 3)
        put("CAD", 4)
        put("AUD", 5)
        put("JPY", 6)
        put("CNY", 7)
        put("BTC", 8)
    }

    fun hashmapPegarValor(isoMoeda: String): Int {
        valoresSimulados[isoMoeda].let { quantidade ->
            if(quantidade != null)
                return quantidade
            else return 0
        }
    }

    fun alteraValorSimulado(
        isoMoeda: String,
        operacao: String,
        quantidade: Int
    ) {
        var saldoSimulado = valoresSimulados[isoMoeda]
        if (saldoSimulado != null) {
            if (operacao == COMPRA) {
                saldoSimulado += quantidade
            } else {
                saldoSimulado -= quantidade
            }
            valoresSimulados[isoMoeda] = saldoSimulado
        }
    }
}