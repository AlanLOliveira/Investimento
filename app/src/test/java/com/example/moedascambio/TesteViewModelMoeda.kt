package com.example.moedascambio

import com.example.moedascambio.model.MoedaDataModel
import com.example.moedascambio.model.MoedaModel
import com.example.moedascambio.repository.RepositorioMoeda
import com.example.moedascambio.viewmodel.MoedaViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class TesteMoedaViewModel : BaseTest() {

    private var api = mockk <RepositorioMoeda>(relaxUnitFun = true)
    private var viewModel : MoedaViewModel = MoedaViewModel(api)

    @Test
    fun retornoMoedaApi(){

        val resultado = MoedaDataModel().apply {
            this.currencies.USD = MoedaModel(
                nome_moeda = "Dollar",
                variacao_moeda = 0.0,
                valor_compra = 0.5,
                valor_venda = 0.6,
                isoMoeda = "USD"
             )
        }
        val resultListaTeste = listOfNotNull(
            resultado.currencies.USD,
            resultado.currencies.GBP,
            resultado.currencies.ARS,
            resultado.currencies.CAD,
            resultado.currencies.BTC,
            resultado.currencies.AUD,
            resultado.currencies.JPY,
            resultado.currencies.EUR,
            resultado.currencies.CNY
        )
        coEvery { api.lerMoedas() } returns resultado
        viewModel.atualizaMoedas()
        assertEquals(resultListaTeste, viewModel.listaDeMoedas.value)
    }

    @Test
    fun testeDeFalhaViewModelMoedas(){
        coEvery { api.lerMoedas() }throws Exception("Ops, ocorreu um erro")
        viewModel.atualizaMoedas()

        assertEquals("Ops, ocorreu um erro", viewModel.errorTest.value)
    }

}