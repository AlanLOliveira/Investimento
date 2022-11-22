package com.example.moedascambio
import android.graphics.Color
import android.widget.TextView
import com.example.moedascambio.model.MoedaModel

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

   fun alteraCorDaVariacaoDaMoeda(moedaModel: MoedaModel, tvPorcentagemDaMoeda: TextView ){
     val variacaoDaMoeda = moedaModel.variacao_moeda
     val cor: String
     if(variacaoDaMoeda != null){
         cor = when {
             variacaoDaMoeda < 0 -> "#D0021B"
             variacaoDaMoeda > 0 -> "#7ED321"
             else -> "#FFFFFFFF"
          }
         tvPorcentagemDaMoeda.setTextColor(Color.parseColor(cor))
     }
   }
}