package com.example.moedascambio.viewmodel


import androidx.lifecycle.MutableLiveData
import com.example.moedascambio.model.MoedaModel
import com.example.moedascambio.repository.RepositorioMoeda


import kotlinx.coroutines.launch

//São as informações que serão jogadas na view
class MoedaViewModel (private val repositorioMoeda: RepositorioMoeda): BaseViewModel() {

    val listaDeMoedas = MutableLiveData<List<MoedaModel?>>()
    val errorTest = MutableLiveData<String>()
    fun atualizaMoedas() {
        launch {
            try {

                val call = repositorioMoeda.lerMoedas()
                val listaTodasAsMoedas = mapeiaNome(
                    listOfNotNull(

                    call.currencies.USD,
                    call.currencies.EUR,
                    call.currencies.ARS,
                    call.currencies.CAD,
                    call.currencies.GBP,
                    call.currencies.AUD,
                    call.currencies.JPY,
                    call.currencies.CNY,
                    call.currencies.BTC

                    )
                )
               // mapeiaNome(listaTodasAsMoedas)

                listaDeMoedas.postValue(listaTodasAsMoedas)
            } catch (e: Exception) {
                errorTest.postValue("Ops, ocorreu um erro")
            }
        }

    }
    private fun mapeiaNome(moedas: List<MoedaModel?>): List<MoedaModel?> {
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



}