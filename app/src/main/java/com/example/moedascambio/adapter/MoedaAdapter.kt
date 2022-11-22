package com.example.moedascambio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moedascambio.R
import com.example.moedascambio.model.MoedaModel

class MoedaAdapter(var onclick: (MoedaModel) -> Unit = {}) : RecyclerView.Adapter<MoedaViewHolder>() {
    private val listaDeMoedas = mutableListOf<MoedaModel?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoedaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_moeda, parent, false)
        return MoedaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoedaViewHolder, position: Int) {
        listaDeMoedas[position]?.let { preencherMoeda ->
            holder.preencher(preencherMoeda)
            holder.itemView.setOnClickListener {
                onclick.invoke(preencherMoeda)
            }
        }

    }
    override fun getItemCount(): Int = listaDeMoedas.size

    fun refresh(newList: List<MoedaModel?>) {
        listaDeMoedas.clear()
        listaDeMoedas.addAll(newList)


    }

}



