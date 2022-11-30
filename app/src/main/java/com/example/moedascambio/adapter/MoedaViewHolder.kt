package com.example.moedascambio.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moedascambio.R
import com.example.moedascambio.Utils
import com.example.moedascambio.model.MoedaModel
import java.math.RoundingMode


class MoedaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvMoeda = itemView.findViewById<TextView>(R.id.textView_moeda_title)
    private val tvPorcentagemMoeda = itemView.findViewById<TextView>(R.id.textView_moeda_porcent)



    fun preencherTituloMoeda(moedaModel: MoedaModel) {

        tvMoeda.text = moedaModel.isoMoeda
        Utils.alteraCorDaVariacaoDaMoeda(moedaModel,tvPorcentagemMoeda )
        tvPorcentagemMoeda.text = buildString {
            append((moedaModel.variacao_moeda).toString().toBigDecimal().setScale(2,RoundingMode.UP))
            append("%")

    }
        acessibilidadeMoeda()
    }

    private fun acessibilidadeMoeda(){
        tvMoeda.let { tvMoeda->
            tvMoeda.contentDescription = "A variação da moeda ${tvMoeda.text} é de "
       }
    }


}
