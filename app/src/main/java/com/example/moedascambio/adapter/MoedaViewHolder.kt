package com.example.moedascambio.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moedascambio.R
import com.example.moedascambio.model.MoedaModel
import java.text.DecimalFormat

class MoedaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//dados para o adapter
    private val tv_Moeda = itemView.findViewById<TextView>(R.id.textView_moeda_title)
    private val tv_PorcentMoeda = itemView.findViewById<TextView>(R.id.textView_moeda_porcent)

    fun preencher(moedaModel: MoedaModel) {

        tv_Moeda.text = moedaModel.isoMoeda
        tv_PorcentMoeda.text = moedaModel.variacao_moeda.toString()
        if (moedaModel.variacao_moeda !! < 0){
            tv_PorcentMoeda.setTextColor(Color.RED)
        }else if(moedaModel.variacao_moeda > 0){
            tv_PorcentMoeda.setTextColor(Color.GREEN)
        }else{
            tv_PorcentMoeda.setTextColor(Color.WHITE)
        }
        val formatarItem = DecimalFormat("#.##")
            tv_PorcentMoeda.text = formatarItem.format(moedaModel.variacao_moeda).toString() +  "%"



    }


}
