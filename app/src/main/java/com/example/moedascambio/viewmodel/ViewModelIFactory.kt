@file:Suppress("UNCHECKED_CAST")

package com.example.moedascambio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moedascambio.repository.RepositorioMoeda

class ViewModelIFactory constructor(private val repositorio: RepositorioMoeda) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MoedaViewModel::class.java)) {
            MoedaViewModel(this.repositorio) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }
}
