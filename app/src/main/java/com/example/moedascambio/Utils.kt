package com.example.moedascambio

import android.graphics.Color
import android.widget.TextView
import com.example.moedascambio.model.MoedaModel
import java.text.NumberFormat
import java.util.*


object Utils {

    fun mapeiaNome(moedas: List<MoedaModel?>): List<MoedaModel?> {
        return moedas.map {
            it?.apply {
                it.isoMoeda =
                    when (it.nome_moeda) {
                        "Dollar" -> "USD"
                        "Euro" -> "EUR"
                        "Argentine Peso" -> "ARS"
                        "Canadian Dollar" -> "CAD"
                        "Pound Sterling" -> "GBP"
                        "Australian Dollar" -> "AUD"
                        "Japanese Yen" -> "JPY"
                        "Renminbi" -> "CNY"
                        "Bitcoin" -> "BTC"
                       else -> ""
                }
            }
        }
    }

    fun alteraCorDaVariacaoDaMoeda(moedaModel: MoedaModel, tvPorcentagemDaMoeda: TextView) {
        val variacaoDaMoeda = moedaModel.variacao_moeda
        val cor: String = when {
            variacaoDaMoeda < 0 -> "#D0021B"
            variacaoDaMoeda > 0 -> "#7ED321"
            else -> "#FFFFFFFF"
        }
        tvPorcentagemDaMoeda.setTextColor(Color.parseColor(cor))
    }

    fun formataMoedaBrasileira (moedaBrasileira: Double?): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(moedaBrasileira)
    }

    fun formatarPorcentage(valor: Double?) : String{
        val br = Locale("pt", "BR")
        val formatarPorcentagem = NumberFormat.getNumberInstance(br)
        formatarPorcentagem.minimumFractionDigits = 2
        formatarPorcentagem.maximumFractionDigits = 2
        return "${formatarPorcentagem.format(valor)}%"
    }


}